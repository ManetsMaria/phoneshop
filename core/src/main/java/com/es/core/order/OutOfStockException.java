package com.es.core.order;

public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfStockException() {
    }
}
