-- Eliminar las restricciones de clave foránea si existen
SET @schema_name = 'restdb';

-- Check and drop foreign keys
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.REFERENTIAL_CONSTRAINTS WHERE CONSTRAINT_NAME = 'pc_beer_id_fk' AND CONSTRAINT_SCHEMA = @schema_name) THEN
ALTER TABLE beer_category DROP FOREIGN KEY pc_beer_id_fk;
END IF;
IF EXISTS (SELECT 1 FROM information_schema.REFERENTIAL_CONSTRAINTS WHERE CONSTRAINT_NAME = 'bos_shipment_fk' AND CONSTRAINT_SCHEMA = @schema_name) THEN
ALTER TABLE beer_order DROP FOREIGN KEY bos_shipment_fk;
END IF;
IF EXISTS (SELECT 1 FROM information_schema.REFERENTIAL_CONSTRAINTS WHERE CONSTRAINT_NAME = 'fk_customer' AND CONSTRAINT_SCHEMA = @schema_name) THEN
ALTER TABLE beer_order DROP FOREIGN KEY fk_customer;
END IF;
IF EXISTS (SELECT 1 FROM information_schema.REFERENTIAL_CONSTRAINTS WHERE CONSTRAINT_NAME = 'fk_beer_order' AND CONSTRAINT_SCHEMA = @schema_name) THEN
ALTER TABLE beer_order_line DROP FOREIGN KEY fk_beer_order;
END IF;
IF EXISTS (SELECT 1 FROM information_schema.REFERENTIAL_CONSTRAINTS WHERE CONSTRAINT_NAME = 'fk_beer' AND CONSTRAINT_SCHEMA = @schema_name) THEN
ALTER TABLE beer_order_line DROP FOREIGN KEY fk_beer;
END IF;
IF EXISTS (SELECT 1 FROM information_schema.REFERENTIAL_CONSTRAINTS WHERE CONSTRAINT_NAME = 'fk_beer_order_shipment' AND CONSTRAINT_SCHEMA = @schema_name) THEN
ALTER TABLE beer_order_shipment DROP FOREIGN KEY fk_beer_order_shipment;
END IF;
END $$;

-- Luego eliminar las tablas si existen
DROP TABLE IF EXISTS beer_order_line;
DROP TABLE IF EXISTS beer_order_shipment;
DROP TABLE IF EXISTS beer_order;
DROP TABLE IF EXISTS beer_category;
DROP TABLE IF EXISTS beer;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS user;

-- Crear nuevamente las tablas
CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL
);

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

CREATE TABLE beer_category (
                               id VARCHAR(36) NOT NULL,
                               name VARCHAR(50) NOT NULL,
                               beer_id VARCHAR(36),
                               PRIMARY KEY (id),
                               CONSTRAINT pc_beer_id_fk FOREIGN KEY (beer_id) REFERENCES beer (id)
) ENGINE=InnoDB;

CREATE TABLE customer (
                          id VARCHAR(36) NOT NULL,
                          created_date DATETIME(6),
                          name VARCHAR(255),
                          update_date DATETIME(6),
                          version INTEGER,
                          PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE beer_order (
                            id VARCHAR(36) NOT NULL,
                            customer_id VARCHAR(36) NOT NULL,
                            created_date DATETIME(6),
                            update_date DATETIME(6),
                            version INTEGER,
                            PRIMARY KEY (id),
                            CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE=InnoDB;

CREATE TABLE beer_order_line (
                                 id VARCHAR(36) NOT NULL,
                                 beer_order_id VARCHAR(36) NOT NULL,
                                 beer_id VARCHAR(36) NOT NULL,
                                 quantity INTEGER NOT NULL,
                                 PRIMARY KEY (id),
                                 CONSTRAINT fk_beer_order FOREIGN KEY (beer_order_id) REFERENCES beer_order (id),
                                 CONSTRAINT fk_beer FOREIGN KEY (beer_id) REFERENCES beer (id)
) ENGINE=InnoDB;

CREATE TABLE beer_order_shipment (
                                     id VARCHAR(36) NOT NULL,
                                     beer_order_id VARCHAR(36) NOT NULL,
                                     shipment_date DATETIME(6),
                                     PRIMARY KEY (id),
                                     CONSTRAINT fk_beer_order_shipment FOREIGN KEY (beer_order_id) REFERENCES beer_order (id)
) ENGINE=InnoDB;
