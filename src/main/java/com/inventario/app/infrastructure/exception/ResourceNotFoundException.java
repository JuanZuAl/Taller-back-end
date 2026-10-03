package com.inventario.app.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, Throwable cause) {
        super("No se encontró el recurso: " + message, cause);
    }
    public ResourceNotFoundException(String message) {
        super("No se encontró el recurso: " + message);
    }
}
