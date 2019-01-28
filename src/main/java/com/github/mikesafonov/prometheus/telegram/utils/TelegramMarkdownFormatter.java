package com.github.mikesafonov.prometheus.telegram.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TelegramMarkdownFormatter {

    public static String makeBold(String target) {
        return "*" + target + "*";
    }

    public static String addParagraph(String target) {
        return target + "\n";
    }
}
