package org.chatop.chatopback.dto.rental;

import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.Rental}
 */
public record UpdateRentalRequestDto(

        @NotBlank
        @Size(max = 255)
        String name,

        @NotNull
        @Positive
        @Digits(integer = 10, fraction = 0)
        Long surface,

        @NotNull
        @PositiveOrZero
        @Digits(integer = 10, fraction = 0)
        Long price,

        @NotBlank
        @Size(max = 2000)
        String description

) implements Serializable {}