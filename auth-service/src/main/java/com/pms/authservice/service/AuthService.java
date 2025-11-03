package com.pms.authservice.service;

import java.util.Optional;

import com.pms.authservice.dto.LoginRequestDTO;
import com.pms.authservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        Optional<User> user=userService.findByEmail(loginRequestDTO.getEmail());
    }
}
