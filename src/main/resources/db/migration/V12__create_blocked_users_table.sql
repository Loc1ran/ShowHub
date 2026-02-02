CREATE TABLE blocked_users
(
    blocker_id BIGINT                                             NOT NULL,
    blocked_id BIGINT                                             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE blocked_users
    ADD CONSTRAINT blocked_users_blocker_id_fk FOREIGN KEY (blocker_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE blocked_users
    ADD CONSTRAINT blocked_users_blocked_id_fk FOREIGN KEY (blocked_id) REFERENCES users (id) ON DELETE CASCADE;
