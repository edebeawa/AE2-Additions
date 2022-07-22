
package edebe.ae2_additions.common.group;

import edebe.ae2_additions.core.api.AE2AdditionsAPI;
import edebe.ae2_additions.common.lib.LibItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class AE2AdditionsGroup extends ItemGroup {
    public AE2AdditionsGroup() {
        super(AE2AdditionsAPI.MOD_ID + "_group");
    }

    @NotNull
    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        ItemStack stack = new ItemStack(LibItems.GOLD_CELL[0]);
        return stack;
    }
}
