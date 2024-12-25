package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.UserDto;
import org.chatop.chatopback.exception.UserNotFoundException;
import org.chatop.chatopback.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserDto getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()))
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
