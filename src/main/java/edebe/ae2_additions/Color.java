package edebe.ae2_additions;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class Color {
    private static final String TAG_COLOR = "Color";
    private static final String TAG_RED = "red";
    private static final String TAG_GREEN = "green";
    private static final String TAG_BLUE = "blue";
    private static final String TAG_ALPHA = "alpha";

    public static java.awt.Color getColor(ItemStack stack) {
        CompoundNBT cmd = stack.getOrCreateTag().getCompound(TAG_COLOR);
        return new java.awt.Color(cmd.getInt(TAG_RED),cmd.getInt(TAG_GREEN),cmd.getInt(TAG_BLUE),cmd.getInt(TAG_ALPHA));
    }

    public static void putColor(ItemStack stack, java.awt.Color color) {
        CompoundNBT cmd = new CompoundNBT();
        cmd.putInt(TAG_RED, color.getRed());
        cmd.putInt(TAG_GREEN, color.getGreen());
        cmd.putInt(TAG_BLUE, color.getBlue());
        cmd.putInt(TAG_ALPHA, color.getAlpha());
        stack.getOrCreateTag().put(TAG_COLOR, cmd);
    }

    public static void initColor(ItemStack stack) {
        CompoundNBT cmd = new CompoundNBT();
        cmd.putInt(TAG_RED, 255);
        cmd.putInt(TAG_GREEN, 255);
        cmd.putInt(TAG_BLUE, 255);
        cmd.putInt(TAG_ALPHA, 255);
        stack.getOrCreateTag().put(TAG_COLOR, cmd);
    }
}
