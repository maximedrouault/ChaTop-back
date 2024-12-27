package org.chatop.chatopback.controller;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.MessageDto;
import org.chatop.chatopback.response.ResponseMessage;
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
    public ResponseEntity<ResponseMessage> saveMessage(@RequestBody MessageDto messageDto) {
        ResponseMessage responseMessage = messageService.saveMessage(messageDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
