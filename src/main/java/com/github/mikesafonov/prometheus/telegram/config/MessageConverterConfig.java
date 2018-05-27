package com.github.mikesafonov.prometheus.telegram.config;

import com.github.mikesafonov.prometheus.telegram.service.message.MessageConverter;
import com.github.mikesafonov.prometheus.telegram.service.message.SimpleMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConverterConfig {

    @Bean
    @ConditionalOnMissingBean(MessageConverter.class)
    public MessageConverter simpleMessageConverter() {
        return new SimpleMessageConverter();
    }
}
