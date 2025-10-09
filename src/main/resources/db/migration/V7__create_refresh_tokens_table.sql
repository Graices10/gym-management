CREATE TABLE refresh_tokens (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT false,

    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

COMMENT ON TABLE refresh_tokens IS 'Токены обновления сессии (для получения нового access token без повторного ввода пароля)';
COMMENT ON COLUMN refresh_tokens.id IS 'Уникальный идентификатор записи токена';
COMMENT ON COLUMN refresh_tokens.token IS 'Уникальная строка refresh-токена (UUID)';
COMMENT ON COLUMN refresh_tokens.user_id IS 'Ссылка на пользователя, которому принадлежит токен';
COMMENT ON COLUMN refresh_tokens.revoked IS 'Отозван ли токен (true = недействителен, например, после logout)';