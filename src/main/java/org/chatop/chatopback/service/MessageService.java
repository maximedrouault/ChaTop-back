package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.message.MessageRequestDto;
import org.chatop.chatopback.entity.Message;
import org.chatop.chatopback.entity.User;
import org.chatop.chatopback.exception.RentalNotFoundException;
import org.chatop.chatopback.mapper.MessageMapper;
import org.chatop.chatopback.repository.MessageRepository;
import org.chatop.chatopback.repository.RentalRepository;
import org.chatop.chatopback.response.ApiResponse;
import org.chatop.chatopback.response.ApiResponseMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RentalRepository rentalRepository;
    private final MessageMapper messageMapper;
    private final AuthService authService;


    @Transactional
    public ApiResponse saveMessage(MessageRequestDto messageRequestDto) {
        User authenticatedUser = authService.getAuthenticatedUser();
        checkRentalExists(messageRequestDto.rentalId());

        Message message = messageMapper.toEntity(messageRequestDto);
        message.setUser(authenticatedUser);
        messageRepository.save(message);

        return new ApiResponse(ApiResponseMessage.MESSAGE_SEND_SUCCESS);
    }


    private void checkRentalExists(Integer rentalId) {
        if (!rentalRepository.existsById(rentalId)) {
            throw new RentalNotFoundException(rentalId);
        }
    }
}
