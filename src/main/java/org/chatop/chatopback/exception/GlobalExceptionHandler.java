package org.chatop.chatopback.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Hidden
@Log4j2
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseStatusException handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        log.error("{} with ID: {}, Path={}", exception.getMessage(), exception.getUserId(), request.getRequestURI());

        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
