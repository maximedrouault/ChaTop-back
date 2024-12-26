package org.chatop.chatopback.mapper;

import org.chatop.chatopback.dto.UserDto;
import org.chatop.chatopback.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}