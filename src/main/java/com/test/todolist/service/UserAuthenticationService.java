package com.test.todolist.service;

import com.test.todolist.persistence.entity.User;
import com.test.todolist.persistence.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService passwordService;

    public void createUser(User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        user.setPassword(SecurityService.hashPassword(user.getPassword()));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(value -> passwordService.matches(password, value.getPassword())).orElse(false);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }
}
