package com.file.share.service;

import com.file.share.repository.UserRepository;
import com.file.share.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(User user){
        if (userRepository.existByEmail(user.getEmail()))
            throw new RuntimeException("Email already exist");
        userRepository.save(user);
    }
}
