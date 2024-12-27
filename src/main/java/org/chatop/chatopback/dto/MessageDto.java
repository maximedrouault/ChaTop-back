package org.chatop.chatopback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.Message}
 */
public record MessageDto(

        @NotBlank
        String message,

        @NotNull
        @Positive
        Integer userId,

        @NotNull
        @Positive
        Integer rentalId

  ) implements Serializable {}