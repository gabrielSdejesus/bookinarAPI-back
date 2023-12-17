-- Create to table 'role'
CREATE TABLE sys.role (
                             id INTEGER AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL UNIQUE,
                            date_register DATETIME NOT NULL,
                            date_modify DATETIME,
                            status INTEGER NOT NULL CHECK (status IN (0, 1)),
                            PRIMARY KEY (id)
);
-- Create to table 'user'
CREATE TABLE sys.user (
                            id INTEGER AUTO_INCREMENT,
                            login varchar(255) NOT NULL UNIQUE,
                            password varchar(255) NOT NULL,
                            date_register DATETIME NOT NULL,
                            date_modify DATETIME,
                            status INTEGER NOT NULL CHECK (status IN (0, 1)),
                            PRIMARY KEY (id)
);

-- Create to table 'user_x_role'
CREATE TABLE sys.user_x_role (
                            id_role INTEGER NOT NULL,
                            id_user INTEGER NOT NULL,
                            PRIMARY KEY (id_role, id_user),
                            CONSTRAINT fk_id_user FOREIGN KEY (id_user) REFERENCES user(id),
                            CONSTRAINT fk_id_role FOREIGN KEY (id_role) REFERENCES role(id)
);

-- Create to table 'product'
CREATE TABLE sys.product (
                            id INTEGER AUTO_INCREMENT,
                            type VARCHAR(15) NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            price DECIMAL(10,3) NOT NULL,
                            description VARCHAR(255),
                            amount_stock INTEGER(10) NOT NULL DEFAULT 0,
                            rating INTEGER(1) NOT NULL CHECK (rating >= 0 AND rating <= 4) DEFAULT 0,
                            date_register DATETIME NOT NULL,
                            date_modify DATETIME,
                            status INTEGER NOT NULL CHECK (status IN (0, 1)),
                            PRIMARY KEY (id)
);

-- Create to table 'photos_product'
CREATE TABLE sys.photos_product (
                            id INTEGER AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            type VARCHAR(255) NOT NULL,
                            data MEDIUMBLOB NOT NULL,
                            id_product INTEGER,
                            date_register DATETIME NOT NULL,
                            date_modify DATETIME,
                            status INTEGER NOT NULL CHECK (status IN (0, 1)),
                            PRIMARY KEY (id),
                            CONSTRAINT fk_id_product FOREIGN KEY (id_product) REFERENCES product(id)
);