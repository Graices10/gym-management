CREATE TABLE clients (
       id BIGSERIAL PRIMARY KEY,
       first_name VARCHAR(100) NOT NULL,
       last_name VARCHAR(100) NOT NULL,
       phone VARCHAR(20),
       email VARCHAR(150),
       created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

COMMENT ON TABLE clients IS 'Клиенты тренажерного зала';
COMMENT ON COLUMN clients.id IS 'Уникальный идентификатор клиента';
COMMENT ON COLUMN clients.first_name IS 'Имя';
COMMENT ON COLUMN clients.last_name IS 'Фамилия';
COMMENT ON COLUMN clients.phone IS 'Телефон';
COMMENT ON COLUMN clients.email IS 'Email';
COMMENT ON COLUMN clients.created_at IS 'Дата и время регистрации клиента';