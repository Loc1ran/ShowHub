CREATE TABLE email_verification_tokens
(
    id         BIGSERIAL PRIMARY KEY,
    token      VARCHAR(255)                                       NOT NULL UNIQUE,
    user_id    BIGINT                                             NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE                           NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE email_verification_tokens
    ADD CONSTRAINT email_verification_tokens_user_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;