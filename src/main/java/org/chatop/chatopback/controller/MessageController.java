package org.chatop.chatopback.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.MessageDto;
import org.chatop.chatopback.response.ApiResponse;
import org.chatop.chatopback.service.MessageService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> saveMessage(@RequestBody @Valid MessageDto messageDto) {
        ApiResponse apiResponse = messageService.saveMessage(messageDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
