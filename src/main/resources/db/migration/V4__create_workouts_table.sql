CREATE TABLE workouts(
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       description TEXT,
       start_time TIMESTAMP NOT NULL,
       end_time TIMESTAMP NOT NULL,
       max_participants INTEGER NOT NULL,
       trainer_id BIGINT NOT NULL REFERENCES trainers(id) ON DELETE RESTRICT
);

COMMENT ON TABLE workouts IS 'Тренировки';
COMMENT ON COLUMN workouts.id IS 'Уникальный идентификатор тренировки';
COMMENT ON COLUMN workouts.name IS 'Название тренировки';
COMMENT ON COLUMN workouts.description IS 'Описание тренировки';
COMMENT ON COLUMN workouts.start_time IS 'Дата и время начала тренировки';
COMMENT ON COLUMN workouts.end_time IS 'Дата и время окончания тренировки';
COMMENT ON COLUMN workouts.max_participants IS 'Максимальное количество участников';
COMMENT ON COLUMN workouts.trainer_id IS 'Ссылка на тренера, проводящего тренировку';

