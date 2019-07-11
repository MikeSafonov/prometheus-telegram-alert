package com.github.mikesafonov.prometheus.telegram.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mike Safonov
 */
class TelegramMarkdownFormatterTest {

    @Test
    void shouldMakeBold() {
        String testValue = "Example string";

        assertEquals("*Example string*", TelegramMarkdownFormatter.makeBold(testValue));
    }

    @Test
    void shouldAddParagraph() {
        String testValue = "Example string";

        assertEquals("Example string\n", TelegramMarkdownFormatter.addParagraph(testValue));
    }

}
