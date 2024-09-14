package com.maintenance.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.maintenance.management.model.Role;
import com.maintenance.management.model.User;
import com.maintenance.management.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject the PasswordEncoder

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {  // Only insert if no users exist
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));  // Encode the password
            admin.setRole(Role.ADMIN);

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));  // Encode the password
            user.setRole(Role.USER);

            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
