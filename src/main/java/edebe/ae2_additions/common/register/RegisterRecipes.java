package edebe.ae2_additions.common.register;

import edebe.ae2_additions.recipe.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static edebe.ae2_additions.common.helper.RegistryHelper.*;

public class RegisterRecipes {
    public static void registerRecipes(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();
        register(registry, "cell_recipe", CellRecipe.SERIALIZER);
        register(registry, "cell_disassemble_recipe", CellDisassembleRecipe.SERIALIZER);
    }
}
