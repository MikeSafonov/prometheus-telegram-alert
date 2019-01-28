package com.github.mikesafonov.prometheus.telegram.service.message;

import com.github.mikesafonov.prometheus.telegram.dto.AlertManagerNotification;


/**
 * Converts {@link AlertManagerNotification} to String message
 *
 * @author MikeSafonov
 */
public interface MessageConverter {

    String convert(AlertManagerNotification notification);
}
