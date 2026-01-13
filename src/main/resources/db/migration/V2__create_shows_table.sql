CREATE TABLE shows
(
    id           BIGSERIAL PRIMARY KEY                   NOT NULL,
    title        VARCHAR(255)                            NOT NULL,
    description  TEXT,
    poster_url   VARCHAR(500),
    banner_url   VARCHAR(500),
    release_date DATE                                    NOT NULL,
    end_date     DATE,
    status       VARCHAR(20)                             NOT NULL DEFAULT 'UPCOMING',
    avg_rating   DECIMAL(3, 2) DEFAULT 0.0 CHECK (avg_rating >= 1 AND <= 5),
    rating_count INT           DEFAULT 0                 NOT NULL,
    country      VARCHAR(100)                            NOT NULL,
    created_at   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE genres
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE show_genres
(
    show_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL
);

ALTER TABLE show_genres
    ADD CONSTRAINT genres_show_id_fk FOREIGN KEY (show_id) REFERENCES shows (id) on DELETE CASCADE;

ALTER TABLE show_genres
    ADD CONSTRAINT genres_id_fk FOREIGN KEY (genre_id) REFERENCES genres (id) on DELETE CASCADE;
