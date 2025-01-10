package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.auth.RegisterRequestDto;
import org.chatop.chatopback.dto.user.UserResponseDto;
import org.chatop.chatopback.entity.User;
import org.mapstruct.*;

/**
 * Mapper interface for converting between User entities and DTOs.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    /**
     * Converts a RegisterRequestDto to a User entity.
     *
     * @param registerRequestDto the DTO to convert
     * @return the converted User entity
     */
    User toEntity(RegisterRequestDto registerRequestDto);

    /**
     * Converts a User entity to a UserResponseDto.
     *
     * @param user the entity to convert
     * @return the converted UserResponseDto
     */
    UserResponseDto toDto(User user);
}