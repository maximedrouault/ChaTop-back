package org.chatop.chatopback.dto.rental;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.chatop.chatopback.entity.Rental}
 */
public record RentalsDto(

        List<RentalResponseDto> rentals

) implements Serializable {}