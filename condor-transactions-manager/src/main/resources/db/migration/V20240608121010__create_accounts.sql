CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL,
    balance DOUBLE NOT NULL,
    password VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_accounts_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Inserts iniciales
INSERT INTO accounts (customer_id, type, balance, password, status)
VALUES (1, 'SAVINGS', 500.00, 'securePass123', 'ACTIVE');

INSERT INTO accounts (customer_id, type, balance, password, status)
VALUES (2, 'CHECKING', 100.00, 'pass456', 'ACTIVE');

INSERT INTO accounts (customer_id, type, balance, password, status)
VALUES (3, 'SAVINGS', 1000.00, 'secret789', 'ACTIVE');
