package org.chatop.chatopback.exception;

import lombok.Getter;

@Getter
public class RentalNotFoundException extends RuntimeException {

    private final Integer rentalId;


    public RentalNotFoundException(Integer rentalId) {

        super("Rental not found");
        this.rentalId = rentalId;
    }
}
