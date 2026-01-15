CREATE TABLE posts
(
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT NOT NULL,
    content        TEXT,
    show_id        BIGINT NOT NULL,
    likes_count    BIGINT NOT NULL DEFAULT 0,
    comments_count BIGINT NOT NULL DEFAULT 0,
    shares_count   BIGINT NOT NULL DEFAULT 0,
    created_at     TIMESTAMP       DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP       DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE post_media
(
    id             BIGSERIAL PRIMARY KEY,
    post_id        BIGINT                                             NOT NULL,
    media_type     VARCHAR(20)                                        NOT NULL,
    media_order    INT                      DEFAULT 0                 NOT NULL,
    original_url   VARCHAR(500)                                       NOT NULL,
    compressed_url VARCHAR(500)                                       NOT NULL,
    hls_url        VARCHAR(500)                                       NOT NULL,
    duration       INT,
    created_at     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE post_likes
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT                                             NOT NULL,
    post_id    BIGINT                                             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE post_media
    ADD CONSTRAINT post_media_posts_id_fk FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE;

ALTER TABLE post_likes
    ADD CONSTRAINT post_likes_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE post_likes
    ADD CONSTRAINT post_likes_posts_id_fk FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE;
