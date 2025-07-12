package com.file.share.service;

import com.file.share.repository.UserRepository;
import com.file.share.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
