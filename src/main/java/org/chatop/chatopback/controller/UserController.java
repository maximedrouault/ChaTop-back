package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.UserDto;
import org.chatop.chatopback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{id}")
    @Operation(summary = "Get a user by id", parameters = {
            @Parameter(name = "id", description = "ID of the user to be retrieved", required = true, example = "1"),
    }, responses = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(example = """
                        {
                            "status": 404,
                            "path": "/user/0",
                            "error": "Not Found",
                            "timestamp": "2024-12-25T13:49:26.1972492",
                            "message": "User not found"
                        }
                    """)))
    })
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
