--liquibase formatted sql

--changeset eventify:4
ALTER TABLE event ADD organizer_id BIGINT NOT NULL;
ALTER TABLE event ADD CONSTRAINT fk_event_organizer FOREIGN KEY (organizer_id) REFERENCES user(id);
