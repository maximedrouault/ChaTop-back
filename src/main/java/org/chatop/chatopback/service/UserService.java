package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.dto.user.UserResponseDto;
import org.chatop.chatopback.exception.UserNotFoundException;
import org.chatop.chatopback.mapper.UserMapper;
import org.chatop.chatopback.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a UserResponseDto containing the user details
     * @throws UserNotFoundException if the user is not found
     */
    public UserResponseDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
