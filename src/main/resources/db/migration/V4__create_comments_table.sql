CREATE TABLE comments
(
    id                BIGSERIAL PRIMARY KEY,
    post_id           BIGINT                              NOT NULL,
    user_id           BIGINT                              NOT NULL,
    content           TEXT                                NOT NULL,
    parent_comment_id BIGINT                              NOT NULL,
    likes_count       BIGINT    DEFAULT 0                 NOT NULL,
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE comment_likes
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT                              NOT NULL,
    comment_id BIGINT                              NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE comments
    ADD CONSTRAINT comments_post_id_fk FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE;

ALTER TABLE comments
    ADD CONSTRAINT comments_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE comment_likes
    ADD CONSTRAINT comment_likes_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE comment_likes
    ADD CONSTRAINT comment_likes_comments_id_fk FOREIGN KEY (comment_id) REFERENCES comments (id) ON DELETE CASCADE;