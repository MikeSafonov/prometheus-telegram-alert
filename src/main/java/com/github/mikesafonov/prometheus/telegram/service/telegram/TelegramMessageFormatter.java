package com.github.mikesafonov.prometheus.telegram.service.telegram;

public class TelegramMessageFormatter {

    public static String makeBold(String target) {
        return "*" + target + "*";
    }

    public static String addParagraph(String target) {
        return target + "\n";
    }
}
