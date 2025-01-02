package org.chatop.chatopback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.chatop.chatopback.entity.Rental}
 */
public record RentalResponseDto(

        Integer id,
        String name,
        BigDecimal surface,
        BigDecimal price,
        URL picture,
        String description,
        Integer ownerId,

        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDateTime updatedAt

) implements Serializable {}