package com.github.mikesafonov.prometheus.telegram.service;

import com.github.mikesafonov.prometheus.alerts.starter.dto.Alert;
import com.github.mikesafonov.prometheus.alerts.starter.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.service.message.MessageConverter;
import com.github.mikesafonov.prometheus.telegram.service.telegram.TelegramApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

class TelegramNotificationServiceTest {
    private TelegramApiService telegramApiService;
    private MessageConverter messageConverter;
    private TelegramNotificationService notificationService;

    @BeforeEach
    void setUp() {
        telegramApiService = mock(TelegramApiService.class);
        messageConverter = mock(MessageConverter.class);

        notificationService = new TelegramNotificationService(telegramApiService, messageConverter);
    }

    @Test
    void shouldSendNotification() {
        Alert alert = new Alert();
        List<Alert> alerts = singletonList(alert);
        AlertManagerNotification notification = new AlertManagerNotification();
        notification.setAlerts(alerts);

        String message = "Alert message";

        when(messageConverter.convert(notification, alert)).thenReturn(message);

        notificationService.onNotification(notification);

        verify(messageConverter).convert(notification, alert);
        verify(telegramApiService).sendMessage(message);
        verifyZeroInteractions(telegramApiService);
    }


    @Test
    void shouldSendAnyNotificationBecauseAlertsAreEmpty() {

        AlertManagerNotification notification = new AlertManagerNotification();
        notificationService.onNotification(notification);

        verifyZeroInteractions(messageConverter);
        verifyZeroInteractions(telegramApiService);
    }
}
