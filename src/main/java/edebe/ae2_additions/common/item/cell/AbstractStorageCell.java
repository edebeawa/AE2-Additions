package edebe.ae2_additions.common.item.cell;

import appeng.api.config.FuzzyMode;
import appeng.api.exceptions.MissingDefinitionException;
import appeng.api.features.AEFeature;
import appeng.api.implementations.items.IStorageCell;
import appeng.api.implementations.items.IUpgradeModule;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;
import appeng.core.AEConfig;
import appeng.core.Api;
import appeng.items.AEBaseItem;
import appeng.items.contents.CellConfig;
import appeng.items.contents.CellUpgrades;
import appeng.util.InteractionUtil;
import appeng.util.InventoryAdaptor;
import edebe.ae2_additions.materials.ComponentType;
import edebe.ae2_additions.materials.CellMaterialType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static edebe.ae2_additions.common.helper.AE2AdditionsHelper.translationString;
import static edebe.ae2_additions.common.helper.ResourceLocationHelper.prefix;

@ParametersAreNonnullByDefault
public abstract class AbstractStorageCell<T extends IAEStack<T>> extends AEBaseItem implements IStorageCell<T> {
    protected final ComponentType component;
    protected final CellMaterialType material;

    public AbstractStorageCell(Properties properties, final ComponentType componentType, final CellMaterialType materialType) {
        super(properties);
        this.component = componentType;
        this.material = materialType;
        setRegistryName(prefix(materialType.getName() + "_" + componentType.getId()));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(final ItemStack cellItem, @Nullable final World world, final List<ITextComponent> tooltip, final ITooltipFlag flag) {
        CellHelper.addCellInformation(tooltip, Api.instance().registries().cell().getCellInventory(cellItem, null, this.getChannel()));
        //Api.instance().client().addCellInformation(Api.instance().registries().cell().getCellInventory(stack, null, this.getChannel()), lines);
    }

    @NotNull
    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent("item.ae2_additions." + component.getId(), translationString("item.ae2_additions." + material.getName()));
    }

    @Override
    public int getBytes(final ItemStack cellItem) {
        return this.component.getBytes();
    }

    @Override
    public int getBytesPerType(ItemStack cellItem) {
        return this.component.getPerType();
    }

    @Override
    public double getIdleDrain() {
        return this.component.getIdleDrain();
    }

    @Override
    public int getTotalTypes(final ItemStack cellItem) {
        return this.component.getTypes();
    }

    @Override
    public boolean isBlackListed(final ItemStack cellItem, final T requestedAddition) {
        return false;
    }

    @Override
    public boolean storableInStorageCell() {
        return false;
    }

    @Override
    public boolean isStorageCell(final ItemStack cellItem) {
        return true;
    }

    @Override
    public boolean isEditable(final ItemStack cellItem) {
        return true;
    }

    @Override
    public IItemHandler getUpgradesInventory(final ItemStack cellItem) {
        return new CellUpgrades(cellItem, 2);
    }

    @Override
    public IItemHandler getConfigInventory(final ItemStack cellItem) {
        return new CellConfig(cellItem);
    }

    @Override
    public FuzzyMode getFuzzyMode(final ItemStack cellItem) {
        final String fz = cellItem.getOrCreateTag().getString("FuzzyMode");
        if (fz.isEmpty()) {
            return FuzzyMode.IGNORE_ALL;
        }
        try {
            return FuzzyMode.valueOf(fz);
        } catch (final Throwable t) {
            return FuzzyMode.IGNORE_ALL;
        }
    }

    @Override
    public void setFuzzyMode(final ItemStack cellItem, final FuzzyMode fzMode) {
        cellItem.getOrCreateTag().putString("FuzzyMode", fzMode.name());
    }

    @NotNull
    @Override
    public ActionResult<ItemStack> onItemRightClick(final World world, final PlayerEntity player, final Hand hand) {
        this.disassembleDrive(player.getHeldItem(hand), world, player);
        return new ActionResult<>(ActionResultType.func_233537_a_(world.isRemote()), player.getHeldItem(hand));
    }

    private boolean disassembleDrive(final ItemStack cellItem, final World world, final PlayerEntity player) {
        if (InteractionUtil.isInAlternateUseMode(player)) {
            if (world.isRemote() || component.getItemInstance() == Items.AIR) {
                return false;
            }

            final PlayerInventory playerInventory = player.inventory;
            final IMEInventoryHandler inventory = Api.instance().registries().cell().getCellInventory(cellItem, null, this.getChannel());
            if (inventory != null && playerInventory.getCurrentItem() == cellItem) {
                final InventoryAdaptor inventoryAdaptor = InventoryAdaptor.getAdaptor(player);
                final IItemList<IAEItemStack> list = inventory.getAvailableItems(this.getChannel().createList());
                if (list.isEmpty() && inventoryAdaptor != null) {
                    playerInventory.setInventorySlotContents(playerInventory.currentItem, ItemStack.EMPTY);

                    // drop core
                    final ItemStack extraB = inventoryAdaptor.addItems(this.component.stack(1));
                    if (!extraB.isEmpty()) {
                        player.dropItem(extraB, false);
                    }

                    // drop upgrades
                    final IItemHandler upgradesInventory = this.getUpgradesInventory(cellItem);
                    for (int upgradeIndex = 0; upgradeIndex < upgradesInventory.getSlots(); upgradeIndex++) {
                        final ItemStack upgradeStack = upgradesInventory.getStackInSlot(upgradeIndex);
                        final ItemStack leftStack = inventoryAdaptor.addItems(upgradeStack);
                        if (!leftStack.isEmpty() && upgradeStack.getItem() instanceof IUpgradeModule) {
                            player.dropItem(upgradeStack, false);
                        }
                    }

                    // drop empty storage cell case
                    final ItemStack extraA = inventoryAdaptor.addItems(new ItemStack(material.getEmptyCell()));
                    if (!extraA.isEmpty()) {
                        player.dropItem(extraA, false);
                    }

                    if (player.container != null) {
                        player.container.detectAndSendChanges();
                    }

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        return this.disassembleDrive(stack, context.getWorld(), context.getPlayer())
                ? ActionResultType.func_233537_a_(context.getWorld().isRemote())
                : ActionResultType.PASS;
    }

    @Override
    public ItemStack getContainerItem(final ItemStack itemStack) {
        return Api.instance().definitions().materials().emptyStorageCell().maybeStack(1)
                .orElseThrow(() -> new MissingDefinitionException(
                        "Tried to use empty storage cells while basic storage cells are defined."));
    }

    @Override
    public boolean hasContainerItem(final ItemStack stack) {
        return AEConfig.instance().isFeatureEnabled(AEFeature.ENABLE_DISASSEMBLY_CRAFTING);
    }
}
