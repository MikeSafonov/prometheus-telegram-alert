package com.github.mikesafonov.prometheus.telegram.service.message;


import com.github.mikesafonov.prometheus.alerts.starter.dto.Alert;
import com.github.mikesafonov.prometheus.alerts.starter.dto.AlertManagerNotification;

/**
 * Converts {@link AlertManagerNotification} to String message
 *
 * @author MikeSafonov
 */
public interface MessageConverter {

    /**
     * Converting {@link Alert} to markdown based telegram message.
     *
     * @param notification base prometheus notification
     * @param alert        prometheus alert
     * @return markdown based telegram message
     */
    String convert(AlertManagerNotification notification, Alert alert);
}
