package com.eventify.eventify.repository;

import com.eventify.eventify.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
