package com.github.mikesafonov.prometheus.telegram.service;

import com.github.mikesafonov.prometheus.telegram.config.TelegramProperties;
import com.github.mikesafonov.prometheus.telegram.service.telegram.TelegramApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.mockito.Mockito.*;

class TelegramApiServiceTest {
    private TelegramProperties telegramProperties;
    private RestTemplate restTemplate;
    private TelegramApiService telegramApiService;

    private String authToken;
    private String chatId;

    @BeforeEach
    void setUp(){
        authToken = "asdasdads";
        chatId = "31112";
        telegramProperties = mock(TelegramProperties.class);
        when(telegramProperties.getAuthToken()).thenReturn(authToken);
        when(telegramProperties.getChatId()).thenReturn(chatId);
        restTemplate = mock(RestTemplate.class);
        telegramApiService = new TelegramApiService(telegramProperties, restTemplate);
    }

    @Test
    void shouldSendMessage(){
        String message = "Hello alert";

        String expectedUrl = format("https://api.telegram.org/bot%s/sendmessage?chat_id={chat_id}&text={text}&parse_mode={parse_mode}" +
                "&disable_notification={disable_notification}", telegramProperties.getAuthToken());
        Map<String, String> expectedParams = new HashMap<>();
        expectedParams.put("chat_id", telegramProperties.getChatId());
        expectedParams.put("text", message);
        expectedParams.put("parse_mode", "Markdown");
        expectedParams.put("disable_notification", "false");

        telegramApiService.sendMessage(message);

        verify(restTemplate).getForObject(expectedUrl, Void.class, expectedParams);
    }
}
