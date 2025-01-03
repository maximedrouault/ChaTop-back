package org.chatop.chatopback.exception;

public class AwsS3FileDeleteException extends RuntimeException {

    public AwsS3FileDeleteException(Throwable cause) {
        super("Failed to delete picture file.", cause);
    }
}