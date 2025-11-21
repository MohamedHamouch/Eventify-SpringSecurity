package com.eventify.eventify.controller;

import com.eventify.eventify.dto.EventRequest;
import com.eventify.eventify.dto.EventResponse;
import com.eventify.eventify.security.CustomUserDetails;
import com.eventify.eventify.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
public class OrganizerController {

    private final EventService eventService;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(
            @Valid @RequestBody EventRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return eventService.createEvent(request, userDetails.getUser());
    }

    @PutMapping("/events/{id}")
    public EventResponse updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return eventService.updateEvent(id, request, userDetails.getUser());
    }

    @DeleteMapping("/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        eventService.deleteEvent(id, userDetails.getUser());
    }
}
