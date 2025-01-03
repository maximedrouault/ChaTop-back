package org.chatop.chatopback.exception;

public class AwsS3FileUploadException extends RuntimeException {

    public AwsS3FileUploadException(Throwable cause) {
        super("Failed to upload picture file.", cause);
    }
}