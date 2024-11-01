package com.example.rencapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
    @Table(name = "usuarios")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    @DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
    public class Usuario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty("_id")
        private Long id;

        private String nombre;

        private String apellido;


        @Column(nullable = false, unique = true)//Restricciones para la columna, no permitir null y sólo registros únicos
        private String email;

        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)//Anotación para indicar que el valor del atributo va a tomarse de una enumeración
        @Column(nullable = false)
        private TipoUsuario tipo;

        //Atributos de auditoría, me permiten conocer la fecha de creación y de la última edición
        @CreationTimestamp//Anotación para generar el almacenamiento de la fecha de creación
        private LocalDateTime createdAt;

        @UpdateTimestamp//Anotación para generar el almacenamiento de la fecha de actualización
        private LocalDateTime updatedAt;

        @OneToMany(mappedBy = "usuarioId")
        @JsonIgnore
        List<Notificacion> notificaciones;

}
