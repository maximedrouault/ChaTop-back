package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.RentalDto;
import org.chatop.chatopback.dto.RentalsDto;
import org.chatop.chatopback.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    schema = @Schema(example = """
                        {
                            "type": "about:blank",
                            "title": "Not Found",
                            "status": 404,
                            "detail": "Rentals not found",
                            "instance": "/api/rentals"
                        }
                    """)))
    })
    public ResponseEntity<RentalsDto> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/rentals/{id}")
    @Operation(summary = "Get Rental by ID", parameters = {
            @Parameter(name = "id", description = "ID of the rental to be retrieved", required = true, example = "1"),
    }, responses = {
            @ApiResponse(responseCode = "200", description = "Rental found", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RentalDto.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found", content = @Content(mediaType = "application/json",
                    schema = @Schema(example = """
                        {
                            "type": "about:blank",
                            "title": "Not Found",
                            "status": 404,
                            "detail": "Rental not found",
                            "instance": "/api/rentals/0"
                        }
                    """)))
    })
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }
}
