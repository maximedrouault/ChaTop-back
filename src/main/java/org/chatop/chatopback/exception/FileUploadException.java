package org.chatop.chatopback.exception;

public class FileUploadException extends RuntimeException {

    public FileUploadException(Throwable cause) {
        super("Failed to upload picture file.", cause);
    }
}