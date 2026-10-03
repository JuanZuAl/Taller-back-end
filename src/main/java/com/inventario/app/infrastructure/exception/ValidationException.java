package com.inventario.app.infrastructure.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message, Throwable cause) {
        super("La validación falló: " + message, cause);
    }
    public ValidationException(String message) {
        super("La validación falló: " + message);
    }
}
