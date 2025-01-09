package org.chatop.chatopback.dto.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.Message}
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MessageRequestDto(

        @NotBlank
        @Size(max = 2000)
        String message,

        @NotNull
        @Positive
        Integer rentalId

  ) implements Serializable {}