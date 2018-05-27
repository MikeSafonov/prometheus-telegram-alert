package com.github.mikesafonov.prometheus.telegram.service.telegram;

import com.github.mikesafonov.prometheus.telegram.config.TelegramProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

@Service
@Log4j2
public class TelegramApiService {

    private final TelegramProperties telegramProperties;
    private final RestTemplate restTemplate;
    private final String targetUrl;


    public TelegramApiService(TelegramProperties telegramProperties) {
        this.telegramProperties = telegramProperties;
        this.restTemplate = createRestTemplate(telegramProperties);
        targetUrl = format("https://api.telegram.org/bot%s/sendmessage?chat_id={chat_id}&text={text}&parse_mode={parse_mode}" +
                "&disable_notification={disable_notification}", telegramProperties.getAuthToken());
    }


    public void sendMessage(String message){
        restTemplate.getForObject(targetUrl, Void.class, params(message));
    }

    private RestTemplate createRestTemplate(TelegramProperties telegramProperties) {
        if (telegramProperties.getProxy() != null && telegramProperties.getProxy().isEnable()) {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            InetSocketAddress address = new InetSocketAddress(telegramProperties.getProxy().getHost(),
                    telegramProperties.getProxy().getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            factory.setProxy(proxy);
            return new RestTemplate(factory);
        } else {
            return new RestTemplate();
        }
    }

    private Map<String, String> params(String message) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("chat_id", telegramProperties.getChatId());
        stringMap.put("text", message);
        stringMap.put("parse_mode", "Markdown");
        stringMap.put("disable_notification", "false");
        return stringMap;
    }

}
