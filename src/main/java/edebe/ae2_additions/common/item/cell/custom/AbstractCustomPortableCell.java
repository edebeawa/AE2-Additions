package edebe.ae2_additions.common.item.cell.custom;

import appeng.api.config.Actionable;
import appeng.api.config.PowerUnits;
import appeng.api.storage.data.IAEStack;
import appeng.core.Api;
import appeng.core.localization.GuiText;
import appeng.items.contents.CellUpgrades;
import edebe.ae2_additions.Color;
import edebe.ae2_additions.common.item.cell.AbstractPortableCell;
import edebe.ae2_additions.common.item.cell.CellHelper;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.List;

@ParametersAreNonnullByDefault
public abstract class AbstractCustomPortableCell<T extends IAEStack<T>> extends AbstractPortableCell<T> {
    private static final String CURRENT_POWER_NBT_KEY = "internalCurrentPower";

    public AbstractCustomPortableCell(Item.Properties properties, final ComponentType componentType, final CellMaterialType materialType) {
        super(properties, componentType, materialType);
    }

    @Override
    public void fillItemGroup(@Nonnull ItemGroup tab, @Nonnull NonNullList<ItemStack> stacks) {
        if (this.isInGroup(tab)) {
            final ItemStack cellItem = new ItemStack(this);
            Color.initColor(cellItem);
            CellHelper.initCustomCell(cellItem);
            stacks.add(cellItem);

            final ItemStack charged = cellItem.copy();
            injectAEPower(charged, getAEMaxPower(charged), Actionable.MODULATE);
            stacks.add(charged);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(final ItemStack cellItem, @Nullable final World world, final List<ITextComponent> tooltip, final ITooltipFlag flag) {
        final CompoundNBT tag = cellItem.getTag();
        double internalCurrentPower = 0;
        final double internalMaxPower = this.getAEMaxPower(cellItem);

        if (tag != null) {
            internalCurrentPower = tag.getDouble(CURRENT_POWER_NBT_KEY);
        }

        final double percent = internalCurrentPower / internalMaxPower;

        tooltip.add(GuiText.StoredEnergy.text().deepCopy().appendString(':' + MessageFormat.format(" {0,number,#} ", internalCurrentPower)).appendSibling(PowerUnits.AE.textComponent()).appendString(" - " + MessageFormat.format(" {0,number,#.##%} ", percent)));
        CellHelper.addCellInformation(tooltip, Api.instance().registries().cell().getCellInventory(cellItem, null, this.getChannel()));
    }

    @Override
    public int getBytes(final ItemStack cellItem) {
        return CellHelper.getBytes(cellItem);
    }

    @Override
    public int getBytesPerType(ItemStack cellItem) {
        return CellHelper.getBytesPerType(cellItem);
    }

    @Override
    public int getTotalTypes(final ItemStack cellItem) {
        return CellHelper.getTypes(cellItem);
    }

    @Override
    public IItemHandler getUpgradesInventory(final ItemStack cellItem) {
        return new CellUpgrades(cellItem, 1);
    }
}
