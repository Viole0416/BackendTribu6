package com.example.rencapp.controllers;

import com.example.rencapp.services.NotificacionServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionServicesImpl notificacionServicesImpl;

    @GetMapping("/index")
    public String indexNotificaciones() {
        return "indexnotificaciones.html";
    }
}
