package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
}
