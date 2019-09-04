package com.github.mikesafonov.prometheus.telegram.service;

import com.github.mikesafonov.prometheus.alerts.starter.dto.Alert;
import com.github.mikesafonov.prometheus.alerts.starter.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.alerts.starter.dto.enums.AlertLevel;
import com.github.mikesafonov.prometheus.alerts.starter.dto.enums.AlertStatus;
import com.github.mikesafonov.prometheus.telegram.service.message.SimpleMessageConverter;
import com.vdurmont.emoji.EmojiManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static com.github.mikesafonov.prometheus.telegram.utils.TelegramMarkdownFormatter.addParagraph;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleMessageConverterTest {
    private SimpleMessageConverter simpleMessageConverter;

    @BeforeEach
    void setUp() {
        simpleMessageConverter = new SimpleMessageConverter();
    }

    @ParameterizedTest
    @MethodSource("alertLevelAndEmojiProvider")
    void defaultDataFiringAlert(AlertLevel level, String emoji) {
        Alert alert = firingAlertByLevel(level);
        when(alert.getTitle()).thenReturn(Optional.empty());
        when(alert.getDescription()).thenReturn(Optional.empty());

        AlertManagerNotification notification = null;
        String expectedMessage = String.format(emoji + " Title", "UTF-8");
        expectedMessage = addParagraph(expectedMessage);
        expectedMessage = addParagraph(expectedMessage + "Description");

        String message = simpleMessageConverter.convert(notification, alert);

        assertEquals(expectedMessage, message);
    }
    @ParameterizedTest
    @MethodSource("alertLevelAndEmojiProvider")
    void customFiringAlert(AlertLevel level, String emoji) {
        Alert alert = firingAlertByLevel(level);
        when(alert.getTitle()).thenReturn(Optional.of("CustomTitle"));
        when(alert.getDescription()).thenReturn(Optional.of("CustomDescription"));
        AlertManagerNotification notification = null;
        String expectedMessage = String.format(emoji + " CustomTitle", "UTF-8");
        expectedMessage = addParagraph(expectedMessage);
        expectedMessage = addParagraph(expectedMessage + "CustomDescription");

        String message = simpleMessageConverter.convert(notification, alert);

        assertEquals(expectedMessage, message);
    }

    @ParameterizedTest
    @MethodSource("alertLevelAndEmojiProvider")
    void defaultDataResolvedAlert(AlertLevel level, String emoji) {
        Alert alert = resolvedAlertByLevel(level);
        when(alert.getResolvedTitle()).thenReturn(Optional.empty());
        when(alert.getResolvedDescription()).thenReturn(Optional.empty());

        AlertManagerNotification notification = null;
        String expectedMessage = String.format(emoji + " " +EmojiManager.getForAlias("white_check_mark").getUnicode() + "Title", "UTF-8");
        expectedMessage = addParagraph(expectedMessage);
        expectedMessage = addParagraph(expectedMessage + "Description");

        String message = simpleMessageConverter.convert(notification, alert);

        assertEquals(expectedMessage, message);
    }
    @ParameterizedTest
    @MethodSource("alertLevelAndEmojiProvider")
    void customResolvedAlert(AlertLevel level, String emoji) {
        Alert alert = resolvedAlertByLevel(level);
        when(alert.getResolvedTitle()).thenReturn(Optional.of("CustomTitle"));
        when(alert.getResolvedDescription()).thenReturn(Optional.of("CustomDescription"));
        AlertManagerNotification notification = null;
        String expectedMessage = String.format(emoji + " " + EmojiManager.getForAlias("white_check_mark").getUnicode() + "CustomTitle", "UTF-8");
        expectedMessage = addParagraph(expectedMessage);
        expectedMessage = addParagraph(expectedMessage + "CustomDescription");

        String message = simpleMessageConverter.convert(notification, alert);

        assertEquals(expectedMessage, message);
    }

    private Alert firingAlertByLevel(AlertLevel level) {
        Alert alert = mock(Alert.class);
        when(alert.getStatus()).thenReturn(AlertStatus.firing);
        when(alert.getLevel()).thenReturn(Optional.ofNullable(level));
        return alert;
    }

    private Alert resolvedAlertByLevel(AlertLevel level){
        Alert alert = mock(Alert.class);
        when(alert.getStatus()).thenReturn(AlertStatus.resolved);
        when(alert.getLevel()).thenReturn(Optional.ofNullable(level));
        return alert;
    }

    private static Stream<Arguments> alertLevelAndEmojiProvider() {
        return Stream.of(
                Arguments.of(AlertLevel.INFO, EmojiManager.getForAlias("information_source").getUnicode()),
                Arguments.of(AlertLevel.CRITICAL, EmojiManager.getForAlias("fire").getUnicode()),
                Arguments.of(AlertLevel.WARNING, EmojiManager.getForAlias("warning").getUnicode())
        );
    }

//    @Nested
//    class ResolvedAlert {
//
//    }

}
