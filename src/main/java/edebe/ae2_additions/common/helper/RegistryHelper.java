package edebe.ae2_additions.common.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static edebe.ae2_additions.common.helper.ResourceLocationHelper.prefix;

public interface RegistryHelper {
    static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> registry, ResourceLocation name, IForgeRegistryEntry<V> thing) {
        registry.register(thing.setRegistryName(name));
    }

    static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> registry, String name, IForgeRegistryEntry<V> thing) {
        register(registry, prefix(name), thing);
    }

    static void register(IForgeRegistry<Item> registry, Block block, Item.Properties properties) {
        register(registry, Registry.BLOCK.getKey(block), new BlockItem(block, properties));
    }

    static <V extends IForgeRegistryEntry<V>> void registerAll(IForgeRegistry<V> registry, String[] names, IForgeRegistryEntry<V>[] things) {
        for (int i = 0;i < names.length;i++) {
            register(registry, names[i], things[i]);
        }
    }

    static void registerAll(IForgeRegistry<Item> registry, String[] names, Item... items) {
        for (int i = 0;i < names.length;i++) {
            register(registry, names[i], items[i]);
        }
    }

    static void registerAll(IForgeRegistry<Item> registry, Item.Properties properties, Block... blocks) {
        for (Block block : blocks) {
            register(registry, block, properties);
        }
    }

    static void registerAll(ItemColors registry, IItemColor[] itemColors, IItemProvider... items) {
        for (int i = 0;i < itemColors.length;i++) {
            registry.register(itemColors[i], items[i]);
        }
    }
}