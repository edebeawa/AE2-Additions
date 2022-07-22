package edebe.ae2_additions.common.item.cell;

import appeng.api.storage.IStorageChannel;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.core.Api;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class BasicStorageCellItem extends AbstractStorageCell<IAEItemStack> {
    public BasicStorageCellItem(final ComponentType componentType, final CellMaterialType materialType) {
        super(materialType.getProperties(1), componentType, materialType);
    }

    @NotNull
    @Override
    public IStorageChannel<IAEItemStack> getChannel() {
        return Api.instance().storage().getStorageChannel(IItemStorageChannel.class);
    }
}
