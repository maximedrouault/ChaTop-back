package org.chatop.chatopback.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

    private final Integer userId;


    public UserNotFoundException(Integer userId) {

        super("User not found");
        this.userId = userId;
    }
}