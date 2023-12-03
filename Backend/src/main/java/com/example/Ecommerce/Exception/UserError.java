package com.example.Ecommerce.Exception;
public enum UserError{

    USER_NOT_FOUND("User not found"),
    USER_LOGIN_NOT_AVAILABLE("User login is not available"),
    USER_EMAIL_NOT_AVAILABLE("User email is not available"),
    USER_EMAIL_EMPTY("User email is empty"),
    USER_LOGIN_EMPTY("User login is empty"),;

    private final String message;

    UserError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}