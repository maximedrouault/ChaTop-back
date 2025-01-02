package org.chatop.chatopback.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link org.chatop.chatopback.entity.Rental}
 */
public record CreateRentalRequestDto(

        String name,
        BigDecimal surface,
        BigDecimal price,
        MultipartFile picture,
        String description,
        Integer ownerId

) implements Serializable {}