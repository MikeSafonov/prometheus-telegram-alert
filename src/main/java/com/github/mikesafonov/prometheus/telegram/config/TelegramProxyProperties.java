package com.github.mikesafonov.prometheus.telegram.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties
public class TelegramProxyProperties {

    private boolean enable;
    private String host;
    private int port;

}
