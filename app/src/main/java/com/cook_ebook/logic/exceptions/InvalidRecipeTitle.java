package com.cook_ebook.logic.exceptions;

public class InvalidRecipeTitle extends InvalidRecipeException{
    public InvalidRecipeTitle(String error) {
        super("The title is empty:\n" + error);
    }
}
