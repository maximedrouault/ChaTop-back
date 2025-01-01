package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.RentalDto;
import org.chatop.chatopback.dto.RentalsDto;
import org.chatop.chatopback.exception.RentalNotFoundException;
import org.chatop.chatopback.exception.RentalsNotFoundException;
import org.chatop.chatopback.mapper.RentalMapper;
import org.chatop.chatopback.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final AwsS3Service awsS3Service;

    @Value("${aws.bucket.name}")
    private String bucketName;


    public RentalsDto getAllRentals() {
        List<RentalDto> rentals = rentalRepository.findAll()
                .parallelStream()
                .map(rental -> {
                    String signedPictureUrl = generateSignedPictureUrl(rental.getPicture());

                    return rentalMapper.toDto(rental, signedPictureUrl);
                })
                .toList();

        if (rentals.isEmpty()) {
            throw new RentalsNotFoundException();
        }

        return new RentalsDto(rentals);
    }

    public RentalDto getRentalById(Integer id) {
        return rentalRepository.findById(id)
                .map(rental -> {
                    String signedPictureUrl = generateSignedPictureUrl(rental.getPicture());

                    return rentalMapper.toDto(rental, signedPictureUrl);
                })
                .orElseThrow(() -> new RentalNotFoundException(id));
    }


    private String generateSignedPictureUrl(String key) {
        return awsS3Service.createSignedGetURL(bucketName, key, Duration.ofMinutes(5)).toString();
    }
}
