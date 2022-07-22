package edebe.ae2_additions.common.event;

import appeng.api.storage.cells.ICellInventory;
import appeng.api.storage.cells.ICellInventoryHandler;
import appeng.api.storage.data.IAEStack;
import appeng.core.Api;
import appeng.items.tools.powered.powersink.AEBasePoweredItem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import edebe.ae2_additions.common.item.cell.AbstractPortableCell;
import edebe.ae2_additions.common.item.cell.AbstractStorageCell;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

import static edebe.ae2_additions.common.helper.AE2AdditionsHelper.hsvToRGB;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ToolTipEvent {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof AEBasePoweredItem) {
            event.getToolTip().add(new StringTextComponent("|"));
        }
    }

    @SubscribeEvent
    public static void onToolTipRender(RenderTooltipEvent.PostText event) {
        if (event.getStack().isEmpty()) {
            return;
        }

        MatrixStack ms = event.getMatrixStack();

        ItemStack stack = event.getStack();
        int width = event.getWidth();
        int height = 6;
        int tooltipX = event.getX();
        int tooltipY = event.getY();

        if (stack.getItem() instanceof AEBasePoweredItem) {
            drawPower(ms, stack, tooltipX, tooltipY + event.getHeight(), width, height);
        }

        ICellInventoryHandler<?> handler;
        int drawStorageCellY;

        if (stack.getItem() instanceof AbstractStorageCell) {
            drawStorageCellY = tooltipY + 30;
            handler = Api.instance().registries().cell().getCellInventory(stack, null, ((AbstractStorageCell<?>) stack.getItem()).getChannel());
        } else if (stack.getItem() instanceof AbstractPortableCell) {
            drawStorageCellY = tooltipY + 40;
            handler = Api.instance().registries().cell().getCellInventory(stack, null, ((AbstractPortableCell<?>) stack.getItem()).getChannel());
        } else {
            return;
        }

        if (handler == null) {
            return;
        }

        drawStorageCell(ms, stack, handler, tooltipX, drawStorageCellY, width, height);
    }

    private static final String CURRENT_POWER_NBT_KEY = "internalCurrentPower";

    private static void drawPower(MatrixStack ms, ItemStack stack, int mouseX, int mouseY, int width, int height) {
        CompoundNBT tag = stack.getTag();

        if (tag == null) {
            return;
        }

        AEBasePoweredItem poweredItem = (AEBasePoweredItem)stack.getItem();
        double fraction = tag.getDouble(CURRENT_POWER_NBT_KEY) / poweredItem.getAEMaxPower(stack);
        int barWidth = (int) Math.ceil(width * fraction);

        RenderSystem.disableDepthTest();
        AbstractGui.fill(ms, mouseX - 1, mouseY - height - 1, mouseX + width + 1, mouseY + 1, new Color(0, 0, 0).getRGB());
        AbstractGui.fill(ms, mouseX, mouseY - height, mouseX + barWidth, mouseY, 0xFF000000 | new Color(155, 0, 255).getRGB());
        AbstractGui.fill(ms, mouseX + barWidth, mouseY - height, mouseX + width, mouseY, new Color(40, 40, 40).getRGB());
    }

    private static <T extends IAEStack<T>> void drawStorageCell(MatrixStack ms, ItemStack stack, ICellInventoryHandler<T> handler, int mouseX, int mouseY, int width, int height) {
        ICellInventory<?> cellInventory = handler.getCellInv();

        if (cellInventory == null) {
            return;
        }

        Item storageCell = stack.getItem();
        double byteBarFraction = 0;
        double typeBarFraction = 0;

        if (storageCell instanceof AbstractStorageCell) {
            byteBarFraction = cellInventory.getUsedBytes()/(double)(((AbstractStorageCell)storageCell).getBytes(stack));
            typeBarFraction = cellInventory.getStoredItemTypes()/(double)(((AbstractStorageCell)storageCell).getTotalTypes(stack));
        } else if (storageCell instanceof AbstractPortableCell) {
            byteBarFraction = cellInventory.getUsedBytes()/(double)(((AbstractPortableCell)storageCell).getBytes(stack));
            typeBarFraction = cellInventory.getStoredItemTypes()/(double)(((AbstractPortableCell)storageCell).getTotalTypes(stack));
        }

        int byteBarWidth = (int) Math.ceil(width * byteBarFraction);
        int typeBarWidth = (int) Math.ceil(width * typeBarFraction);

        RenderSystem.disableDepthTest();
        AbstractGui.fill(ms, mouseX - 1, mouseY - height - 1, mouseX + width + 1, mouseY + 1, new Color(0, 0, 0).getRGB());
        AbstractGui.fill(ms, mouseX, mouseY - height, mouseX + byteBarWidth, mouseY, new Color(0, 0, 0).getRGB() | hsvToRGB((int)((1 - byteBarFraction) * 100), 100, 100));
        AbstractGui.fill(ms, mouseX + byteBarWidth, mouseY - height, mouseX + width, mouseY, new Color(40, 40, 40).getRGB());
        mouseY += 20;
        AbstractGui.fill(ms, mouseX - 1, mouseY - height - 1, mouseX + width + 1, mouseY + 1, new Color(0, 0, 0).getRGB());
        AbstractGui.fill(ms, mouseX, mouseY - height, mouseX + typeBarWidth, mouseY, new Color(0, 0, 0).getRGB() | hsvToRGB((int)((1 - typeBarFraction) * 100), 100, 100));
        AbstractGui.fill(ms, mouseX + typeBarWidth, mouseY - height, mouseX + width, mouseY, new Color(40, 40, 40).getRGB());
    }
}
