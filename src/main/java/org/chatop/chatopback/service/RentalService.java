package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.rental.CreateRentalRequestDto;
import org.chatop.chatopback.dto.rental.RentalResponseDto;
import org.chatop.chatopback.dto.rental.RentalsResponseDto;
import org.chatop.chatopback.dto.rental.UpdateRentalRequestDto;
import org.chatop.chatopback.entity.Rental;
import org.chatop.chatopback.entity.User;
import org.chatop.chatopback.exception.EntityPersistenceException;
import org.chatop.chatopback.exception.InvalidMimeTypeException;
import org.chatop.chatopback.exception.RentalNotFoundException;
import org.chatop.chatopback.exception.RentalsNotFoundException;
import org.chatop.chatopback.mapper.RentalMapper;
import org.chatop.chatopback.repository.RentalRepository;
import org.chatop.chatopback.response.ApiResponse;
import org.chatop.chatopback.response.ApiResponseMessage;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for handling rental-related operations.
 */
@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final AwsS3Service awsS3Service;
    private final AuthService authService;


    /**
     * Retrieves all rentals.
     *
     * @return a RentalsResponseDto containing a list of all rentals
     * @throws RentalsNotFoundException if no rentals are found
     */
    public RentalsResponseDto getAllRentals() {
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

        return new RentalsResponseDto(rentals);
    }

    /**
     * Retrieves a rental by its ID.
     *
     * @param id the ID of the rental to retrieve
     * @return a RentalResponseDto containing the rental details
     * @throws RentalNotFoundException if the rental is not found
     */
    public RentalResponseDto getRentalById(Integer id) {
        return rentalRepository.findById(id)
                .map(rental -> {
                    URL signedPictureUrl = generateSignedPictureUrl(rental.getPicture());

                    return rentalMapper.toDto(rental, signedPictureUrl);
                })
                .orElseThrow(() -> new RentalNotFoundException(id));
    }

    /**
     * Creates a new rental.
     *
     * @param createRentalRequestDto the DTO containing rental details
     * @param pictureFile the picture file to be uploaded
     * @return an ApiResponse indicating the result of the operation
     * @throws InvalidMimeTypeException if the picture file has an invalid MIME type
     * @throws EntityPersistenceException if there is an error during persistence
     */
    @Transactional
    public ApiResponse createRental(CreateRentalRequestDto createRentalRequestDto,
                                    MultipartFile pictureFile,
                                    JwtAuthenticationToken jwtAuthenticationToken) {

        User authenticatedUser = authService.getAuthenticatedUser(jwtAuthenticationToken);
        String key = Optional.ofNullable(pictureFile.getContentType())
                .map(mimeType -> "rental_" + UUID.randomUUID() + "." + mimeType.split("/")[1])
                .orElseThrow(() -> new InvalidMimeTypeException(pictureFile));

        try {
            awsS3Service.uploadFile(key, pictureFile);

            Rental rental = rentalMapper.toEntity(createRentalRequestDto);
            rental.setOwner(authenticatedUser);
            rental.setPicture(key);

            rentalRepository.save(rental);

            return new ApiResponse(ApiResponseMessage.RENTAL_CREATE_SUCCESS);

        } catch (Exception exception) {
            awsS3Service.deleteFile(key);
            throw new EntityPersistenceException(exception);
        }
    }

    /**
     * Updates an existing rental.
     *
     * @param id the ID of the rental to update
     * @param updateRentalRequestDto the DTO containing updated rental details
     * @return an ApiResponse indicating the result of the operation
     * @throws RentalNotFoundException if the rental is not found
     */
    public ApiResponse updateRental(Integer id, UpdateRentalRequestDto updateRentalRequestDto) {

        return rentalRepository.findById(id)
                .map(rental -> {
                    Rental updatedRental = rentalMapper.partialUpdate(updateRentalRequestDto, rental);

                    rentalRepository.save(updatedRental);

                    return new ApiResponse(ApiResponseMessage.RENTAL_UPDATE_SUCCESS);
                })
                .orElseThrow(() -> new RentalNotFoundException(id));
    }


    /**
     * Generates a signed URL for accessing a picture.
     *
     * @param key the key of the picture in the storage
     * @return the signed URL
     */
    private URL generateSignedPictureUrl(String key) {
        return awsS3Service.createSignedGetURL(key);
    }
}
