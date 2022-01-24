package com.revature.revaturebookshelfjava.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidSearchPropertyException extends Exception{

    private String message;

    public InvalidSearchPropertyException(String message) {
        this.message = message;
    }
}

//THIS IS A CUSTOM EXCEPTION USED IN THE CASE OF A SEARCH FAILURE - USED ONLY IN THE SEARCH FUNCTIONALITY