package org.chatop.chatopback.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.User}
 */
public record RegisterRequestDto(

        @Size(min = 3, max = 255)
        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 3, max = 255)
        String name,

        @Size(min = 3, max = 255)
        @NotBlank
        String password

) implements Serializable {}