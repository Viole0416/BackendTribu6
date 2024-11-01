package com.example.rencapp.api;

import com.example.rencapp.models.Agendamiento;
import com.example.rencapp.models.Notificacion;
import com.example.rencapp.services.NotificacionServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin("*")
public class NotificacionRestController {

    @Autowired
    private NotificacionServicesImpl notificacionServicesImpl;

    @GetMapping("/notificacion")
    public ResponseEntity <?> findNotificacionById(@RequestParam Long id) {
       Notificacion notificacionSeleccionada = notificacionServicesImpl.findById(id);
        if (notificacionSeleccionada == null) {
            return new ResponseEntity<>("No se encontró la notificación con el ID especificado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notificacionSeleccionada, HttpStatus.OK);
    }
    /*@GetMapping("/lista")
    public ResponseEntity<List<Notificacion>> findAllNotificacion() {
        List<Notificacion> listaNotificaciones = notificacionServicesImpl.findAll();
        return new ResponseEntity<>(listaNotificaciones, HttpStatus.OK);
    } */

    @GetMapping("/usuarioId")
    public ResponseEntity<?>  findNotificacionAllByUsuarioId(@RequestParam Long id) {
        List<Notificacion> notificaciones = notificacionServicesImpl.findAllByUsuarioId(id);
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);  }

    @GetMapping("/enviar/{email}/{numeroTelefonico}")
    public void enviarNotificacion(@PathVariable String email ,@PathVariable String numeroTelefonico, @RequestParam String departamento) {

        Agendamiento agendamiento = Agendamiento.builder().
                fecha(LocalDate.parse("2024-10-31") ).
                estado(Agendamiento.estadoAgendamiento.AGENDADA).  // Aqui se construye instancia de agendamiento
                build();
        Map<String, String>datos = new HashMap<>();
        datos.put("nombre" ,"Juan");
        datos.put("apellido" ,"    Perez");
        datos.put("idsolicitud","001");
        datos.put("fecha", agendamiento.getFecha().toString());// esto mismo los hacemos a los demás campos  -PRUEBA
        datos.put("horacita","10:00");
        datos.put("tiposervicios","Poda de arboles");


        notificacionServicesImpl.crearNotificacion(email, numeroTelefonico,departamento,datos, agendamiento);   // g5
    }

}
