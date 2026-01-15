CREATE TABLE ratings
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT                                             NOT NULL,
    show_id       BIGINT                                             NOT NULL,
    rating        DECIMAL(3, 2)            DEFAULT 0.0 CHECK (rating >= 1 AND <= 5),
    review        TEXT                                               NOT NULL,
    helpful_count BIGINT                   DEFAULT 0                 NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE ratings
    ADD CONSTRAINT ratings_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;
ALTER TABLE ratings
    ADD CONSTRAINT ratings_show_id_fk FOREIGN KEY (show_id) REFERENCES shows (id) ON DELETE CASCADE;