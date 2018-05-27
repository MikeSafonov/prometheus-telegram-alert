package com.github.mikesafonov.prometheus.telegram.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class AlertManagerNotification {

    private String version;
    private String receiver;
    private String status;
    private Map<String, String> groupLabels;
    private Map<String, String> commonLabels;
    private Map<String, String> commonAnnotations;
    private String externalURL;
    private List<Alert> alerts;

    public Optional<String> getAlertName() {
        if (commonLabels != null) {
            return Optional.ofNullable(commonLabels.get("alertname"));
        }
        return Optional.empty();
    }

    public Optional<String> getDescription() {
        if (commonAnnotations != null) {
            return Optional.ofNullable(commonAnnotations.get("description"));
        }
        return Optional.empty();
    }
}
