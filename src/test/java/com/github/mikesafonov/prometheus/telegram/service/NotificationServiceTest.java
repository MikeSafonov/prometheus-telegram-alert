package com.github.mikesafonov.prometheus.telegram.service;

import com.github.mikesafonov.prometheus.telegram.dto.Alert;
import com.github.mikesafonov.prometheus.telegram.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.service.message.MessageConverter;
import com.github.mikesafonov.prometheus.telegram.service.telegram.TelegramApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

class NotificationServiceTest {
    private TelegramApiService telegramApiService;
    private MessageConverter messageConverter;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        telegramApiService = mock(TelegramApiService.class);
        messageConverter = mock(MessageConverter.class);

        notificationService = new NotificationService(telegramApiService, messageConverter);
    }

    @Test
    void shouldSendNotification() {
        Alert alert = new Alert();
        List<Alert> alerts = singletonList(alert);
        AlertManagerNotification notification = new AlertManagerNotification();
        notification.setAlerts(alerts);

        String message = "Alert message";

        when(messageConverter.convert(notification, alert)).thenReturn(message);

        notificationService.sendNotification(notification);

        verify(messageConverter).convert(notification, alert);
        verify(telegramApiService).sendMessage(message);
        verifyZeroInteractions(telegramApiService);
    }


    @Test
    void shouldSendAnyNotificationBecauseAlertsAreEmpty() {

        AlertManagerNotification notification = new AlertManagerNotification();
        notificationService.sendNotification(notification);

        verifyZeroInteractions(messageConverter);
        verifyZeroInteractions(telegramApiService);
    }
}
