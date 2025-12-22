package com.eventify.eventify.config;

import com.eventify.eventify.entity.User;
import com.eventify.eventify.entity.enums.Role;
import com.eventify.eventify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist
        if (userRepository.count() == 0) {
            // Create Admin Account
            User admin = new User();
            admin.setName("Admin Account");
            admin.setEmail("admin@eventify.com");
            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);

            // Create Organizer Account
            User organizer = new User();
            organizer.setName("Organizer Account");
            organizer.setEmail("organizer@eventify.com");
            organizer.setPassword(passwordEncoder.encode("password123"));
            organizer.setRole(Role.ROLE_ORGANIZER);
            userRepository.save(organizer);

            // Create User Account
            User user = new User();
            user.setName("User Account");
            user.setEmail("user@eventify.com");
            user.setPassword(passwordEncoder.encode("password123"));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);

            System.out.println("✓ Initial users created successfully!");
        }
    }
}
