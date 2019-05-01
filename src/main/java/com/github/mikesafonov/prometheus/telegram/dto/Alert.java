package com.github.mikesafonov.prometheus.telegram.dto;

import com.github.mikesafonov.prometheus.telegram.dto.enums.AlertLevel;
import com.github.mikesafonov.prometheus.telegram.dto.enums.AlertStatus;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author MikeSafonov
 */
@Data
public class Alert extends MapValue {

    private Map<String, String> labels;
    private Map<String, String> annotations;
    private ZonedDateTime startsAt;
    private ZonedDateTime endsAt;
    private String generatorURL;
    private AlertStatus status;

    public Optional<String> getLabelByName(String name) {
        return getValue(labels, name);
    }

    public Optional<String> getAnnotationByName(String name) {
        return getValue(annotations, name);
    }

    public Optional<String> getAlertName() {
        return getLabelByName("alertname");
    }

    public Optional<String> getJob() {
        return getLabelByName("job");
    }

    public Optional<String> getProject() {
        return getLabelByName("project");
    }

    public Optional<String> getService() {
        return getLabelByName("service");
    }

    public Optional<AlertLevel> getLevel() {
        Optional<String> level = getAnnotationByName("level");
        if (level.isPresent()) {
            switch (level.get()) {
                case "info":
                    return Optional.of(AlertLevel.INFO);
                case "warning":
                    return Optional.of(AlertLevel.WARNING);
                case "critical":
                    return Optional.of(AlertLevel.CRITICAL);
            }
        }
        return Optional.empty();
    }

    public Optional<String> getTitle() {
        return getAnnotationByName("title");
    }


    public Optional<String> getDescription() {
        return getAnnotationByName("description");
    }

    public Optional<String> getResolvedTitle() {
        return getAnnotationByName("resolved_title");
    }

    public Optional<String> getResolvedDescription() {
        return getAnnotationByName("resolved_description");
    }
}