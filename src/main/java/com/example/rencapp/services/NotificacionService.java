package com.example.rencapp.services;

import com.example.rencapp.models.Agendamiento;
import com.example.rencapp.models.Notificacion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface NotificacionService {

    Notificacion findById(Long id);
   /* List<Notificacion> findAllByUsuarioId(Long idUsuario);*/
    Notificacion crearNotificacion(String email, String numeroTelefono, String departamento, Map<String, String> datos, Agendamiento agendamiento);


}
