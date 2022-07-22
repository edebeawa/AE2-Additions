package edebe.ae2_additions.recipe;

import appeng.items.materials.MaterialType;
import edebe.ae2_additions.common.item.cell.CellComponent;
import edebe.ae2_additions.common.item.cell.EmptyStorageCellItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

import static edebe.ae2_additions.common.item.cell.CellHelper.getCell;

public final class CellRecipe extends SpecialRecipe {
    public static final SpecialRecipeSerializer<CellRecipe> SERIALIZER = new SpecialRecipeSerializer<>(CellRecipe::new);

    public CellRecipe(ResourceLocation id) {
        super(id);
    }

    @Override
    public boolean matches(@Nonnull final CraftingInventory inventory, @Nonnull final World world) {
        boolean foundEmptyStorageCell = false;
        boolean foundCellComponent = false;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof EmptyStorageCellItem) {
                    foundEmptyStorageCell = true;
                } else if (isCellComponent(stack)) {
                    foundCellComponent = true;
                } else {
                    return false;
                }
            }
        }

        return foundEmptyStorageCell && foundCellComponent;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull CraftingInventory inventory) {
        ItemStack emptyStorageCell = ItemStack.EMPTY;
        ItemStack cellComponent = ItemStack.EMPTY;

        for(int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof EmptyStorageCellItem) {
                    emptyStorageCell = stack;
                } else if (isCellComponent(stack)) {
                    cellComponent = stack;
                }
            }
        }
        return new ItemStack(getCell(emptyStorageCell.getItem(), cellComponent.getItem()));
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