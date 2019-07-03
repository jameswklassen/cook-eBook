package com.cook_ebook.logic.exceptions;

public class NotATimeException extends InvalidCookingTimeException {
    public NotATimeException(String error) {
        super("The cooking time is not a number:\n" +error);
    }
}
