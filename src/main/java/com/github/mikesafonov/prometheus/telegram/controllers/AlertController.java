package com.github.mikesafonov.prometheus.telegram.controllers;


import com.github.mikesafonov.prometheus.telegram.dto.AlertManagerNotification;
import com.github.mikesafonov.prometheus.telegram.service.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class AlertController {

    private final NotificationService notificationService;

    public AlertController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/alert")
    public void alert(@RequestBody AlertManagerNotification managerNotification) {
        log.debug(managerNotification);
        notificationService.sendNotification(managerNotification);
    }
}
