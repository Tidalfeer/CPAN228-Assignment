package com.codecrew.config;

import com.codecrew.model.User;
import com.codecrew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createUserIfNotExists("admin@domain.com", "Administrator", "Admin@123", "ADMIN");
        createUserIfNotExists("staff@domain.com", "Staff Member", "Staff@123", "STAFF");
        createUserIfNotExists("user@domain.com", "Regular User", "User@123", "USER");
    } // This method runs on application startup to ensure default accounts are created if they don't exist

    private void createUserIfNotExists(String email, String name, String rawPassword, String role) {
        if(userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);  // set role directly since it's a known valid value
            userRepository.save(user);
            System.out.println("Account created: " + email + " / " + rawPassword + " (" + role + ")");
        } else {
            System.out.println("Account already exists: " + email);
        }
    }
}