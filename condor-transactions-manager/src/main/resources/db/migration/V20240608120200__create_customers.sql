CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender CHAR(1) NOT NULL,
    birth_date DATE NOT NULL,
    identification VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL
);

-- Inserts iniciales
INSERT INTO customers (first_name, last_name, gender, birth_date, identification, address, phone, password, active)
VALUES ('Marianela', 'Perez', 'F', '1995-05-20', 'ID12345', 'Amazonas y NNUU', '5678', 'password123', true);

INSERT INTO customers (first_name, last_name, gender, birth_date, identification, address, phone, password, active)
VALUES ('Juan', 'Osorio', 'M', '1990-06-13', 'ID67890', '13 junio y Equinoccial', '1245', 'password456', true);

INSERT INTO customers (first_name, last_name, gender, birth_date, identification, address, phone, password, active)
VALUES ('Jose', 'Lema', 'M', '1985-01-01', 'ID99999', 'Otavalo sn y principal', '1234', 'secret123', true);
