package com.eventify.eventify.controller;

import com.eventify.eventify.dto.UserResponse;
import com.eventify.eventify.service.EventService;
import com.eventify.eventify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final EventService eventService;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}/role")
    public UserResponse updateUserRole(
            @PathVariable Long id,
            @RequestBody Map<String, String> roleUpdate) {
        String role = roleUpdate.get("role");
        return userService.changeUserRole(id, role);
    }

    @DeleteMapping("/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEventByAdmin(id);
    }
}
