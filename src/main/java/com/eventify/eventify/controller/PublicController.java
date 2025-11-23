package com.eventify.eventify.controller;

import com.eventify.eventify.dto.EventResponse;
import com.eventify.eventify.dto.UserRequest;
import com.eventify.eventify.dto.UserResponse;
import com.eventify.eventify.service.EventService;
import com.eventify.eventify.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;
    private final EventService eventService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/events")
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }
}
