package org.chatop.chatopback.dto.rental;

import jakarta.validation.constraints.*;
import org.chatop.chatopback.validation.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.Rental}
 */
public record CreateRentalRequestDto(

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

        @ImageFile
        @NotNull
        MultipartFile picture,

        @NotBlank
        @Size(max = 2000)
        String description,

//        @NotNull  // TODO: check this line when security is implemented
        @Positive
        @Max(Integer.MAX_VALUE)
        Integer ownerId

) implements Serializable {}