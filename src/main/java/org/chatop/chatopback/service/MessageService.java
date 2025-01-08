package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.message.MessageRequestDto;
import org.chatop.chatopback.entity.Message;
import org.chatop.chatopback.exception.RentalNotFoundException;
import org.chatop.chatopback.exception.UserNotFoundException;
import org.chatop.chatopback.mapper.MessageMapper;
import org.chatop.chatopback.repository.MessageRepository;
import org.chatop.chatopback.repository.RentalRepository;
import org.chatop.chatopback.repository.UserRepository;
import org.chatop.chatopback.response.ApiResponse;
import org.chatop.chatopback.response.ApiResponseMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final MessageMapper messageMapper;


    @Transactional
    public ApiResponse saveMessage(MessageRequestDto messageRequestDto) {
        checkUserExists(messageRequestDto.userId());
        checkRentalExists(messageRequestDto.rentalId());

        Message message = messageMapper.toEntity(messageRequestDto);
        messageRepository.save(message);

        return new ApiResponse(ApiResponseMessage.MESSAGE_SEND_SUCCESS);
    }


    private void checkUserExists(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    private void checkRentalExists(Integer rentalId) {
        if (!rentalRepository.existsById(rentalId)) {
            throw new RentalNotFoundException(rentalId);
        }
    }
}
