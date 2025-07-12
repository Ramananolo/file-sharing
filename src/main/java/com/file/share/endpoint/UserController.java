package com.file.share.endpoint;

import com.file.share.repository.model.User;
import com.file.share.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/register")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> RegisterUser(@RequestBody User user){
        try {
            userService.register(user);
            return ResponseEntity.ok("User register successfully");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error: "+ e.getMessage());
        }
    }
}
