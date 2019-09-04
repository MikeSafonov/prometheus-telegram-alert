package com.github.mikesafonov.prometheus.telegram.service.message;

import com.github.mikesafonov.prometheus.alerts.starter.dto.enums.AlertLevel;
import com.vdurmont.emoji.EmojiManager;
import org.springframework.stereotype.Service;

@Service
public class EmojiService {
    public String emojiByLevel(AlertLevel alertLevel) {
        switch (alertLevel) {
            case INFO: {
                return EmojiManager.getForAlias("information_source").getUnicode();
            }
            case CRITICAL: {
                return EmojiManager.getForAlias("fire").getUnicode();
            }
            case WARNING: {
                return EmojiManager.getForAlias("warning").getUnicode();
            }
            default: {
                return EmojiManager.getForAlias("warning").getUnicode();
            }
        }
    }
}
