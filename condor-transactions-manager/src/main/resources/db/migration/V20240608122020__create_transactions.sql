CREATE TABLE transactions (
    id VARCHAR(36) PRIMARY KEY,
    account_id BIGINT,
    amount DOUBLE NOT NULL,
    balance DOUBLE,
    requested_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    error_message VARCHAR(255) NOT NULL,
    status VARCHAR(10) NOT NULL,
    CONSTRAINT fk_transactions_account FOREIGN KEY (account_id) REFERENCES accounts(id)
);

-- Inserts iniciales
INSERT INTO transactions (id, account_id, amount, balance, requested_at, created_at, error_message, status)
VALUES ('tx-1001', 1, 150.00, 650.00, NOW(), NOW(), 'NONE', 'OK');

INSERT INTO transactions (id, account_id, amount, balance, requested_at, created_at, error_message, status)
VALUES ('tx-1002', 2, 200.00, 300.00, NOW(), NOW(), 'NONE', 'OK');

INSERT INTO transactions (id, account_id, amount, balance, requested_at, created_at, error_message, status)
VALUES ('tx-1003', 3, 50.00, 950.00, NOW(), NOW(), 'NONE', 'OK');
