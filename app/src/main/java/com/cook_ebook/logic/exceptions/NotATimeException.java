package com.cook_ebook.logic.exceptions;

public class NotATimeException extends InvalidCookingTimeException {
    public NotATimeException(String error) {
        super(error);
    }
}
