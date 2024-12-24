package org.chatop.chatopback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.chatop.chatopback.entity.User}
 */
@Value
public class UserDto implements Serializable {

    Integer id;
    String name;
    String email;

    @JsonFormat(pattern = "yyyy/MM/dd")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy/MM/dd")
    LocalDateTime updatedAt;
}