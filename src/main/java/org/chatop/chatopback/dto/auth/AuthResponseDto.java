package org.chatop.chatopback.dto.auth;

import java.io.Serializable;

public record AuthResponseDto(

        String token

) implements Serializable {}
