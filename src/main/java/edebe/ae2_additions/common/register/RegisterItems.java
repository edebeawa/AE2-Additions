package edebe.ae2_additions.common.register;

import edebe.ae2_additions.common.lib.LibItemNames;
import edebe.ae2_additions.common.lib.LibItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static edebe.ae2_additions.common.helper.RegistryHelper.registerAll;

public final class RegisterItems {
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(LibItems.ACACIA_CELL);
        registry.registerAll(LibItems.BIRCH_CELL);
        registry.registerAll(LibItems.JUNGLE_CELL);
        registry.registerAll(LibItems.OAK_CELL);
        registry.registerAll(LibItems.DARK_OAK_CELL);
        registry.registerAll(LibItems.SPRUCE_CELL);
        registry.registerAll(LibItems.CRIMSON_CELL);
        registry.registerAll(LibItems.WARPED_CELL);
        registry.registerAll(LibItems.STONE_CELL);
        registry.registerAll(LibItems.IRON_CELL);
        registry.registerAll(LibItems.GOLD_CELL);
        registry.registerAll(LibItems.DIAMOND_CELL);
        registry.registerAll(LibItems.NETHERITE_CELL);
        registerAll(registry, LibItemNames.EMPTY_STORAGE_CELL,
                LibItems.ACACIA_EMPTY_STORAGE_CELL,
                LibItems.BIRCH_EMPTY_STORAGE_CELL,
                LibItems.JUNGLE_EMPTY_STORAGE_CELL,
                LibItems.OAK_EMPTY_STORAGE_CELL,
                LibItems.DARK_OAK_EMPTY_STORAGE_CELL,
                LibItems.SPRUCE_EMPTY_STORAGE_CELL,
                LibItems.CRIMSON_EMPTY_STORAGE_CELL,
                LibItems.WARPED_EMPTY_STORAGE_CELL,

                LibItems.STONE_EMPTY_STORAGE_CELL,
                LibItems.IRON_EMPTY_STORAGE_CELL,
                LibItems.GOLDEN_EMPTY_STORAGE_CELL,
                LibItems.DIAMOND_EMPTY_STORAGE_CELL,
                LibItems.NETHERITE_EMPTY_STORAGE_CELL
        );
        registerAll(registry, LibItemNames.CELL_COMPONENT,
                LibItems.ITEM_256K_CELL_COMPONENT,
                LibItems.ITEM_1M_CELL_COMPONENT,
                LibItems.ITEM_4M_CELL_COMPONENT,
                LibItems.ITEM_16M_CELL_COMPONENT,
                LibItems.ITEM_64M_CELL_COMPONENT,
                LibItems.ITEM_256M_CELL_COMPONENT,

                LibItems.FLUID_256K_CELL_COMPONENT,
                LibItems.FLUID_1M_CELL_COMPONENT,
                LibItems.FLUID_4M_CELL_COMPONENT,
                LibItems.FLUID_16M_CELL_COMPONENT
        );
    }
}