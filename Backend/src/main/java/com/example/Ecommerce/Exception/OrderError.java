package com.example.Ecommerce.Exception;

public enum OrderError {

    ORDER_NOT_FOUND("Order not found"),;

    private final String message;

    OrderError(String message) {
        this.message = message;
    }
}
