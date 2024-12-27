package org.chatop.chatopback.dto;

import java.io.Serializable;

/**
 * DTO for {@link org.chatop.chatopback.entity.Message}
 */
public record MessageDto(

        String message,
        Integer userId,
        Integer rentalId

  ) implements Serializable {
}