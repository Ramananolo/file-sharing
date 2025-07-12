package com.file.share.endpoint;

import com.file.share.config.jwt.JwtUtil;
import com.file.share.endpoint.authRequestBody.AuthRequest;
import com.file.share.repository.UserRepository;
import com.file.share.repository.model.User;
import com.file.share.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.email());
        if (user != null && authService.authenticate(request.email(),request.password())) {
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        }

        return ResponseEntity.status(401).body("Email or password invalid");
    }


    public record AuthResponse(String token) {}

}
