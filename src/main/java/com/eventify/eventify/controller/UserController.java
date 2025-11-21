package com.eventify.eventify.controller;

import com.eventify.eventify.dto.RegistrationResponse;
import com.eventify.eventify.dto.UserResponse;
import com.eventify.eventify.security.CustomUserDetails;
import com.eventify.eventify.service.RegistrationService;
import com.eventify.eventify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/profile")
    public UserResponse getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.getUserById(userDetails.getUser().getId());
    }

    @PostMapping("/events/{id}/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponse registerToEvent(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return registrationService.registerUserToEvent(id, userDetails.getUser());
    }

    @GetMapping("/registrations")
    public List<RegistrationResponse> getUserRegistrations(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return registrationService.getUserRegistrations(userDetails.getUser().getId());
    }
}
