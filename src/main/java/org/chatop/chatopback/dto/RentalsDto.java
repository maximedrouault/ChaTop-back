package org.chatop.chatopback.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.chatop.chatopback.entity.Rental}
 */
public record RentalsDto(

        List<RentalDto> rentals

        ) implements Serializable {
}