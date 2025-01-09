package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.auth.AuthResponseDto;
import org.chatop.chatopback.dto.auth.LoginRequestDto;
import org.chatop.chatopback.dto.auth.RegisterRequestDto;
import org.chatop.chatopback.entity.User;
import org.chatop.chatopback.exception.UserAlreadyExistsException;
import org.chatop.chatopback.exception.UserNotFoundException;
import org.chatop.chatopback.mapper.UserMapper;
import org.chatop.chatopback.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    public AuthResponseDto getAuthToken(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.email(),
                        loginRequestDto.password()
                )
        );

        String token = jwtService.generateToken(authentication);

        return new AuthResponseDto(token);
    }

    public User getAuthenticatedUser() {
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findUserByEmail(authenticatedUserName)
                .orElseThrow(() -> new UserNotFoundException(authenticatedUserName));
    }

    public AuthResponseDto registerUser(RegisterRequestDto registerRequestDto) {
        return Optional.of(registerRequestDto.email())
                .filter(email -> !userRepository.existsUserByEmail(email))
                .map(email -> {
                    User user = userMapper.toEntity(registerRequestDto);
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userRepository.save(user);

                    return new LoginRequestDto(email, registerRequestDto.password());
                })
                .map(this::getAuthToken)
                .orElseThrow(() -> new UserAlreadyExistsException(registerRequestDto.email()));
    }
}
