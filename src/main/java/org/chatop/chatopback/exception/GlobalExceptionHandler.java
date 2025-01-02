package org.chatop.chatopback.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Hidden
@Log4j2
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseStatusException handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), exception.getUserId(), request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(RentalsNotFoundException.class)
    public ResponseStatusException handleRentalsNotFoundException(RentalsNotFoundException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), null, request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseStatusException handleRentalNotFoundException(RentalNotFoundException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), exception.getRentalId(), request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseStatusException handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), null, request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseStatusException handleFileUploadException(FileUploadException exception) {
        log.error(exception.getCause());

        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }


    private String formatLogMessage(String entity, Object id, String path) {
        return id != null
                ? String.format("%s with ID: %s, Path=%s", entity, id, path)
                : String.format("%s, Path=%s", entity, path);
    }
}
