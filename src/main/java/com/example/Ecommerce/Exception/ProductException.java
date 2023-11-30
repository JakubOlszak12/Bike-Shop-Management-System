package com.example.Ecommerce.Exception;




public class ProductException extends RuntimeException{
    private final ProductError productError;

    public ProductException(ProductError productError) {
        this.productError = productError;
    }

    public ProductError getProductError() {
        return productError;
    }
}
