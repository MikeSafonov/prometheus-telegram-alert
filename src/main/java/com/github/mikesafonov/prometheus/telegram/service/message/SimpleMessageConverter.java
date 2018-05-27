package com.github.mikesafonov.prometheus.telegram.service.message;

import com.github.mikesafonov.prometheus.telegram.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.service.telegram.TelegramMessageFormatter;

public class SimpleMessageConverter implements MessageConverter {
    @Override
    public String convert(AlertManagerNotification notification) {
        String message = notification.getAlertName().orElse("UnknownAlert");
        message = TelegramMessageFormatter.makeBold(message);
        message = TelegramMessageFormatter.addParagraph(message);
        message = message + notification.getDescription().orElse("UnknownDescription");
        return message;
    }
}
