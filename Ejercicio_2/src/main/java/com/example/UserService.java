package com.example;

import org.springframework.stereotype.Component;

@Component

public class UserService {

    NotificationService notificacion;

    public UserService(NotificationService notificacion) {
        this.notificacion = notificacion;
    }
}
