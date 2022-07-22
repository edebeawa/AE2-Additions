package edebe.ae2_additions.common.event;

import edebe.ae2_additions.core.api.AE2AdditionsAPI;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AE2AdditionsAPI.MOD_ID, value = Dist.CLIENT)
public class ClientProxyEvent {
    @SubscribeEvent
    public static void stitchTextures(TextureStitchEvent.Pre event) {
        /*if (event.getMap().getTextureLocation() == PlayerContainer.LOCATION_BLOCKS_TEXTURE) {
            event.addSprite(ResourceLocationHelper.prefix("item/empty_interlayer_slot"));
        }*/
    }
}