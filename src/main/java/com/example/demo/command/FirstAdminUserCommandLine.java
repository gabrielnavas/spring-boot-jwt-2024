package com.example.demo.command;

import com.example.demo.domain.user.Role;
import com.example.demo.domain.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Component
public class FirstAdminUserCommandLine implements CommandLineRunner {

    @Value("${api.admin.email}")
    private String adminEmail;

    @Value("${api.admin.password}")
    private String adminPassword;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        Optional<User> user = userRepository.findByEmail(adminEmail);
        if (user.isEmpty()) {
            createAdminUser();
        }
    }

    private void createAdminUser() {
        final User userAdmin = createUser();
        userRepository.save(userAdmin);
    }


    private User createUser() {
        User user = new User();
        user.setEmail(adminEmail);
        user.setPassword(passwordEncoder.encode(adminPassword));
        user.setRoles(new HashSet<>(Collections.singletonList(Role.ADMIN)));

        final String adminName = adminEmail.split("@")[0];
        user.setName(adminName);

        return user;
    }
}
