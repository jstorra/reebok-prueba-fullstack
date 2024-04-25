DROP DATABASE IF EXISTS reebokdb;
CREATE DATABASE reebokdb;
USE reebokdb;

CREATE TABLE tipo_producto(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO tipo_producto (nombre) VALUES
('Zapatillas'),
('Camisetas'),
('Pantalones');

CREATE TABLE color_producto(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO color_producto (nombre) VALUES
('Rojo'),
('Azul'),
('Negro');

CREATE TABLE talla_producto(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO talla_producto (nombre) VALUES
('S'),
('M'),
('L');

CREATE TABLE forma_pago(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO forma_pago (nombre) VALUES
('Tarjeta de crédito'),
('Transferencia bancaria'),
('PayPal');

CREATE TABLE usuario(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    tipo ENUM('cliente', 'admin') NOT NULL
);

INSERT INTO usuario (nombre, correo, contraseña, tipo) VALUES
('Juan Pérez', 'juan@example.com', 'contraseña123', 'cliente'),
('María López', 'maria@example.com', 'clave456', 'cliente'),
('Admin', 'admin@example.com', 'admin123', 'admin');

CREATE TABLE producto(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_tipo_fk INT NOT NULL,
    id_color_fk INT NOT NULL,
    id_talla_fk INT NOT NULL,
    FOREIGN KEY (id_tipo_fk) REFERENCES tipo_producto(id),
	FOREIGN KEY (id_color_fk) REFERENCES color_producto(id),
	FOREIGN KEY (id_talla_fk) REFERENCES talla_producto(id)
);

INSERT INTO producto (nombre, precio, id_tipo_fk, id_color_fk, id_talla_fk) VALUES
('Zapatillas Rojas', 79.99, 1, 1, 1),
('Camiseta Azul', 29.99, 2, 2, 2),
('Pantalones Negros', 49.99, 3, 3, 3);

CREATE TABLE inventario(
	id INT PRIMARY KEY AUTO_INCREMENT,
    cantidad INT NOT NULL,
    id_producto_fk INT NOT NULL,
    FOREIGN KEY (id_producto_fk) REFERENCES producto(id)
);

INSERT INTO inventario (cantidad, id_producto_fk) VALUES
(50, 1),
(30, 2),
(20, 3);

CREATE TABLE venta(
	id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    unidades INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    id_producto_fk INT NOT NULL,
	id_cliente_fk INT NOT NULL,
    id_forma_pago_fk INT NOT NULL,
	FOREIGN KEY (id_producto_fk) REFERENCES producto(id),
	FOREIGN KEY (id_cliente_fk) REFERENCES usuario(id),
	FOREIGN KEY (id_forma_pago_fk) REFERENCES forma_pago(id)
);

INSERT INTO venta (fecha, unidades, total, id_producto_fk, id_cliente_fk, id_forma_pago_fk) VALUES
('2024-04-24', 1, 79.99, 1, 1, 1),
('2024-04-25', 2, 59.98, 2, 2, 2),
('2024-04-26', 1, 49.99, 3, 1, 3);

-- TRIGGER
DELIMITER //
CREATE TRIGGER actualizar_nueva_cantidad_producto
AFTER INSERT ON venta
FOR EACH ROW
BEGIN
	INSERT INTO inventario (cantidad, id_producto_fk) VALUES (NEW.cantidad * -1, NEW.id_producto_fk);
END;
//
DELIMITER ;
