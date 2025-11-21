package com.eventify.eventify.service;

import com.eventify.eventify.dto.RegistrationResponse;
import com.eventify.eventify.entity.Event;
import com.eventify.eventify.entity.Registration;
import com.eventify.eventify.entity.User;
import com.eventify.eventify.entity.enums.RegistrationStatus;
import com.eventify.eventify.exception.EventNotFoundException;
import com.eventify.eventify.mapper.RegistrationMapper;
import com.eventify.eventify.repository.EventRepository;
import com.eventify.eventify.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final RegistrationMapper registrationMapper;

    @Transactional
    public RegistrationResponse registerUserToEvent(Long eventId, User user) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found: " + eventId));

        if (registrationRepository.existsByUserIdAndEventId(user.getId(), eventId)) {
            throw new RuntimeException("User already registered for this event");
        }

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);
        registration.setRegisteredAt(LocalDateTime.now());
        registration.setStatus(RegistrationStatus.PENDING);

        Registration saved = registrationRepository.save(registration);
        return registrationMapper.toResponse(saved);
    }

    public List<RegistrationResponse> getUserRegistrations(Long userId) {
        return registrationRepository.findByUserId(userId).stream()
                .map(registrationMapper::toResponse)
                .toList();
    }
}
