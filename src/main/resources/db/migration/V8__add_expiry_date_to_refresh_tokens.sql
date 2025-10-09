ALTER TABLE refresh_tokens
ADD COLUMN expiry_date TIMESTAMP WITHOUT TIME ZONE;

UPDATE refresh_tokens
SET expiry_date = NOW() + INTERVAL '30 days'
WHERE expiry_date IS NULL;

ALTER TABLE refresh_tokens
ALTER COLUMN expiry_date SET NOT NULL;

COMMENT ON COLUMN refresh_tokens.expiry_date IS 'Время истечения срока действия refresh-токена';