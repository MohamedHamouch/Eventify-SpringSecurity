package com.eventify.eventify.service;

import com.eventify.eventify.dto.EventRequest;
import com.eventify.eventify.dto.EventResponse;
import com.eventify.eventify.entity.Event;
import com.eventify.eventify.entity.User;
import com.eventify.eventify.exception.EventNotFoundException;
import com.eventify.eventify.exception.UnauthorizedActionException;
import com.eventify.eventify.mapper.EventMapper;
import com.eventify.eventify.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Transactional
    public EventResponse createEvent(EventRequest request, User organizer) {
        Event event = eventMapper.toEntity(request);
        event.setOrganizer(organizer);

        Event saved = eventRepository.save(event);
        return eventMapper.toResponse(saved);
    }

    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found: " + id));
        return eventMapper.toResponse(event);
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    @Transactional
    public EventResponse updateEvent(Long id, EventRequest request, User currentUser) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found: " + id));

        if (!event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new UnauthorizedActionException("You are not the organizer of this event");
        }

        eventMapper.updateEntity(request, event);
        Event updated = eventRepository.save(event);
        return eventMapper.toResponse(updated);
    }

    @Transactional
    public void deleteEvent(Long id, User currentUser) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found: " + id));

        if (!event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new UnauthorizedActionException("You are not the organizer of this event");
        }

        eventRepository.delete(event);
    }

    @Transactional
    public void deleteEventByAdmin(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException("Event not found: " + id);
        }
        eventRepository.deleteById(id);
    }
}
