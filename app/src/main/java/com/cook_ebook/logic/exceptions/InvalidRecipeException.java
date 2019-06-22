package com.cook_ebook.logic.exceptions;

public class InvalidRecipeException extends RuntimeException {
    public InvalidRecipeException(String error) {
        super(error);
    }

}
