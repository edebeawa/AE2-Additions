package edebe.ae2_additions.common.lib;

import net.minecraft.client.renderer.color.IItemColor;

import java.awt.*;

public class LibItemColors {
    private static IItemColor getItemColor() {
        return (stack, color) -> edebe.ae2_additions.Color.getColor(stack).getRGB();
    }

    public static final IItemColor[] CELL_COLOR = new IItemColor[]{
            (stack, color) -> new Color(170, 70, 35).getRGB(),
            (stack, color) -> new Color(255, 255, 0).getRGB(),
            (stack, color) -> new Color(0, 255, 0).getRGB(),
            (stack, color) -> new Color(0, 255, 255).getRGB(),
            (stack, color) -> new Color(0, 0, 255).getRGB(),
            (stack, color) -> new Color(255, 0, 0).getRGB(),
            (stack, color) -> new Color(255, 180, 0).getRGB(),
            (stack, color) -> new Color(255, 120, 0).getRGB(),
            (stack, color) -> new Color(60, 255, 170).getRGB(),
            (stack, color) -> new Color(160, 0, 255).getRGB(),

            (stack, color) -> new Color(170, 70, 35).getRGB(),
            (stack, color) -> new Color(255, 255, 0).getRGB(),
            (stack, color) -> new Color(0, 255, 0).getRGB(),
            (stack, color) -> new Color(0, 255, 255).getRGB(),
            (stack, color) -> new Color(0, 0, 255).getRGB(),
            (stack, color) -> new Color(255, 0, 0).getRGB(),
            (stack, color) -> new Color(255, 180, 0).getRGB(),
            (stack, color) -> new Color(255, 120, 0).getRGB(),

            (stack, color) -> new Color(170, 70, 35).getRGB(),
            (stack, color) -> new Color(255, 255, 0).getRGB(),
            (stack, color) -> new Color(0, 255, 0).getRGB(),
            (stack, color) -> new Color(0, 255, 255).getRGB(),
            (stack, color) -> new Color(0, 0, 255).getRGB(),
            (stack, color) -> new Color(255, 0, 0).getRGB(),
            (stack, color) -> new Color(255, 180, 0).getRGB(),
            (stack, color) -> new Color(255, 120, 0).getRGB(),
            (stack, color) -> new Color(60, 255, 170).getRGB(),
            (stack, color) -> new Color(160, 0, 255).getRGB(),

            getItemColor(),
            getItemColor(),
            getItemColor()
    };
}
