package edebe.ae2_additions.common.helper;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public interface AE2AdditionsHelper {
    static int hsvToRGB(int h,int s,int v) {
        return MathHelper.hsvToRGB(h % 360 / 360F,s / 100F,v / 100F);
    }

    static java.awt.Color parseToColor(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        int a = (color >> 24) & 0xff;
        return new java.awt.Color(r,g,b,a);
    }

    static int random(int bound) {
        return new Random().nextInt(bound + 1);
    }

    static double numberFormat(double number,int mf){
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(mf);
        return Double.parseDouble(format.format(number));
    }

    static String TranslationTextComponentProcessing(String text) {
        String[] target = new String[]{"{TTC_A}"};
        String[] replacement = new String[]{"%"};
        for (int i = 0;i < target.length;i++) {
            text = text.replace(target[i],replacement[i]);
        }
        return text;
    }

    static String numberToString(Object number) {
        return new DecimalFormat("0.#").format(number);
    }

    static String translationString(String key) {
        return new TranslationTextComponent(key).getString();
    }

    static int getPower(int number, int power){
        if(power == 0) return 1;
        if(power == 1) return number;
        return number * getPower(number, power - 1);
    }

    static Item resourceLocationToItem(ResourceLocation resourceLocation) {
        return Registry.ITEM.getOrDefault(resourceLocation);
    }
}
