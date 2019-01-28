package com.github.mikesafonov.prometheus.telegram.dto.enums;


public enum AlertLevel {
    INFO("info"), WARNING("warning"), CRITICAL("critical");

    private String str;

    private AlertLevel(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
