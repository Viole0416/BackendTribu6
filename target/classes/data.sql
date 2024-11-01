INSERT IGNORE INTO notificaciones (notificacion_id, contenido_mensaje, estado, created_at, updated_at)
VALUES
    (1, "Hola", b'1', NOW(), NOW()),
    (2, "Hola, soy prueba", b'0', NOW(), NOW());

INSERT IGNORE INTO usuarios (nombre, apellido, email, password, tipo_usuario)
VALUES
    ("Diego", "Palacios", "d@gmail.com", "123", "VECINO"),
    ("Juana", "Muñoz", "j@gmail.com", "123" , "FUNCIONARIO");
