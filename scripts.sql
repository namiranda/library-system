-- crea la base de datos
CREATE
DATABASE biblioteca;

-- crea la tabla libros
CREATE TABLE libros
(
    id_libro INT PRIMARY KEY AUTO_INCREMENT,
    titulo   VARCHAR(255),
    autor    VARCHAR(255),
    genero   VARCHAR(255),
    `año`    VARCHAR(255),
    estado   VARCHAR(255)
);

-- crea la tabla prestamos
CREATE TABLE prestamos
(
    id_prestamo      INT PRIMARY KEY AUTO_INCREMENT,
    id_libro         INT,
    estudiante       VARCHAR(255),
    fecha_prestamo   TIMESTAMP,
    fecha_devolucion TIMESTAMP,
    FOREIGN KEY (id_libro) REFERENCES libros (id_libro)
);