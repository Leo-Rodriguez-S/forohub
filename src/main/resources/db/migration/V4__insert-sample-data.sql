-- Insertar datos de ejemplo
INSERT INTO usuarios (login, clave, nombre, email) VALUES
('admin', '$2a$10$DXi8XCG4cIfdDdtgo7K0secO4TlULU0twEA5h8lk4miPjOrbepN0G', 'Administrador', 'admin@forohub.com'),
('usuario1', '$2a$10$DXi8XCG4cIfdDdtgo7K0secO4TlULU0twEA5h8lk4miPjOrbepN0G', 'Usuario Uno', 'usuario1@email.com');

INSERT INTO cursos (nombre, categoria) VALUES
('Spring Boot', 'Programación'),
('Java Básico', 'Programación'),
('Python', 'Programación'),
('HTML y CSS', 'Front-end');

INSERT INTO topicos (titulo, mensaje, fecha_creacion, status, autor_id, curso_id) VALUES
('¿Cómo configurar Spring Boot?', '¿Alguien puede ayudarme con la configuración inicial de Spring Boot?', NOW(), 'NO_RESPONDIDO', 1, 1),
('Error en compilación Java', 'Tengo un error de compilación en mi proyecto Java, ¿qué puede ser?', NOW(), 'NO_RESPONDIDO', 2, 2),
('Mejores prácticas en Spring', '¿Cuáles son las mejores prácticas para desarrollar con Spring Boot?', NOW(), 'NO_RESPONDIDO', 1, 1);