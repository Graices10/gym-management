CREATE TABLE memberships (
       id BIGSERIAL PRIMARY KEY,
       client_id BIGINT NOT NULL REFERENCES clients(id) ON DELETE CASCADE,
       type VARCHAR(50) NOT NULL,
       start_date DATE NOT NULL,
       end_date DATE NOT NULL,
       price NUMERIC(10,2) NOT NULL,
       is_active BOOLEAN NOT NULL DEFAULT TRUE
);

COMMENT ON TABLE memberships IS 'Абонементы клиентов';
COMMENT ON COLUMN memberships.id IS 'Уникальный идентификатор абонемента';
COMMENT ON COLUMN memberships.client_id IS 'Ссылка на клиента (владельца абонемента)';
COMMENT ON COLUMN memberships.type IS 'Тип абонемента (например, MONTHLY, YEARLY)';
COMMENT ON COLUMN memberships.start_date IS 'Дата начала действия';
COMMENT ON COLUMN memberships.end_date IS 'Дата окончания действия';
COMMENT ON COLUMN memberships.price IS 'Стоимость абонемента';
COMMENT ON COLUMN memberships.is_active IS 'Активен ли абонемент';