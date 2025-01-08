package org.chatop.chatopback.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Hidden
@Log4j2
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseStatusException handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), exception.getUserId(), request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalsNotFoundException.class)
    public ResponseStatusException handleRentalsNotFoundException(RentalsNotFoundException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), null, request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseStatusException handleRentalNotFoundException(RentalNotFoundException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), exception.getRentalId(), request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseStatusException handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), null, request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AwsS3FileUploadException.class)
    public ResponseStatusException handleAwsS3FileUploadException(AwsS3FileUploadException exception) {
        log.error(exception.getCause());

        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AwsS3FileDeleteException.class)
    public ResponseStatusException handleAwsS3FileDeleteException(AwsS3FileDeleteException exception) {
        log.error(exception.getCause());

        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityPersistenceException.class)
    public ResponseStatusException handleEntityPersistenceException(EntityPersistenceException exception) {
        log.error(exception.getCause());

        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidMimeTypeException.class)
    public ResponseStatusException handleInvalidMimeTypeException(InvalidMimeTypeException exception) {
        log.warn(exception.getMessage(), exception.getCause());

        return new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseStatusException handleHandlerMethodValidationException(HandlerMethodValidationException exception,
                                                                          HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), null, request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseStatusException handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception,
                                                                             HttpServletRequest request) {
        log.warn(formatLogMessage(exception.getMessage(), null, request.getRequestURI()));

        return new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseStatusException handleBadCredentialsException(BadCredentialsException exception) {
        log.warn(exception.getMessage());

        return new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseStatusException handleGenericException(Exception exception, HttpServletRequest request) {
        log.error("Unexpected error occurred: {}, Cause: {}, Path: {}",
                exception.getMessage(), exception.getCause(), request.getRequestURI());

        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private String formatLogMessage(String entity, Object id, String path) {
        return id != null
                ? String.format("%s with ID: %s, Path=%s", entity, id, path)
                : String.format("%s, Path=%s", entity, path);
    }
}
