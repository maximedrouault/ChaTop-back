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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling authentication-related operations.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param loginRequestDto the login request with the user's email and password
     * @return the authentication response data transfer object containing the JWT token
     */
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

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the authenticated user
     * @throws UserNotFoundException if the authenticated user is not found
     */
    public User getAuthenticatedUser(JwtAuthenticationToken jwtAuthenticationToken) {
        String authenticatedUserName = jwtAuthenticationToken.getName();

        return userRepository.findUserByEmail(authenticatedUserName)
                .orElseThrow(() -> new UserNotFoundException(authenticatedUserName));
    }

    /**
     * Registers a new user and returns an authentication response.
     *
     * @param registerRequestDto the registration request with the user's details
     * @return the authentication response data transfer object containing the JWT token
     * @throws UserAlreadyExistsException if a user with the given email already exists
     */
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
