package org.chatop.chatopback.exception;

public class RentalsNotFoundException extends RuntimeException {

    public RentalsNotFoundException() {

        super("Rentals not found");
    }
}