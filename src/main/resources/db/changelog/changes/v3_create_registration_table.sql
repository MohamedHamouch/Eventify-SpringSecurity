--liquibase formatted sql

--changeset eventify:3
CREATE TABLE registration (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registered_at TIMESTAMP,
    status VARCHAR(50),
    event_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_registration_event FOREIGN KEY (event_id) REFERENCES event(id),
    CONSTRAINT fk_registration_user FOREIGN KEY (user_id) REFERENCES user(id)
);
