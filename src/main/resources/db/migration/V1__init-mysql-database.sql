-- Eliminar las restricciones de clave foránea primero
SET @foreign_key_exists = (
    SELECT COUNT(*)
    FROM information_schema.REFERENTIAL_CONSTRAINTS
    WHERE CONSTRAINT_SCHEMA = 'restdb' AND CONSTRAINT_NAME = 'beer_order_line_ibfk_2'
);

SET @foreign_key_exists2 = (
    SELECT COUNT(*)
    FROM information_schema.REFERENTIAL_CONSTRAINTS
    WHERE CONSTRAINT_SCHEMA = 'restdb' AND CONSTRAINT_NAME = 'beer_order_ibfk_1'
);

-- Si la restricción de clave foránea existe, eliminarla
SET @query1 = IF(@foreign_key_exists > 0, 'ALTER TABLE beer_order_line DROP FOREIGN KEY beer_order_line_ibfk_2', 'SELECT 1');
SET @query2 = IF(@foreign_key_exists2 > 0, 'ALTER TABLE beer_order DROP FOREIGN KEY beer_order_ibfk_1', 'SELECT 1');

PREPARE stmt1 FROM @query1;
EXECUTE stmt1;
DEALLOCATE PREPARE stmt1;

PREPARE stmt2 FROM @query2;
EXECUTE stmt2;
DEALLOCATE PREPARE stmt2;

-- Luego eliminar las tablas en el orden correcto
DROP TABLE IF EXISTS beer_order_line;
DROP TABLE IF EXISTS beer_order;
DROP TABLE IF EXISTS beer;
DROP TABLE IF EXISTS customer;

-- V1__create_user_table.sql
CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL
);


-- Crear nuevamente las tablas
CREATE TABLE beer (
                      id VARCHAR(36) NOT NULL,
                      beer_name VARCHAR(50) NOT NULL,
                      beer_style TINYINT NOT NULL,
                      created_date DATETIME(6),
                      price DECIMAL(38,2) NOT NULL,
                      quantity_on_hand INTEGER,
                      upc VARCHAR(255) NOT NULL,
                      update_date DATETIME(6),
                      version INTEGER,
                      PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE customer (
                          id VARCHAR(36) NOT NULL,
                          created_date DATETIME(6),
                          name VARCHAR(255),
                          update_date DATETIME(6),
                          version INTEGER,
                          PRIMARY KEY (id)
) ENGINE=InnoDB;
