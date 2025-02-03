package com.example.Ecommerce.Exception;

public enum CustomerOrderError {

    ORDER_NOT_FOUND("Order not found"),;

    private final String message;

    CustomerOrderError(String message) {
        this.message = message;
    }
}
