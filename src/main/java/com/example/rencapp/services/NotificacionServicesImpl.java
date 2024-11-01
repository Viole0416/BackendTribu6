package com.example.rencapp.services;
import com.example.rencapp.email.EmailService;
import com.example.rencapp.models.Agendamiento;
import com.example.rencapp.models.Notificacion;
import com.example.rencapp.repositories.NotificacionRepository;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class NotificacionServicesImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;// crear metodo y dentro restcontroller que reciba la notificación del mensaje
    @Autowired
    private EmailService emailService;
    @Autowired
    private MensajesService mensajesService;

    public Notificacion crearNotificacion(String email, String numeroTelefono, String departamento, Map<String, String> datos, Agendamiento agendamiento) {

        /***NOTIFICACIÓN DENTRO DEL SISTEMA***/
        //Creamos nuestra nueva instancia de la notificación con patrón Builder()
        Notificacion notificacion = Notificacion.builder()
                .contenidoMensaje("Tu hora ha sido agendada para el día" + agendamiento.getFecha())// metodo que crea las notificaciones y manda un mensaje dentro el sistema notificando.
                .leida(false) // esto cambia cuando se abre la notificación
                .build();
        notificacionRepository.save(notificacion);

        /***NOTIFICACIÓN POR MAIL***/
        //Validamos que el usuario tenga un mail asociado (Que sea distinto de null o que no esté vacío
        if (email != null && !email.isEmpty()) {
            try {
                //Llamamos al método enviar correo del service de email
                emailService.enviarCorreo(email, "Nueva Notificación", departamento, datos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /***NOTIFICACIÓN POR SMS***/
        //Validamos que el usuario tenga un mensaje asociado (Que sea distinto de null o que no esté vacío
        if (numeroTelefono != null && !numeroTelefono.isEmpty()) {
            try {
                //Llamamos al método enviar correo del service de email
                mensajesService.enviarMensaje(numeroTelefono, "Tu hora ha sido agendada para el día" + agendamiento.getFecha());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return notificacion;
    }

    @Override
    public Notificacion findById(Long id) {
        return notificacionRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public List<Notificacion> findAllByUsuarioId(Long idUsuario) { // este permite buscar el ID del usuario y mostrar todas las notificaciones que se han hecho.
        return notificacionRepository.findNotificacionsByUsuarioId(idUsuario);    // Implementación de notificacionService.}

    }

}

