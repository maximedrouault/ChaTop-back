package org.chatop.chatopback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.message.MessageRequestDto;
import org.chatop.chatopback.response.ApiResponse;
import org.chatop.chatopback.service.MessageService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;


    @PostMapping("/messages")
    @Operation(summary = "Save a message",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Message to be saved", required = true,
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MessageRequestDto.class))),
            responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Message saved",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User or Rental not found",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ApiResponse> saveMessage(@RequestBody @Valid MessageRequestDto messageRequestDto) {
        ApiResponse apiResponse = messageService.saveMessage(messageRequestDto);

        return ResponseEntity.ok().body(apiResponse);
    }
}
