package org.chatop.chatopback.exception;

public class EntityPersistenceException extends RuntimeException {

    public EntityPersistenceException(Throwable cause) {
        super("Database error occurred.", cause);
    }
}