CREATE TABLE bookings(
       id BIGSERIAL PRIMARY KEY,
       client_id BIGINT NOT NULL REFERENCES clients(id) ON DELETE CASCADE,
       workout_id BIGINT NOT NULL REFERENCES workouts(id) ON DELETE CASCADE,
       booked_at TIMESTAMP NOT NULL DEFAULT NOW(),
       attended BOOLEAN NOT NULL DEFAULT FALSE
);

COMMENT ON TABLE bookings IS 'Записи клиентов на тренировки';
COMMENT ON COLUMN bookings.id IS 'Уникальный идентификатор записи';
COMMENT ON COLUMN bookings.client_id IS 'Ссылка на клиента';
COMMENT ON COLUMN bookings.workout_id IS 'Ссылка на тренировку';
COMMENT ON COLUMN bookings.booked_at IS 'Дата и время создания записи';
COMMENT ON COLUMN bookings.attended IS 'Фактическое посещение тренировки (true/false)';