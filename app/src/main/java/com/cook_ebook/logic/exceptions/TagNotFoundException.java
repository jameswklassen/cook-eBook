package com.cook_ebook.logic.exceptions;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String error) {
        super("The tag does not exist:\n" + error);
    }
}
