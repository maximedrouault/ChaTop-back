package org.chatop.chatopback.auth;

import java.io.Serializable;

public record AuthResponseDto(

        String token

) implements Serializable {}
