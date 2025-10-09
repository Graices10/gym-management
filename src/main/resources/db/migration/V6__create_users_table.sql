CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true,
    client_id BIGINT,
    trainer_id BIGINT,

    CONSTRAINT fk_user_client FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE SET NULL,
    CONSTRAINT fk_user_trainer FOREIGN KEY (trainer_id) REFERENCES trainers(id) ON DELETE SET NULL,
    CONSTRAINT chk_user_has_role CHECK (
        (role = 'CLIENT' AND client_id IS NOT NULL AND trainer_id IS NULL) OR
        (role = 'TRAINER' AND trainer_id IS NOT NULL AND client_id IS NULL) OR
        (role = 'ADMIN' AND client_id IS NULL AND trainer_id IS NULL)
    )
);

COMMENT ON TABLE users IS 'Пользователи системы (клиенты, тренеры, администраторы)';
COMMENT ON COLUMN users.id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN users.email IS 'Электронная почта (уникальная, используется для входа)';
COMMENT ON COLUMN users.password IS 'Хэш пароля (BCrypt)';
COMMENT ON COLUMN users.role IS 'Роль пользователя: CLIENT, TRAINER, ADMIN';
COMMENT ON COLUMN users.enabled IS 'Активен ли аккаунт (для блокировки/подтверждения email)';
COMMENT ON COLUMN users.client_id IS 'Ссылка на профиль клиента (заполняется, если роль = CLIENT)';
COMMENT ON COLUMN users.trainer_id IS 'Ссылка на профиль тренера (заполняется, если роль = TRAINER)';