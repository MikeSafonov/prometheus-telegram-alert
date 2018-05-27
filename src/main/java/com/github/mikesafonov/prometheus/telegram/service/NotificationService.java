package com.github.mikesafonov.prometheus.telegram.service;

import com.github.mikesafonov.prometheus.telegram.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.service.message.MessageConverter;
import com.github.mikesafonov.prometheus.telegram.service.telegram.TelegramApiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NotificationService {

    private final TelegramApiService telegramApiService;
    private final MessageConverter messageConverter;

    public NotificationService(TelegramApiService telegramApiService, MessageConverter messageConverter) {
        this.telegramApiService = telegramApiService;
        this.messageConverter = messageConverter;
    }

    public void sendNotification(AlertManagerNotification notification) {
        String message = messageConverter.convert(notification);
        log.debug(String.format("Telegram message:%s", message));
        telegramApiService.sendMessage(message);
    }
}
