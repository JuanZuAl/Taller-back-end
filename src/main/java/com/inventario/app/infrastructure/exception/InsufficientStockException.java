package com.inventario.app.infrastructure.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message, Throwable cause) {
        super("No hay suficiente stock: " + message, cause);
    }
    public InsufficientStockException(String message) {
        super("No hay suficiente stock: " + message);
    }
}
