package com.github.mikesafonov.prometheus.telegram.service.message;

import com.github.mikesafonov.prometheus.telegram.dto.Alert;
import com.github.mikesafonov.prometheus.telegram.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.dto.enums.AlertLevel;
import com.github.mikesafonov.prometheus.telegram.dto.enums.AlertStatus;
import com.vdurmont.emoji.EmojiManager;

import java.util.Optional;

import static com.github.mikesafonov.prometheus.telegram.utils.TelegramMarkdownFormatter.addParagraph;

/**
 * Base implementation of {@link MessageConverter}
 *
 * @author MikeSafonov
 */
public class SimpleMessageConverter implements MessageConverter {

    /**
     * Build notification message from alerts annotations (title, description, resolved_title, resolved_description)
     *
     * @param notification base prometheus notification
     * @param alert        prometheus alert
     * @return notification message
     */
    @Override
    public Optional<String> convert(AlertManagerNotification notification, Alert alert) {

        Optional<AlertLevel> level = alert.getLevel();
        String emojiByLevel = emojiByLevel(level.orElse(AlertLevel.WARNING));

        if (alert.getStatus() == AlertStatus.firing) {
            String title = alert.getTitle().orElse("Title");
            String description = alert.getDescription().orElse("Description");
            String message = String.format(emojiByLevel + " " + title, "UTF-8");
            message = addParagraph(message);
            message = addParagraph(message + description);
            return Optional.of(message);
        } else {
            String title = alert.getResolvedTitle().orElse("Title");
            String description = alert.getResolvedDescription().orElse("Description");
            String message = String.format(emojiByLevel + " " + EmojiManager.getForAlias("white_check_mark").getUnicode() + title, "UTF-8");
            message = addParagraph(message);
            message = addParagraph(message + description);
            return Optional.of(message);
        }
    }

    private String emojiByLevel(AlertLevel alertLevel) {
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
