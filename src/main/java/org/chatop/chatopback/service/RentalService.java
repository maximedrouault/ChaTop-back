package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.RentalDto;
import org.chatop.chatopback.dto.RentalsDto;
import org.chatop.chatopback.mapper.RentalMapper;
import org.chatop.chatopback.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;


    public RentalsDto getAllRentals() {
        List<RentalDto> rentals = rentalRepository.findAll()
                .stream()
                .map(rentalMapper::toDto)
                .toList();

        return new RentalsDto(rentals);
    }
}
