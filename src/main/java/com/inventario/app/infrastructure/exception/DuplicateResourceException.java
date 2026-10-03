package com.inventario.app.infrastructure.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message, Throwable cause) {
        super("El recurso ya existe: " + message, cause);
    }
    public DuplicateResourceException(String message) {
        super("El recurso ya existe: " + message);
    }

}
