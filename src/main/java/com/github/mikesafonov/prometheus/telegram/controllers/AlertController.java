package com.github.mikesafonov.prometheus.telegram.controllers;


import com.github.mikesafonov.prometheus.telegram.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MikeSafonov
 */
@Log4j2
@RestController
@RequiredArgsConstructor
public class AlertController {
    private final NotificationService notificationService;

    @PostMapping("/alert")
    public void alert(@RequestBody AlertManagerNotification managerNotification) {
        log.debug("Notification: {}",managerNotification);
        notificationService.sendNotification(managerNotification);
    }
}
