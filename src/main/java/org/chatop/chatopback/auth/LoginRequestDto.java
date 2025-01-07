package org.chatop.chatopback.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.User}
 */
public record LoginRequestDto(

        @Size(min = 3, max = 255)
        @Email
        @NotBlank
        String email,

        @Size(min = 3, max = 255)
        @NotBlank
        String password

) implements Serializable {}