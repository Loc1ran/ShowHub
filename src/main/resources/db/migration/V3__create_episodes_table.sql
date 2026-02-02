CREATE TABLE episodes
(
    id             SERIAL PRIMARY KEY,
    show_id        BIGINT                                         NOT NULL,
    season         INT DEFAULT 1 CHECK ( season >= 1 AND season <= 100 ) NOT NULL,
    episode_number INT                                            NOT NULL,
    title          VARCHAR(50)                                    NOT NULL,
    air_date       DATE                                           NOT NULL,
    description    TEXT
);