package com.es.core.exception;

public class QuantityException extends Exception {
    public QuantityException(String message) {
        super(message);
    }

    public QuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
