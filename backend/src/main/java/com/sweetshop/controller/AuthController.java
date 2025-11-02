package com.sweetshop.controller;

import com.sweetshop.dto.AuthRequest;
import com.sweetshop.dto.AuthResponse;
import com.sweetshop.model.User;
import com.sweetshop.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final String jwtSecret;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtSecret = System.getProperty("jwt.secret", "MySuperSecretKeyForJWTsChangeMe");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req){
        if(userRepository.findByUsername(req.username()).isPresent()){
            return ResponseEntity.badRequest().body("username taken");
        }
        User u = new User(req.username(), req.password(), "USER");
        userRepository.save(u);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req){
        var userOpt = userRepository.findByUsername(req.username());
        if(userOpt.isEmpty()) return ResponseEntity.status(401).body("invalid");
        var user = userOpt.get();
        if(!user.getPassword().equals(req.password())) return ResponseEntity.status(401).body("invalid");
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L*60*60*8))
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
