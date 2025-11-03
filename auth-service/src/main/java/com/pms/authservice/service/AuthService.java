package com.pms.authservice.service;

import java.util.Optional;

import com.pms.authservice.dto.LoginRequestDTO;
import com.pms.authservice.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    //if password matches, it is encodedgit add .
    git commit -m ""
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        Optional<User> user=userService
                .findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(),u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(),u.getRole()));

        return token;


    }
}
