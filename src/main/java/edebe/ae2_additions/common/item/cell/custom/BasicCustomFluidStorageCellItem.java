package edebe.ae2_additions.common.item.cell.custom;

import appeng.api.storage.IStorageChannel;
import appeng.api.storage.channels.IFluidStorageChannel;
import appeng.api.storage.data.IAEFluidStack;
import appeng.core.Api;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class BasicCustomFluidStorageCellItem extends AbstractCustomStorageCell<IAEFluidStack> {
    public BasicCustomFluidStorageCellItem(final ComponentType componentType, final CellMaterialType materialType) {
        super(materialType.getProperties(1), componentType, materialType);
    }

    @NotNull
    @Override
    public IStorageChannel<IAEFluidStack> getChannel() {
        return Api.instance().storage().getStorageChannel(IFluidStorageChannel.class);
    }
}
