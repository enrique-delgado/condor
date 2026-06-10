-- Migración solicitada: cambio de tipo de datos de status
ALTER TABLE accounts MODIFY status VARCHAR(30) NOT NULL;
ALTER TABLE transactions MODIFY status VARCHAR(30) NOT NULL;
