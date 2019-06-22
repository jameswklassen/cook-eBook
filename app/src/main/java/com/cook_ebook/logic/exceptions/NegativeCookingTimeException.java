package com.cook_ebook.logic.exceptions;

public class NegativeCookingTimeException extends InvalidCookingTimeException{
    public NegativeCookingTimeException(String error) {
        super(error);
    }
}
