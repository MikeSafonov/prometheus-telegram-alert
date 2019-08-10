package com.github.mikesafonov.prometheus.telegram.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author MikeSafonov
 */
@Data
public class AlertManagerNotification implements MapValue {

    private String version;
    private String receiver;
    private String status;
    private Map<String, String> groupLabels;
    private Map<String, String> commonLabels;
    private Map<String, String> commonAnnotations;
    private String externalURL;
    private List<Alert> alerts;

    public List<Alert> getAlerts() {
        if(alerts == null){
            return Collections.emptyList();
        }
        return alerts;
    }

    public Optional<String> getAlertName() {
        return getValue(commonLabels, "alertname");
    }

    public Optional<String> getDescription() {
        return getValue(commonAnnotations, "description");
    }

    public Optional<String> getGroupInstance() {
        return getValue(groupLabels, "instance");
    }

    public Optional<String> getSeverity() {
        return getValue(commonLabels, "severity");
    }

}
