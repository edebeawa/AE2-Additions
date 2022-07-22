package edebe.ae2_additions.common.lib;

import edebe.ae2_additions.common.item.cell.*;
import edebe.ae2_additions.common.item.cell.custom.*;
import edebe.ae2_additions.common.register.RegisterGroups;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Rarity;

public final class LibItems {
    public static final Properties properties = new Properties().group(RegisterGroups.AE2AdditionsGroup);

    public static Properties defaultBuilder() {
        return properties.maxStackSize(1).rarity(Rarity.COMMON);
    }

    public static Properties defaultBuilder(Rarity rarity) {
        return defaultBuilder().rarity(rarity);
    }

    public static Properties defaultBuilder(int maxStackSize) {
        return defaultBuilder().maxStackSize(maxStackSize);
    }

    public static Properties defaultBuilder(int maxStackSize, Rarity rarity) {
        return properties.maxStackSize(maxStackSize).rarity(rarity);
    }

    public static final Item ACACIA_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item BIRCH_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item JUNGLE_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item OAK_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item DARK_OAK_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item SPRUCE_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item CRIMSON_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item WARPED_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item STONE_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item IRON_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item GOLDEN_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item DIAMOND_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64));
    public static final Item NETHERITE_EMPTY_STORAGE_CELL = new EmptyStorageCellItem(defaultBuilder(64).isImmuneToFire());

    public static final Item ITEM_256K_CELL_COMPONENT = new CellComponent();
    public static final Item ITEM_1M_CELL_COMPONENT = new CellComponent();
    public static final Item ITEM_4M_CELL_COMPONENT = new CellComponent();
    public static final Item ITEM_16M_CELL_COMPONENT = new CellComponent();
    public static final Item ITEM_64M_CELL_COMPONENT = new CellComponent();
    public static final Item ITEM_256M_CELL_COMPONENT = new CellComponent();
    public static final Item FLUID_256K_CELL_COMPONENT = new CellComponent();
    public static final Item FLUID_1M_CELL_COMPONENT = new CellComponent();
    public static final Item FLUID_4M_CELL_COMPONENT = new CellComponent();
    public static final Item FLUID_16M_CELL_COMPONENT = new CellComponent();

    public static Item[] cell(CellMaterialType materialType) {
        return new Item[]{
                new BasicStorageCellItem(ComponentType.ITEM_1K_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_4K_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_16K_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_64K_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_256K_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_1M_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_4M_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_16M_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_64M_CELL_COMPONENT, materialType),
                new BasicStorageCellItem(ComponentType.ITEM_256M_CELL_COMPONENT, materialType),

                new BasicFluidStorageCellItem(ComponentType.FLUID_1K_CELL_COMPONENT, materialType),
                new BasicFluidStorageCellItem(ComponentType.FLUID_4K_CELL_COMPONENT, materialType),
                new BasicFluidStorageCellItem(ComponentType.FLUID_16K_CELL_COMPONENT, materialType),
                new BasicFluidStorageCellItem(ComponentType.FLUID_64K_CELL_COMPONENT, materialType),
                new BasicFluidStorageCellItem(ComponentType.FLUID_256K_CELL_COMPONENT, materialType),
                new BasicFluidStorageCellItem(ComponentType.FLUID_1M_CELL_COMPONENT, materialType),
                new BasicFluidStorageCellItem(ComponentType.FLUID_4M_CELL_COMPONENT, materialType),
                new BasicFluidStorageCellItem(ComponentType.FLUID_16M_CELL_COMPONENT, materialType),

                new PortableCellItem(ComponentType.ITEM_1K_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_4K_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_16K_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_64K_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_256K_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_1M_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_4M_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_16M_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_64M_PORTABLE_CELL_COMPONENT, materialType),
                new PortableCellItem(ComponentType.ITEM_256M_PORTABLE_CELL_COMPONENT, materialType),

                new BasicCustomStorageCellItem(ComponentType.ITEM_CUSTOM_CELL_COMPONENT, materialType),
                new BasicCustomFluidStorageCellItem(ComponentType.FLUID_CUSTOM_CELL_COMPONENT, materialType),
                new BasicCustomPortableCellItem(ComponentType.ITEM_CUSTOM_PORTABLE_CELL_COMPONENT, materialType)
        };
    }

    public static final Item[] ACACIA_CELL = cell(CellMaterialType.ACACIA);
    public static final Item[] BIRCH_CELL = cell(CellMaterialType.BIRCH);
    public static final Item[] JUNGLE_CELL = cell(CellMaterialType.JUNGLE);
    public static final Item[] OAK_CELL = cell(CellMaterialType.OAK);
    public static final Item[] DARK_OAK_CELL = cell(CellMaterialType.DARK_OAK);
    public static final Item[] SPRUCE_CELL = cell(CellMaterialType.SPRUCE);
    public static final Item[] CRIMSON_CELL = cell(CellMaterialType.CRIMSON);
    public static final Item[] WARPED_CELL = cell(CellMaterialType.WARPED);
    public static final Item[] STONE_CELL = cell(CellMaterialType.STONE);
    public static final Item[] IRON_CELL = cell(CellMaterialType.IRON);
    public static final Item[] GOLD_CELL = cell(CellMaterialType.GOLD);
    public static final Item[] DIAMOND_CELL = cell(CellMaterialType.DIAMOND);
    public static final Item[] NETHERITE_CELL = cell(CellMaterialType.NETHERITE);
}