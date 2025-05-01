package com.personal.productservice.exception;

public class CartIsEmptyException extends RuntimeException {
    public CartIsEmptyException(String s) {
        super(s);
    }
}
