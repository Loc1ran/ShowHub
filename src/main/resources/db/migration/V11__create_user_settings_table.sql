CREATE TABLE user_settings
(
    user_id             BIGINT                                             NOT NULL,
    profile_visibility  VARCHAR(20)              DEFAULT 'public'          NOT NULL,
    post_visibility     VARCHAR(20)              DEFAULT 'public'          NOT NULL,
    allow_messages      BOOLEAN                  DEFAULT TRUE              NOT NULL,
    email_notifications BOOLEAN                  DEFAULT TRUE              NOT NULL,
    push_notifications  BOOLEAN                  DEFAULT TRUE              NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE user_settings
    ADD CONSTRAINT user_settings_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;