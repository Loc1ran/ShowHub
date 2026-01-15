CREATE TABLE users
(
    id              BIGSERIAL PRIMARY KEY                              NOT NULL,
    name            VARCHAR(255)                                       NOT NULL,
    username        VARCHAR(50) UNIQUE                                 NOT NULL,
    email           VARCHAR(255)                                       NOT NULL,
    password        VARCHAR(255)                                       NOT NULL,
    profile_pic_url VARCHAR(500)                                       NOT NULL,
    bio             TEXT,
    is_verified     BOOLEAN                  DEFAULT FALSE             NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

