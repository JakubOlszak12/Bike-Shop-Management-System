package com.example.Ecommerce.Exception;

public class OrderException extends RuntimeException{

    private final OrderError orderError;

    public OrderException(OrderError orderError){
        this.orderError = orderError;
    }

    public OrderError getOrderError(){
        return orderError;
    }

}
