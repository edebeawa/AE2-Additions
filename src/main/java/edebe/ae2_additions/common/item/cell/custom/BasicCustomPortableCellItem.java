package edebe.ae2_additions.common.item.cell.custom;

import appeng.api.implementations.guiobjects.IGuiItemObject;
import appeng.api.storage.IStorageChannel;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.core.Api;
import appeng.items.contents.PortableCellViewer;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class BasicCustomPortableCellItem extends AbstractCustomPortableCell<IAEItemStack> {
    public BasicCustomPortableCellItem(final ComponentType componentType, final CellMaterialType materialType) {
        super(materialType.getProperties(1), componentType, materialType);
    }

    @NotNull
    @Override
    public IStorageChannel<IAEItemStack> getChannel() {
        return Api.instance().storage().getStorageChannel(IItemStorageChannel.class);
    }

    @Override
    public IGuiItemObject getGuiObject(final ItemStack itemStack, int playerInventorySlot, final World world, @Nullable final BlockPos pos) {
        return new PortableCellViewer(itemStack, playerInventorySlot);
    }
}
