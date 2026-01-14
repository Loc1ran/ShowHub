CREATE TABLE video_reels
(
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT                              NOT NULL,
    show_id        BIGINT                              NOT NULL,
    title          VARCHAR(255)                        NOT NULL,
    description    TEXT,
    video_url      VARCHAR(500)                        NOT NULL,
    thumbnail_url  VARCHAR(500),
    duration       INT                                 NOT NULL,
    view_count     BIGINT    DEFAULT 0                 NOT NULL,
    likes_count    INT       DEFAULT 0                 NOT NULL,
    comments_count INT       DEFAULT 0                 NOT NULL,
    shares_count   INT       DEFAULT 0                 NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);