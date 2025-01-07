package org.chatop.chatopback.controller;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;


    @GetMapping("/login")
    public String login() {
        return "logged in";
    }

    @PostMapping("/login")
    public Map<String, String> getToken(Authentication authentication) {

        return Map.of("token", jwtService.generateToken(authentication)); // TODO: change to DTO
    }
}
