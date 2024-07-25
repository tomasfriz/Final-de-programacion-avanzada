-- Creación base de datos
CREATE DATABASE biblioteca_digital;

-- usar base de datos
USE biblioteca_digital;

-- Creación tabla usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

-- Creación tabla libros
CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL
);

-- Creación tabla prestamos
CREATE TABLE prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_libro INT,
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_libro) REFERENCES libros(id)
);

-- Insertar usuarios
INSERT INTO usuarios (nombre, email) VALUES ('Tomas', 'tomas@gmail.com');
INSERT INTO usuarios (nombre, email) VALUES ('Belen', 'belen@gmail.com');
INSERT INTO usuarios (nombre, email) VALUES ('Gabriela', 'gabriela@gmail.com');

-- Insertar libros
INSERT INTO libros (titulo, autor, genero) VALUES ('El Quijote', 'Miguel de Cervantes', 'Novela');
INSERT INTO libros (titulo, autor, genero) VALUES ('Cien Años de Soledad', 'Gabriel Garcia Marquez', 'Realismo Mágico');
INSERT INTO libros (titulo, autor, genero) VALUES ('La Odisea', 'Homero', 'Épica');

-- Insertar préstamos
INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (1, 1, '2024-01-01', NULL);
INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (2, 2, '2024-02-01', '2024-02-15');
INSERT INTO prestamos (id_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (3, 3, '2024-03-01', NULL);

-- Verificar usuarios
SELECT * FROM usuarios;

-- Verificar libros
SELECT * FROM libros;

-- Verificar préstamos
SELECT * FROM prestamos;
