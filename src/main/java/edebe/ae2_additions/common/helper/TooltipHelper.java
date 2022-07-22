package edebe.ae2_additions.common.helper;

import net.minecraft.util.text.*;

import java.util.List;

import static edebe.ae2_additions.common.helper.AE2AdditionsHelper.TranslationTextComponentProcessing;

public interface TooltipHelper {
    static void add(List<ITextComponent> tooltip, String key, TextFormatting formatting, Object... value) {
        String text = new TranslationTextComponent(key,value).getString();
        tooltip.add(new StringTextComponent(TranslationTextComponentProcessing(text)).mergeStyle(formatting));
    }

    static void add(List<ITextComponent> tooltip, String key, Color color, Object... value) {
        String text = new TranslationTextComponent(key,value).getString();
        tooltip.add(new StringTextComponent(TranslationTextComponentProcessing(text)).modifyStyle(s -> s.setColor(color)));
    }
}