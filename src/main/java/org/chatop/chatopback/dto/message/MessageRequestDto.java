package org.chatop.chatopback.dto.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.Message}
 */
public record MessageRequestDto(

        @NotBlank
        @Size(max = 2000)
        String message,

        @NotNull
        @Positive
        Integer rentalId

  ) implements Serializable {}