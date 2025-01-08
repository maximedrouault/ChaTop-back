package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.rental.CreateRentalRequestDto;
import org.chatop.chatopback.dto.rental.RentalResponseDto;
import org.chatop.chatopback.dto.rental.RentalsResponseDto;
import org.chatop.chatopback.dto.rental.UpdateRentalRequestDto;
import org.chatop.chatopback.service.RentalService;
import org.chatop.chatopback.validation.ImageFile;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@SecurityRequirement(name = "tokenAuth")
public class RentalController {

    private final RentalService rentalService;


    @GetMapping("/rentals")
    @Operation(summary = "Get all Rentals", responses = {
            @ApiResponse(responseCode = "200", description = "Rentals found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalsResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Rentals not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<RentalsResponseDto> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/rentals/{id}")
    @Operation(summary = "Get Rental by ID", parameters = {
            @Parameter(name = "id", description = "ID of the rental to be retrieved", required = true, example = "1"),
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Rental found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<RentalResponseDto> getRentalById(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PostMapping("/rentals")
    @Operation(summary = "Create a new Rental", requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data",
            schema = @Schema(implementation = CreateRentalRequestDto.class)))
    , responses = {
            @ApiResponse(responseCode = "200", description = "Rental created", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = org.chatop.chatopback.response.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<org.chatop.chatopback.response.ApiResponse> createRental(
            @Valid @ModelAttribute CreateRentalRequestDto createRentalRequestDto,
            @RequestPart("picture") @ImageFile MultipartFile pictureFile) {

        return ResponseEntity.ok(rentalService.createRental(createRentalRequestDto, pictureFile));
    }

    @PutMapping("/rentals/{id}")
    @Operation(summary = "Update Rental by ID", requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data",
            schema = @Schema(implementation = UpdateRentalRequestDto.class)))
    , responses = {
            @ApiResponse(responseCode = "200", description = "Rental updated", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = org.chatop.chatopback.response.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<org.chatop.chatopback.response.ApiResponse> updateRental(
            @PathVariable @Positive Integer id,
            @Valid @ModelAttribute UpdateRentalRequestDto updateRentalRequestDto) {

        return ResponseEntity.ok(rentalService.updateRental(id, updateRentalRequestDto));
    }
}
