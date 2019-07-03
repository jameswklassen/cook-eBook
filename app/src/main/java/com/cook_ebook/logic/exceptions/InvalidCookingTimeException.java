package com.cook_ebook.logic.exceptions;

public class InvalidCookingTimeException extends InvalidRecipeException{
    public InvalidCookingTimeException(String error) {
        super(error);
    }
}
