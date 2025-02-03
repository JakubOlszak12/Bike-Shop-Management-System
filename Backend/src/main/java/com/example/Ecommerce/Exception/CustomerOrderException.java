package com.example.Ecommerce.Exception;

public class CustomerOrderException extends RuntimeException{

    private final CustomerOrderError orderError;

    public CustomerOrderException(CustomerOrderError customerOrderError){
        this.orderError = customerOrderError;
    }

    public CustomerOrderError getOrderError(){
        return orderError;
    }

}
