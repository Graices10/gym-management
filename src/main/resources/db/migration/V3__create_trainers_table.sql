CREATE TABLE trainers(
       id BIGSERIAL PRIMARY KEY,
       first_name VARCHAR(100) NOT NULL,
       last_name VARCHAR(100) NOT NULL,
       specialization VARCHAR(100) NOT NULL,
       phone VARCHAR(20),
       email VARCHAR (150)
);

COMMENT ON TABLE trainers IS 'Тренировочный состав';
COMMENT ON COLUMN trainers.first_name IS 'Имя';
COMMENT ON COLUMN trainers.last_name IS 'Фамилия';
COMMENT ON COLUMN trainers.specialization IS 'Специализация';
COMMENT ON COLUMN trainers.phone IS 'Телефон';
COMMENT ON COLUMN trainers.email IS 'Email';
