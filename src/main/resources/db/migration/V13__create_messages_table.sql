CREATE TABLE messages
(
    id          BIGSERIAL PRIMARY KEY,
    sender_id   BIGINT                                             NOT NULL,
    receiver_id BIGINT                                             NOT NULL,
    content     TEXT,
    is_read     BOOLEAN                  DEFAULT FALSE             NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE messages
    ADD CONSTRAINT messages_sender_id_fk FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE messages
    ADD CONSTRAINT messages_receiver_id_fk FOREIGN KEY (receiver_id) REFERENCES users (id) ON DELETE NO ACTION;
