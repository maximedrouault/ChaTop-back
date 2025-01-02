package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.CreateRentalRequestDto;
import org.chatop.chatopback.dto.RentalResponseDto;
import org.chatop.chatopback.dto.RentalsDto;
import org.chatop.chatopback.service.RentalService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;


    @GetMapping("/rentals")
    @Operation(summary = "Get all Rentals", responses = {
            @ApiResponse(responseCode = "200", description = "Rentals found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalsDto.class))),
            @ApiResponse(responseCode = "404", description = "Rentals not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<RentalsDto> getAllRentals() {

        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/rentals/{id}")
    @Operation(summary = "Get Rental by ID", parameters = {
            @Parameter(name = "id", description = "ID of the rental to be retrieved", required = true, example = "1"),
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Rental found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<RentalResponseDto> getRentalById(@PathVariable Integer id) {

        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PostMapping(value = "/rentals")
    public ResponseEntity<org.chatop.chatopback.response.ApiResponse> createRental(@ModelAttribute CreateRentalRequestDto createRentalRequestDto,
                                                                                   @RequestPart("picture") MultipartFile pictureFile) {

        return ResponseEntity.ok(rentalService.createRental(createRentalRequestDto, pictureFile));
    }
}
