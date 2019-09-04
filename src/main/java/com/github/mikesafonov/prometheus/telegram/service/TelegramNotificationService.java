package com.github.mikesafonov.prometheus.telegram.service;

import com.github.mikesafonov.prometheus.alerts.starter.NotificationService;
import com.github.mikesafonov.prometheus.alerts.starter.dto.Alert;
import com.github.mikesafonov.prometheus.alerts.starter.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.service.message.MessageConverter;
import com.github.mikesafonov.prometheus.telegram.service.telegram.TelegramApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author MikeSafonov
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class TelegramNotificationService implements NotificationService {

    private final TelegramApiService telegramApiService;
    private final MessageConverter messageConverter;

    /**
     * Asynchronous notify about prometheus alerts
     *
     * @param notification alert
     */
    @Async
    @Override
    public void onNotification(AlertManagerNotification notification) {
        notification.getAlerts().forEach(alert -> notifyAlert(notification, alert));
    }

    private void notifyAlert(AlertManagerNotification notification, Alert alert) {
        String message = messageConverter.convert(notification, alert);
        log.debug(String.format("Telegram message:%s", message));
        telegramApiService.sendMessage(message);
    }

}
