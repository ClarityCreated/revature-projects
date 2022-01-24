package com.revature.revaturebookshelfjava.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CartItemNotExistException extends Exception{
    private String message;
}
