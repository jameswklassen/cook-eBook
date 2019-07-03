package com.cook_ebook.logic.exceptions;

public class NonPositiveCookingTimeException extends InvalidCookingTimeException{
    public NonPositiveCookingTimeException(String error) {
        super("The cooking time is a non-positive number:\n" + error);
    }
}
