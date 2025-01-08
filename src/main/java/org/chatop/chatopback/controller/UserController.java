package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.user.UserResponseDto;
import org.chatop.chatopback.service.UserService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@SecurityRequirement(name = "tokenAuth")
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{id}")
    @Operation(summary = "Get a User by ID", parameters = {
            @Parameter(name = "id", description = "ID of the user to be retrieved", required = true, example = "1"),
    }, responses = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
