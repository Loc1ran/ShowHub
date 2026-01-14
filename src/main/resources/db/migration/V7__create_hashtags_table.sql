CREATE TABLE hashtags
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(50),
    usage_count  INT       DEFAULT 0                 NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_used_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE post_hashtags
(
    post_id    BIGINT NOT NULL,
    hashtag_id BIGINT NOT NULL
);

ALTER TABLE post_hashtags
    ADD CONSTRAINT post_hashtags_post_id_fk FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE;

ALTER TABLE post_hashtags
    ADD CONSTRAINT post_hashtags_hashtag_id_fk FOREIGN KEY (hashtag_id) REFERENCES hashtags (id) ON DELETE CASCADE;