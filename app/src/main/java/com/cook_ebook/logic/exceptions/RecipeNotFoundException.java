package com.cook_ebook.logic.exceptions;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(String error) {
        super(error);
    }
}
