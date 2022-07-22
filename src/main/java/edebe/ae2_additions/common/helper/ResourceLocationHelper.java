package edebe.ae2_additions.common.helper;

import edebe.ae2_additions.core.api.AE2AdditionsAPI;
import net.minecraft.util.ResourceLocation;

public interface ResourceLocationHelper {
    static ResourceLocation prefix(String path) {
        return new ResourceLocation(AE2AdditionsAPI.MOD_ID, path);
    }
}
