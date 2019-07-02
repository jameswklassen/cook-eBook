package com.cook_ebook.logic.exceptions;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String error) {
        super(error);
    }
}
