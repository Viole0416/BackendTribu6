package com.example.rencapp.api;

import com.example.rencapp.models.Agendamiento;
import com.example.rencapp.models.Notificacion;
import com.example.rencapp.services.NotificacionServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Buscar notificacion especifica por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontro la notificacion del usuario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))),
            @ApiResponse(responseCode = "404", description = "No se encontro la notificación  del usuario")
    })
    @GetMapping("/notificacion")
    public ResponseEntity <?> findNotificacionById(@RequestParam Long id) {
       Notificacion notificacionSeleccionada = notificacionServicesImpl.findById(id);
        if (notificacionSeleccionada == null) {
            return new ResponseEntity<>("No se encontró la notificación con el ID especificado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notificacionSeleccionada, HttpStatus.OK);
    }

@Operation(summary = "Buscar todas las notificaciones asociadas a un usuario en especifico")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se encontraron todas las notificaciones del usuario", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Notificacion.class))),
        @ApiResponse(responseCode = "404", description = "El usuario no tiene notificaciones")
})
    @GetMapping("/usuarioId")
    public ResponseEntity<?>  findNotificacionAllByUsuarioId(@RequestParam Long id) {
        List<Notificacion> notificaciones = notificacionServicesImpl.findAllByUsuarioId(id);
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);  }

@Operation(summary = "Crear y enviar una notificación personalizada")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha creado y enviado la notificación al usuario", content = @Content(mediaType = "application/json",schema = @Schema(implementation = Notificacion.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
})

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
