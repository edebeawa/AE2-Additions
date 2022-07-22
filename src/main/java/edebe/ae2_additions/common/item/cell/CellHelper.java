package edebe.ae2_additions.common.item.cell;

import appeng.api.config.IncludeExclude;
import appeng.api.storage.cells.ICellInventory;
import appeng.api.storage.cells.ICellInventoryHandler;
import appeng.api.storage.data.IAEStack;
import appeng.core.localization.GuiText;
import appeng.items.materials.MaterialType;
import edebe.ae2_additions.common.lib.LibItems;
import edebe.ae2_additions.materials.CellMaterialType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edebe.ae2_additions.common.helper.AE2AdditionsHelper.*;
import static edebe.ae2_additions.common.helper.ResourceLocationHelper.prefix;
import static edebe.ae2_additions.common.helper.TooltipHelper.add;

public class CellHelper {
    private static final String TAG_CUSTOM_CELL = "CustomCell";
    private static final String TAG_BYTES = "bytes";
    private static final String TAG_TYPES = "types";

    public static int getBytes(ItemStack cellItem) {
        return cellItem.getOrCreateTag().getCompound(TAG_CUSTOM_CELL).getInt(TAG_BYTES);
    }

    public static int getBytesPerType(ItemStack cellItem) {
        return (int)(getBytes(cellItem) * (8 / 1024D));
    }

    public static int getTypes(ItemStack cellItem) {
        return cellItem.getOrCreateTag().getCompound(TAG_CUSTOM_CELL).getInt(TAG_TYPES);
    }

    public static void putCustomCell(ItemStack cellItem, int bytes, int types) {
        CompoundNBT cmd = new CompoundNBT();
        cmd.putInt(TAG_BYTES, bytes);
        cmd.putInt(TAG_TYPES, types);
        cellItem.getOrCreateTag().put(TAG_CUSTOM_CELL, cmd);
    }

    public static void initCustomCell(ItemStack cellItem) {
        CompoundNBT cmd = new CompoundNBT();
        cmd.putInt(TAG_BYTES, 1024);
        cmd.putInt(TAG_TYPES, 1);
        cellItem.getOrCreateTag().put(TAG_CUSTOM_CELL, cmd);
    }

    public static <T extends IAEStack<T>> void addCellInformation(List<ITextComponent> tooltip, ICellInventoryHandler<T> handler) {
        if (handler == null) {
            return;
        }

        final ICellInventory<?> cellInventory = handler.getCellInv();

        if (cellInventory != null) {
            ItemStack stack = cellInventory.getItemStack();
            int bytes;
            int types;

            if (stack.getItem() instanceof AbstractStorageCell) {
                bytes = ((AbstractStorageCell)stack.getItem()).getBytes(stack);
                types = ((AbstractStorageCell)stack.getItem()).getTotalTypes(stack);
            } else if (stack.getItem() instanceof AbstractPortableCell) {
                bytes = ((AbstractPortableCell)stack.getItem()).getBytes(stack);
                types = ((AbstractPortableCell)stack.getItem()).getTotalTypes(stack);
            } else {
                return;
            }

            add(tooltip, "tooltip.ae2_additions.cell", TextFormatting.WHITE, (bytes - cellInventory.getUsedBytes() <= 0 ? "§c" : "§a") + getSize(bytes - cellInventory.getUsedBytes()), getSize(bytes));
            tooltip.add(new StringTextComponent("|"));
            add(tooltip, "tooltip.ae2_additions.cell", TextFormatting.WHITE, (types - cellInventory.getStoredItemTypes() <= 0 ? "§c" : "§a") + (types - cellInventory.getStoredItemTypes() + "§r§f " + translationString("tooltip.ae2_additions.type")), types + " " + translationString("tooltip.ae2_additions.type"));
            tooltip.add(new StringTextComponent("|"));
        }

        if (handler.isPreformatted()) {
            final String list = (handler.getIncludeExcludeMode() == IncludeExclude.WHITELIST ? GuiText.Included : GuiText.Excluded).getLocal();

            if (handler.isFuzzy()) {
                tooltip.add(GuiText.Partitioned.withSuffix(" - " + list + " ").appendSibling(GuiText.Fuzzy.text()));
            } else {
                tooltip.add(GuiText.Partitioned.withSuffix(" - " + list + " ").appendSibling(GuiText.Precise.text()));
            }
        }
    }

    private static String getSize(long size) {
        StringBuilder bytes = new StringBuilder();
        DecimalFormat format = new DecimalFormat("0.###");
        if (size >= getPower(1024, 2)) {
            double i = (size / (double)(getPower(1024, 2)));
            bytes.append(format.format(i)).append("§r§f MB");
        } else if (size >= 1024) {
            double i = (size / 1024D);
            bytes.append(format.format(i)).append("§r§f KB");
        } else {
            if (size <= 0) {
                bytes.append("0§r§f B");
            }
            else {
                bytes.append((int) size).append("§r§f B");
            }
        }
        return bytes.toString();
    }

    public static Item getCell(Item emptyStorageCell, Item cellComponent) {
        Map<Item,String> emptyStorageCellMap = new HashMap();
        emptyStorageCellMap.put(CellMaterialType.ACACIA.getEmptyCell(), CellMaterialType.ACACIA.getName());
        emptyStorageCellMap.put(CellMaterialType.BIRCH.getEmptyCell(), CellMaterialType.BIRCH.getName());
        emptyStorageCellMap.put(CellMaterialType.JUNGLE.getEmptyCell(), CellMaterialType.JUNGLE.getName());
        emptyStorageCellMap.put(CellMaterialType.OAK.getEmptyCell(), CellMaterialType.OAK.getName());
        emptyStorageCellMap.put(CellMaterialType.DARK_OAK.getEmptyCell(), CellMaterialType.DARK_OAK.getName());
        emptyStorageCellMap.put(CellMaterialType.SPRUCE.getEmptyCell(), CellMaterialType.SPRUCE.getName());
        emptyStorageCellMap.put(CellMaterialType.CRIMSON.getEmptyCell(), CellMaterialType.CRIMSON.getName());
        emptyStorageCellMap.put(CellMaterialType.WARPED.getEmptyCell(), CellMaterialType.WARPED.getName());
        emptyStorageCellMap.put(CellMaterialType.STONE.getEmptyCell(), CellMaterialType.STONE.getName());
        emptyStorageCellMap.put(CellMaterialType.IRON.getEmptyCell(), CellMaterialType.IRON.getName());
        emptyStorageCellMap.put(CellMaterialType.GOLD.getEmptyCell(), CellMaterialType.GOLD.getName());
        emptyStorageCellMap.put(CellMaterialType.DIAMOND.getEmptyCell(), CellMaterialType.DIAMOND.getName());
        emptyStorageCellMap.put(CellMaterialType.NETHERITE.getEmptyCell(), CellMaterialType.NETHERITE.getName());

        Map<Item, String> cellComponentMap = new HashMap();
        cellComponentMap.put(MaterialType.ITEM_1K_CELL_COMPONENT.getItemInstance(), "1k_storage_cell");
        cellComponentMap.put(MaterialType.ITEM_4K_CELL_COMPONENT.getItemInstance(), "4k_storage_cell");
        cellComponentMap.put(MaterialType.ITEM_16K_CELL_COMPONENT.getItemInstance(), "16k_storage_cell");
        cellComponentMap.put(MaterialType.ITEM_64K_CELL_COMPONENT.getItemInstance(), "64k_storage_cell");
        cellComponentMap.put(LibItems.ITEM_256K_CELL_COMPONENT, "256k_storage_cell");
        cellComponentMap.put(LibItems.ITEM_1M_CELL_COMPONENT, "1m_storage_cell");
        cellComponentMap.put(LibItems.ITEM_4M_CELL_COMPONENT, "4m_storage_cell");
        cellComponentMap.put(LibItems.ITEM_16M_CELL_COMPONENT, "16m_storage_cell");
        cellComponentMap.put(LibItems.ITEM_64M_CELL_COMPONENT, "64m_storage_cell");
        cellComponentMap.put(LibItems.ITEM_256M_CELL_COMPONENT, "256m_storage_cell");

        cellComponentMap.put(MaterialType.FLUID_1K_CELL_COMPONENT.getItemInstance(), "1k_fluid_storage_cell");
        cellComponentMap.put(MaterialType.FLUID_4K_CELL_COMPONENT.getItemInstance(), "4k_fluid_storage_cell");
        cellComponentMap.put(MaterialType.FLUID_16K_CELL_COMPONENT.getItemInstance(), "16k_fluid_storage_cell");
        cellComponentMap.put(MaterialType.FLUID_64K_CELL_COMPONENT.getItemInstance(), "64k_fluid_storage_cell");
        cellComponentMap.put(LibItems.FLUID_256K_CELL_COMPONENT, "256k_fluid_storage_cell");
        cellComponentMap.put(LibItems.FLUID_1M_CELL_COMPONENT, "1m_fluid_storage_cell");
        cellComponentMap.put(LibItems.FLUID_4M_CELL_COMPONENT, "4m_fluid_storage_cell");
        cellComponentMap.put(LibItems.FLUID_16M_CELL_COMPONENT, "16m_fluid_storage_cell");

        return resourceLocationToItem(prefix(emptyStorageCellMap.get(emptyStorageCell) + "_" + cellComponentMap.get(cellComponent)));
    }

    public static Item[] getCell(Item storageCell) {
        Map<String,Item> emptyStorageCellMap = new HashMap();
        emptyStorageCellMap.put(CellMaterialType.ACACIA.getName(), CellMaterialType.ACACIA.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.BIRCH.getName(), CellMaterialType.BIRCH.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.JUNGLE.getName(), CellMaterialType.JUNGLE.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.OAK.getName(), CellMaterialType.OAK.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.DARK_OAK.getName(), CellMaterialType.DARK_OAK.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.SPRUCE.getName(), CellMaterialType.SPRUCE.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.CRIMSON.getName(), CellMaterialType.CRIMSON.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.WARPED.getName(), CellMaterialType.WARPED.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.STONE.getName(), CellMaterialType.STONE.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.IRON.getName(), CellMaterialType.IRON.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.GOLD.getName(), CellMaterialType.GOLD.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.DIAMOND.getName(), CellMaterialType.DIAMOND.getEmptyCell());
        emptyStorageCellMap.put(CellMaterialType.NETHERITE.getName(), CellMaterialType.NETHERITE.getEmptyCell());

        Map<String,Item> cellComponentMap = new HashMap();
        cellComponentMap.put("1k_storage_cell", MaterialType.ITEM_1K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("4k_storage_cell", MaterialType.ITEM_4K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("16k_storage_cell", MaterialType.ITEM_16K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("64k_storage_cell", MaterialType.ITEM_64K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("256k_storage_cell", LibItems.ITEM_256K_CELL_COMPONENT);
        cellComponentMap.put("1m_storage_cell", LibItems.ITEM_1M_CELL_COMPONENT);
        cellComponentMap.put("4m_storage_cell", LibItems.ITEM_4M_CELL_COMPONENT);
        cellComponentMap.put("16m_storage_cell", LibItems.ITEM_16M_CELL_COMPONENT);
        cellComponentMap.put("64m_storage_cell", LibItems.ITEM_64M_CELL_COMPONENT);
        cellComponentMap.put("256m_storage_cell", LibItems.ITEM_256M_CELL_COMPONENT);

        cellComponentMap.put("1k_fluid_storage_cell", MaterialType.FLUID_1K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("4k_fluid_storage_cell", MaterialType.FLUID_4K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("16k_fluid_storage_cell", MaterialType.FLUID_16K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("64k_fluid_storage_cell", MaterialType.FLUID_64K_CELL_COMPONENT.getItemInstance());
        cellComponentMap.put("256k_fluid_storage_cell", LibItems.FLUID_256K_CELL_COMPONENT);
        cellComponentMap.put("1m_fluid_storage_cell", LibItems.FLUID_1M_CELL_COMPONENT);
        cellComponentMap.put("4m_fluid_storage_cell", LibItems.FLUID_4M_CELL_COMPONENT);
        cellComponentMap.put("16m_fluid_storage_cell", LibItems.FLUID_16M_CELL_COMPONENT);

        String[] storageCellName = storageCell.getRegistryName().getPath().split("_");

        StringBuilder cellComponent = new StringBuilder();

        for (int i = 0; i < storageCellName.length; ++i) {
            if (i == 1) {
                cellComponent.append(storageCellName[1]);
            } else if (i > 1) {
                cellComponent.append("_");
                cellComponent.append(storageCellName[i]);
            }
        }

        return new Item[]{emptyStorageCellMap.get(storageCellName[0]), cellComponentMap.get(cellComponent.toString())};
    }
}