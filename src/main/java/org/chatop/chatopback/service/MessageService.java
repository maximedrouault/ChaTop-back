package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.MessageDto;
import org.chatop.chatopback.entity.Message;
import org.chatop.chatopback.mapper.MessageMapper;
import org.chatop.chatopback.repository.MessageRepository;
import org.chatop.chatopback.response.ResponseMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;


    public ResponseMessage saveMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity(messageDto);
        messageRepository.save(message);

        return new ResponseMessage("Message send with success");
    }
}
