package org.chatop.chatopback.exception;

import org.springframework.web.multipart.MultipartFile;

public class InvalidMimeTypeException extends RuntimeException {

    public InvalidMimeTypeException(MultipartFile file) {
        super("Invalid MimeType. Filename: " + file.getOriginalFilename() + ", MimeType: " + file.getContentType());
    }
}