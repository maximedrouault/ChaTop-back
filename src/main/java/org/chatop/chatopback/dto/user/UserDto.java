package org.chatop.chatopback.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.chatop.chatopback.entity.User}
 */
public record UserDto(

        Integer id,
        String name,
        String email,

        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy/MM/dd")
        LocalDateTime updatedAt

) implements Serializable {}