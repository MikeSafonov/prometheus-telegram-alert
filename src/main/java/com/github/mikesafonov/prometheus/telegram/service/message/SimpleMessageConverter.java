package com.github.mikesafonov.prometheus.telegram.service.message;

import com.github.mikesafonov.prometheus.alerts.starter.dto.Alert;
import com.github.mikesafonov.prometheus.alerts.starter.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.alerts.starter.dto.enums.AlertLevel;
import com.github.mikesafonov.prometheus.alerts.starter.dto.enums.AlertStatus;
import com.vdurmont.emoji.EmojiManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.github.mikesafonov.prometheus.telegram.utils.TelegramMarkdownFormatter.addParagraph;

/**
 * Base implementation of {@link MessageConverter}
 *
 * @author MikeSafonov
 */
@RequiredArgsConstructor
public class SimpleMessageConverter implements MessageConverter {
    private final EmojiService emojiService;

    /**
     * Build notification message from alerts annotations (title, description, resolved_title, resolved_description)
     *
     * @param notification base prometheus notification
     * @param alert        prometheus alert
     * @return notification message
     */
    @Override
    public String convert(AlertManagerNotification notification, Alert alert) {

        Optional<AlertLevel> level = alert.getLevel();
        String emojiByLevel = emojiService.emojiByLevel(level.orElse(AlertLevel.WARNING));

        String message;
        String description;
        if (alert.getStatus() == AlertStatus.firing) {
            String title = alert.getTitle().orElse("Title");
            description = alert.getDescription().orElse("Description");
            message = String.format(emojiByLevel + " " + title, "UTF-8");
        } else {
            String title = alert.getResolvedTitle().orElse("Title");
            description = alert.getResolvedDescription().orElse("Description");
            message = String.format(emojiByLevel + " " + EmojiManager.getForAlias("white_check_mark").getUnicode() + title, "UTF-8");
        }
        message = addParagraph(message);
        message = addParagraph(message + description);
        return message;
    }
}
