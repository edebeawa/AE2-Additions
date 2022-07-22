package edebe.ae2_additions.recipe;

import appeng.api.storage.IMEInventory;
import appeng.api.storage.channels.IItemStorageChannel;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IItemList;
import appeng.core.Api;
import appeng.items.materials.MaterialType;
import edebe.ae2_additions.common.item.cell.AbstractStorageCell;
import edebe.ae2_additions.common.item.cell.CellComponent;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

import static edebe.ae2_additions.common.item.cell.CellHelper.getCell;

public class CellDisassembleRecipe extends SpecialRecipe {
    public static final SpecialRecipeSerializer<CellDisassembleRecipe> SERIALIZER = new SpecialRecipeSerializer<>(CellDisassembleRecipe::new);

    public CellDisassembleRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(@Nonnull final CraftingInventory inventory, @Nonnull final World world) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty() && (stack.getItem() instanceof AbstractStorageCell) && isCellEmpty(stack)) {
                return true;
            }
        }
        return false;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull CraftingInventory inventory) {
        for(int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AbstractStorageCell) {
                return new ItemStack(getCell(stack.getItem())[0]);
            }
        }
        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inventory) {
        NonNullList<ItemStack> nonNullList = NonNullList.withSize(inventory.getSizeInventory(), ItemStack.EMPTY);

        for(int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AbstractStorageCell) {
                nonNullList.set(i, new ItemStack(getCell(stack.getItem())[1]));
            }
        }

        return nonNullList;
    }

    private static boolean isCellEmpty(ItemStack storageCell) {
        final IMEInventory<IAEItemStack> cellInventory = Api.instance().registries().cell().getCellInventory(storageCell, null, Api.instance().storage().getStorageChannel(IItemStorageChannel.class));
        if (cellInventory != null) {
            final IItemList<IAEItemStack> list = cellInventory.getAvailableItems(Api.instance().storage().getStorageChannel(IItemStorageChannel.class).createList());
            return list.isEmpty();
        }
        return true;
    }

    private static boolean isCellComponent(ItemStack stack) {
        return stack.getItem() instanceof CellComponent
                || stack.getItem() == MaterialType.ITEM_1K_CELL_COMPONENT.getItemInstance()
                || stack.getItem() == MaterialType.ITEM_4K_CELL_COMPONENT.getItemInstance()
                || stack.getItem() == MaterialType.ITEM_16K_CELL_COMPONENT.getItemInstance()
                || stack.getItem() == MaterialType.ITEM_64K_CELL_COMPONENT.getItemInstance();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
