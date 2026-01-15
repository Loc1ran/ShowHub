CREATE TABLE notifications
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT                                             NOT NULL,
    actor_id    BIGINT,
    entity_type VARCHAR(50)                                        NOT NULL,
    entity_id   BIGINT,
    message     TEXT                                               NOT NULL,
    is_read     BOOLEAN                  DEFAULT FALSE             NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE notifications
    ADD CONSTRAINT notifications_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE notifications
    ADD CONSTRAINT notifications_actor_id_fk FOREIGN KEY (actor_id) REFERENCES users (id) ON DELETE CASCADE;