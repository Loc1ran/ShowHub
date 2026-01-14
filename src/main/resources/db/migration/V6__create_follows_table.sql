CREATE TABLE follows
(
    id           BIGSERIAL PRIMARY KEY,
    follower_id  BIGINT                              NOT NULL,
    following_id BIGINT                              NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    UNIQUE (follower_id, following_id),
    CHECK (follower_id != following_id)
);

ALTER TABLE follows
    ADD CONSTRAINT follows_follower_id_fk FOREIGN KEY (follower_id) REFERENCES users (id) ON DELETE CASCADE;
ALTER TABLE follows
    ADD CONSTRAINT follows_following_id_fk FOREIGN KEY (following_id) REFERENCES users (id) ON DELETE CASCADE;