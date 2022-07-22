package edebe.ae2_additions.common;

import edebe.ae2_additions.common.event.setup.ClientSetupEvent;
import edebe.ae2_additions.common.register.RegisterItems;
import edebe.ae2_additions.common.register.RegisterRecipes;
import edebe.ae2_additions.core.api.AE2AdditionsAPI;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AE2AdditionsAPI.MOD_ID)
public class AE2Additions {
    private static final String PROTOCOL_VERSION = "1";

    public static final Logger LOGGER = LogManager.getLogger(AE2AdditionsAPI.MOD_ID);
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(AE2AdditionsAPI.MOD_ID, AE2AdditionsAPI.MOD_ID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public AE2Additions() {
        MinecraftForge.EVENT_BUS.register(new AE2AdditionsFMLBusEvents(this));
        MinecraftForge.EVENT_BUS.register(this);

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(this);
        modBus.addListener(ClientSetupEvent::init);
        modBus.addGenericListener(Item.class, RegisterItems::registerItems);
        modBus.addGenericListener(IRecipeSerializer.class, RegisterRecipes::registerRecipes);
    }

    private static class AE2AdditionsFMLBusEvents {
        private final AE2Additions parent;

        AE2AdditionsFMLBusEvents(AE2Additions parent) {
            this.parent = parent;
        }
    }
}
