package edebe.ae2_additions.common.item.cell.custom;

import appeng.api.storage.data.IAEStack;
import appeng.core.Api;
import appeng.items.contents.CellUpgrades;
import edebe.ae2_additions.Color;
import edebe.ae2_additions.common.item.cell.AbstractStorageCell;
import edebe.ae2_additions.common.item.cell.CellHelper;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public abstract class AbstractCustomStorageCell<T extends IAEStack<T>> extends AbstractStorageCell<T> {
    public AbstractCustomStorageCell(Item.Properties properties, final ComponentType componentType, final CellMaterialType materialType) {
        super(properties, componentType, materialType);
    }

    @Override
    public void fillItemGroup(@Nonnull ItemGroup tab, @Nonnull NonNullList<ItemStack> stacks) {
        if (this.isInGroup(tab)) {
            final ItemStack cellItem = new ItemStack(this);
            Color.initColor(cellItem);
            CellHelper.initCustomCell(cellItem);
            stacks.add(cellItem);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(final ItemStack cellItem, @Nullable final World world, final List<ITextComponent> tooltip, final ITooltipFlag flag) {
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
