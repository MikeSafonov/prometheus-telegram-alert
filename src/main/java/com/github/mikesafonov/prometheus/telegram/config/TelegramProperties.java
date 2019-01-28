package com.github.mikesafonov.prometheus.telegram.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author MikeSafonov
 */
@Data
@Configuration
@ConfigurationProperties(value = "prometheus.telegram")
public class TelegramProperties {

    private String chatId;
    private String authToken;
    private TelegramProxyProperties proxy;

}
