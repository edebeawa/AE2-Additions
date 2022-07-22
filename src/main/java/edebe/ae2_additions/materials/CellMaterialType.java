package edebe.ae2_additions.materials;

import edebe.ae2_additions.common.lib.LibItems;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public enum CellMaterialType {
    ACACIA("acacia", LibItems.ACACIA_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    BIRCH("birch", LibItems.BIRCH_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    JUNGLE("jungle", LibItems.JUNGLE_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    OAK("oak", LibItems.OAK_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    DARK_OAK("dark_oak", LibItems.DARK_OAK_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    SPRUCE("spruce", LibItems.SPRUCE_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    CRIMSON("crimson", LibItems.CRIMSON_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    WARPED("warped", LibItems.WARPED_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),

    STONE("stone", LibItems.STONE_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    IRON("iron", LibItems.IRON_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    GOLD("golden", LibItems.GOLDEN_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    DIAMOND("diamond", LibItems.DIAMOND_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON)),
    NETHERITE("netherite", LibItems.NETHERITE_EMPTY_STORAGE_CELL, LibItems.properties.rarity(Rarity.COMMON).isImmuneToFire());

    private final String name;
    private final Item emptyCell;
    private final Properties properties;

    CellMaterialType(String name, Item emptyCell, Properties properties) {
        this.name = name;
        this.emptyCell = emptyCell;
        this.properties = properties;
    }

    public String getName() {
        return this.name;
    }

    public Item getEmptyCell() {
        return this.emptyCell;
    }

    public Properties getProperties(int maxStackSize) {
        return this.properties.maxStackSize(maxStackSize);
    }
}