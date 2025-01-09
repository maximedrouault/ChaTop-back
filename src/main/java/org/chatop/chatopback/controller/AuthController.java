package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.auth.AuthResponseDto;
import org.chatop.chatopback.dto.auth.LoginRequestDto;
import org.chatop.chatopback.dto.user.UserResponseDto;
import org.chatop.chatopback.entity.User;
import org.chatop.chatopback.mapper.UserMapper;
import org.chatop.chatopback.service.AuthService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;


    @GetMapping("/me")
    @SecurityRequirement(name = "tokenAuth")
    @Operation(summary = "Get authenticated user information", responses = {
            @ApiResponse(responseCode = "200", description = "User is authenticated"),
            @ApiResponse(responseCode = "401", description = "User is not authenticated", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<UserResponseDto> getAuthenticatedUserInfo() {
        User authenticatedUser = authService.getAuthenticatedUser();
        UserResponseDto userResponseDto = userMapper.toDto(authenticatedUser);

        return ResponseEntity.ok().body(userResponseDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Get an authentication token", parameters = {
            @Parameter(name = "loginRequestDto", description = "The login request with User credentials", required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginRequestDto.class)))
            }, responses = {
            @ApiResponse(responseCode = "200", description = "Token generated", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuthResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<AuthResponseDto> getAuthToken(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.getAuthToken(loginRequestDto));
    }
}
