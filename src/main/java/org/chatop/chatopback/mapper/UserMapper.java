package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.user.UserResponseDto;
import org.chatop.chatopback.entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserResponseDto userResponseDto);

    UserResponseDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserResponseDto userResponseDto, @MappingTarget User user);
}