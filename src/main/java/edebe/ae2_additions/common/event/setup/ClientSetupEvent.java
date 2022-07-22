package edebe.ae2_additions.common.event.setup;

import edebe.ae2_additions.common.lib.LibItemColors;
import edebe.ae2_additions.common.lib.LibItems;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static edebe.ae2_additions.common.helper.RegistryHelper.registerAll;

@OnlyIn(Dist.CLIENT)
public class ClientSetupEvent {
    public static void init(FMLClientSetupEvent event) {
        registerItemProperty(event);
        registerItemColors();
    }

    private static void registerItemProperty(FMLClientSetupEvent event) {
        /*event.enqueueWork(() -> {
            ItemModelsProperties.registerProperty(LibItems.LENS_MANA, "outputMode", (itemStack, clientWorld, livingEntity) -> (LensMana.getMode(itemStack)?1F:0F));
        });*/
    }

    private static void registerItemColors() {
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.ACACIA_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.BIRCH_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.JUNGLE_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.OAK_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.DARK_OAK_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.SPRUCE_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.CRIMSON_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.WARPED_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.STONE_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.IRON_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.GOLD_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.DIAMOND_CELL);
        registerAll(Minecraft.getInstance().getItemColors(), LibItemColors.CELL_COLOR, LibItems.NETHERITE_CELL);
    }
}