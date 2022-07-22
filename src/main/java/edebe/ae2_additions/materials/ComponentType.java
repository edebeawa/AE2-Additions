package edebe.ae2_additions.materials;

import appeng.api.features.AEFeature;
import appeng.items.materials.MaterialType;
import edebe.ae2_additions.common.lib.LibItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.EnumSet;
import java.util.Set;

import static edebe.ae2_additions.common.helper.ResourceLocationHelper.prefix;

public enum ComponentType {
    ITEM_1K_CELL_COMPONENT("storage_cell", 1, 0.5, 63, MaterialType.ITEM_1K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_4K_CELL_COMPONENT("storage_cell", 4, 1, 63, MaterialType.ITEM_4K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_16K_CELL_COMPONENT("storage_cell", 16, 1.5, 63, MaterialType.ITEM_16K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_64K_CELL_COMPONENT("storage_cell", 64, 2, 63, MaterialType.ITEM_64K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_256K_CELL_COMPONENT("storage_cell", 256, 2.5, 63, LibItems.ITEM_256K_CELL_COMPONENT, CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_1M_CELL_COMPONENT("storage_cell", 1, 3, 63, LibItems.ITEM_1M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_4M_CELL_COMPONENT("storage_cell", 4, 3.5, 63, LibItems.ITEM_4M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_16M_CELL_COMPONENT("storage_cell", 16, 4, 63, LibItems.ITEM_16M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_64M_CELL_COMPONENT("storage_cell", 64, 4.5, 63, LibItems.ITEM_64M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_256M_CELL_COMPONENT("storage_cell", 256, 5, 63, LibItems.ITEM_256M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),

    FLUID_1K_CELL_COMPONENT("fluid_storage_cell", 1, 0.5, 5, MaterialType.FLUID_1K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_4K_CELL_COMPONENT("fluid_storage_cell", 4, 1, 5, MaterialType.FLUID_4K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_16K_CELL_COMPONENT("fluid_storage_cell", 16, 1.5, 5, MaterialType.FLUID_16K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_64K_CELL_COMPONENT("fluid_storage_cell", 64, 2, 5, MaterialType.FLUID_64K_CELL_COMPONENT.getItemInstance(), CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_256K_CELL_COMPONENT("fluid_storage_cell", 256, 2.5, 5, LibItems.FLUID_256K_CELL_COMPONENT, CellUnit.KB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_1M_CELL_COMPONENT("fluid_storage_cell", 1, 3, 5, LibItems.FLUID_1M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_4M_CELL_COMPONENT("fluid_storage_cell", 4, 3.5, 5, LibItems.FLUID_4M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_16M_CELL_COMPONENT("fluid_storage_cell", 16, 4, 5, LibItems.FLUID_16M_CELL_COMPONENT, CellUnit.MB, EnumSet.of(AEFeature.STORAGE_CELLS)),

    ITEM_1K_PORTABLE_CELL_COMPONENT("portable_cell", 1, 0.5, 57, Items.AIR, CellUnit.KB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_4K_PORTABLE_CELL_COMPONENT("portable_cell", 4, 0.5, 51, Items.AIR, CellUnit.KB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_16K_PORTABLE_CELL_COMPONENT("portable_cell", 16, 0.5, 45, Items.AIR, CellUnit.KB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_64K_PORTABLE_CELL_COMPONENT("portable_cell", 64, 0.5, 39, Items.AIR, CellUnit.KB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_256K_PORTABLE_CELL_COMPONENT("portable_cell", 256, 0.5, 33, Items.AIR, CellUnit.KB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_1M_PORTABLE_CELL_COMPONENT("portable_cell", 1, 0.5, 27, Items.AIR, CellUnit.MB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_4M_PORTABLE_CELL_COMPONENT("portable_cell", 4, 0.5, 21, Items.AIR, CellUnit.MB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_16M_PORTABLE_CELL_COMPONENT("portable_cell", 16, 0.5, 15, Items.AIR, CellUnit.MB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_64M_PORTABLE_CELL_COMPONENT("portable_cell", 64, 0.5, 9, Items.AIR, CellUnit.MB, EnumSet.of(AEFeature.PORTABLE_CELL)),
    ITEM_256M_PORTABLE_CELL_COMPONENT("portable_cell", 256, 0.5, 3, Items.AIR, CellUnit.MB, EnumSet.of(AEFeature.PORTABLE_CELL)),

    ITEM_CUSTOM_CELL_COMPONENT("custom_storage_cell", -1, -1, -1, 9.5, Items.AIR, EnumSet.of(AEFeature.STORAGE_CELLS)),
    FLUID_CUSTOM_CELL_COMPONENT("custom_fluid_storage_cell", -1, -1, -1, 9.5, Items.AIR, EnumSet.of(AEFeature.STORAGE_CELLS)),
    ITEM_CUSTOM_PORTABLE_CELL_COMPONENT("custom_portable_cell", -1, -1, -1, 1.5, Items.AIR, EnumSet.of(AEFeature.PORTABLE_CELL)),

    THIS;

    private Set<AEFeature> features;
    private String id;
    private int storage;
    private int perType;
    private int bytes;
    private int types;
    private double idleDrain;
    private Item itemInstance;
    private CellUnit unit;
    private boolean isRegistered = false;

    ComponentType(final String typeName, int storage, double idleDrain, int types, Item itemInstance, CellUnit unit, final Set<AEFeature> features) {
        this.features = features;
        this.id = storage + unit.getName() + "_" + typeName;
        this.storage = storage;
        this.perType = storage * unit.getType();
        this.idleDrain = idleDrain;
        this.bytes = storage * unit.getScalar();
        this.types = types;
        this.itemInstance = itemInstance;
        this.unit = unit;
    }

    ComponentType(final String id, int perType, int bytes, int types, double idleDrain, Item itemInstance, final Set<AEFeature> features) {
        this.features = features;
        this.id = id;
        this.perType = perType;
        this.idleDrain = idleDrain;
        this.bytes = bytes;
        this.types = types;
        this.itemInstance = itemInstance;
    }

    ComponentType() {}

    public ComponentType createCellMaterialType(final String id, int perType, int bytes, int types, double idleDrain, Item itemInstance, final Set<AEFeature> features) {
        this.features = features;
        this.id = id;
        this.perType = perType;
        this.idleDrain = idleDrain;
        this.bytes = bytes;
        this.types = types;
        this.itemInstance = itemInstance;
        return this;
    }

    public ItemStack stack(final int size) {
        return new ItemStack(this.getItemInstance(), size);
    }

    public Set<AEFeature> getFeature() {
        return this.features;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void markReady() {
        this.isRegistered = true;
    }

    public Item getItemInstance() {
        return this.itemInstance;
    }

    public String getId() {
        return this.id;
    }

    public ResourceLocation getRegistryName() {
        return prefix(this.id);
    }

    public int getStorage() {
        return this.storage;
    }

    public int getPerType() {
        return this.perType;
    }

    public double getIdleDrain() {
        return this.idleDrain;
    }

    public int getBytes() {
        return this.bytes;
    }

    public int getTypes() {
        return this.types;
    }

    public CellUnit getUnit() {
        return this.unit;
    }
}
