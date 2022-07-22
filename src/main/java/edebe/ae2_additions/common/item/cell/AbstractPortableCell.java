package edebe.ae2_additions.common.item.cell;

import appeng.api.config.FuzzyMode;
import appeng.api.implementations.guiobjects.IGuiItem;
import appeng.api.implementations.items.IStorageCell;
import appeng.api.storage.data.IAEStack;
import appeng.container.ContainerLocator;
import appeng.container.ContainerOpener;
import appeng.container.me.items.MEPortableCellContainer;
import appeng.core.AEConfig;
import appeng.core.Api;
import appeng.items.contents.CellConfig;
import appeng.items.contents.CellUpgrades;
import appeng.items.tools.powered.powersink.AEBasePoweredItem;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static edebe.ae2_additions.common.helper.AE2AdditionsHelper.translationString;
import static edebe.ae2_additions.common.helper.ResourceLocationHelper.prefix;

@ParametersAreNonnullByDefault
public abstract class AbstractPortableCell<T extends IAEStack<T>> extends AEBasePoweredItem implements IStorageCell<T>, IGuiItem {
    protected final ComponentType component;
    protected final CellMaterialType material;

    public AbstractPortableCell(Properties properties, final ComponentType componentType, final CellMaterialType materialType) {
        super(AEConfig.instance().getPortableCellBattery(), properties);
        this.component = componentType;
        this.material = materialType;
        setRegistryName(prefix(materialType.getName() + "_" + componentType.getId()));
    }

    @NotNull
    @Override
    public ActionResult<ItemStack> onItemRightClick(final World world, final PlayerEntity player, final Hand hand) {
        ContainerOpener.openContainer(MEPortableCellContainer.TYPE, player, ContainerLocator.forHand(player, hand));
        return new ActionResult<>(ActionResultType.func_233537_a_(world.isRemote()), player.getHeldItem(hand));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(final ItemStack cellItem, @Nullable final World world, final List<ITextComponent> tooltip, final ITooltipFlag flag) {
        super.addInformation(cellItem, world, tooltip, flag);
        CellHelper.addCellInformation(tooltip, Api.instance().registries().cell().getCellInventory(cellItem, null, this.getChannel()));
    }

    @NotNull
    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent("item.ae2_additions." + component.getId(), translationString("item.ae2_additions." + material.getName()));
    }

    @Override
    public int getBytes(final ItemStack cellItem) {
        return this.component.getBytes();
    }

    @Override
    public int getBytesPerType(ItemStack cellItem) {
        return this.component.getPerType();
    }

    @Override
    public double getIdleDrain() {
        return this.component.getIdleDrain();
    }

    @Override
    public int getTotalTypes(final ItemStack cellItem) {
        return this.component.getTypes();
    }

    @Override
    public boolean isBlackListed(final ItemStack cellItem, final T requestedAddition) {
        return false;
    }

    @Override
    public boolean storableInStorageCell() {
        return false;
    }

    @Override
    public boolean isStorageCell(final ItemStack cellItem) {
        return true;
    }

    @Override
    public boolean isEditable(final ItemStack cellItem) {
        return true;
    }

    @Override
    public IItemHandler getUpgradesInventory(final ItemStack cellItem) {
        return new CellUpgrades(cellItem, 2);
    }

    @Override
    public IItemHandler getConfigInventory(final ItemStack cellItem) {
        return new CellConfig(cellItem);
    }

    @Override
    public FuzzyMode getFuzzyMode(final ItemStack cellItem) {
        final String fz = cellItem.getOrCreateTag().getString("FuzzyMode");
        try {
            return FuzzyMode.valueOf(fz);
        } catch (final Throwable t) {
            return FuzzyMode.IGNORE_ALL;
        }
    }

    @Override
    public void setFuzzyMode(final ItemStack cellItem, final FuzzyMode fzMode) {
        cellItem.getOrCreateTag().putString("FuzzyMode", fzMode.name());
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }
}
