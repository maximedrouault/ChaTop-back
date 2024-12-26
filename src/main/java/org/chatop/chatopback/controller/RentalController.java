package org.chatop.chatopback.controller;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.RentalsDto;
import org.chatop.chatopback.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;


    @GetMapping("/rentals")
    public ResponseEntity<RentalsDto> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
}
