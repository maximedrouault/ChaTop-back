package org.chatop.chatopback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.auth.AuthResponseDto;
import org.chatop.chatopback.dto.auth.LoginRequestDto;
import org.chatop.chatopback.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/me")
    public ResponseEntity<Void> authCheck() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> getAuthToken(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.getAuthToken(loginRequestDto));
    }
}
