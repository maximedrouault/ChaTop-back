package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.CreateRentalRequestDto;
import org.chatop.chatopback.dto.RentalResponseDto;
import org.chatop.chatopback.dto.RentalsDto;
import org.chatop.chatopback.entity.Rental;
import org.chatop.chatopback.exception.RentalNotFoundException;
import org.chatop.chatopback.exception.RentalsNotFoundException;
import org.chatop.chatopback.mapper.RentalMapper;
import org.chatop.chatopback.repository.RentalRepository;
import org.chatop.chatopback.response.ApiResponse;
import org.chatop.chatopback.response.ApiResponseMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final AwsS3Service awsS3Service;


    public RentalsDto getAllRentals() {
        List<RentalResponseDto> rentals = rentalRepository.findAll()
                .parallelStream()
                .map(rental -> {
                    URL signedPictureUrl = generateSignedPictureUrl(rental.getPicture());

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
                    URL signedPictureUrl = generateSignedPictureUrl(rental.getPicture());

                    return rentalMapper.toDto(rental, signedPictureUrl);
                })
                .orElseThrow(() -> new RentalNotFoundException(id));
    }

    public ApiResponse createRental(CreateRentalRequestDto createRentalRequestDto, MultipartFile pictureFile) {
        String mimeType = pictureFile.getContentType();
        String key = "rental_" + UUID.randomUUID() + "." + mimeType.split("/")[1];

        awsS3Service.uploadFile(key, pictureFile);

        Rental rental = rentalMapper.toEntity(createRentalRequestDto);
        rental.setPicture(key);

        rentalRepository.save(rental);

        return new ApiResponse(ApiResponseMessage.RENTAL_CREATE_SUCCESS);
    }


    private URL generateSignedPictureUrl(String key) {
        return awsS3Service.createSignedGetURL(key);
    }
}
