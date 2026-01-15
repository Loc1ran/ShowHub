CREATE TABLE reports
(
    id                   BIGSERIAL PRIMARY KEY,
    reporter_id          BIGINT                                             NOT NULL,
    reported_entity_type VARCHAR(50)                                        NOT NULL,
    reported_entity_id   BIGINT                                             NOT NULL,
    reported_user_id     BIGINT                                             NOT NULL,
    reason               VARCHAR(50)                                        NOT NULL,
    description          TEXT,
    status               VARCHAR(20)              DEFAULT 'PENDING'         NOT NULL,
    moderator_id         BIGINT,
    moderator_notes      TEXT,
    created_at           TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    resolved_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE reports
    ADD CONSTRAINT reports_reporter_id_fk FOREIGN KEY (reporter_id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE reports
    ADD CONSTRAINT reports_reported_user_id_fk FOREIGN KEY (reported_user_id) REFERENCES users (id) ON DELETE NO ACTION;

ALTER TABLE reports
    ADD CONSTRAINT reports_moderator_id_fk FOREIGN KEY (moderator_id) REFERENCES users (id) ON DELETE SET NULL;