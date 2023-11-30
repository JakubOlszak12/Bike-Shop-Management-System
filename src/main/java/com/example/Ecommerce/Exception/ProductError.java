package com.example.Ecommerce.Exception;




public enum ProductError {
    PRODUCT_NOT_FOUND("Product not found"),;

    private final String message;

    ProductError(String message) {
        this.message = message;
    }

}
