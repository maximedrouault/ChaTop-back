package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.RentalRequestDto;
import org.chatop.chatopback.dto.RentalResponseDto;
import org.chatop.chatopback.dto.RentalsDto;
import org.chatop.chatopback.entity.Rental;
import org.chatop.chatopback.exception.RentalNotFoundException;
import org.chatop.chatopback.exception.RentalsNotFoundException;
import org.chatop.chatopback.mapper.RentalMapper;
import org.chatop.chatopback.repository.RentalRepository;
import org.chatop.chatopback.response.ApiResponse;
import org.chatop.chatopback.response.ApiResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final AwsS3Service awsS3Service;

    @Value("${aws.bucket.name}")
    private String bucketName;


    public RentalsDto getAllRentals() {
        List<RentalResponseDto> rentals = rentalRepository.findAll()
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

    public RentalResponseDto getRentalById(Integer id) {
        return rentalRepository.findById(id)
                .map(rental -> {
                    String signedPictureUrl = generateSignedPictureUrl(rental.getPicture());

                    return rentalMapper.toDto(rental, signedPictureUrl);
                })
                .orElseThrow(() -> new RentalNotFoundException(id));
    }

    public ApiResponse createRental(RentalRequestDto rentalRequestDto, MultipartFile pictureFile) {
        String mimeType = pictureFile.getContentType();
        String pictureKey = "rental_" + UUID.randomUUID() + "." + mimeType.split("/")[1];

        awsS3Service.uploadFile(bucketName, pictureKey, pictureFile);

        Rental rental = rentalMapper.toEntity(rentalRequestDto);
        rental.setPicture(pictureKey);

        rentalRepository.save(rental);

        return new ApiResponse(ApiResponseMessage.RENTAL_CREATE_SUCCESS);
    }


    private String generateSignedPictureUrl(String key) {
        return awsS3Service.createSignedGetURL(bucketName, key, Duration.ofMinutes(5)).toString();
    }
}
