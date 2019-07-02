package com.cook_ebook.logic.exceptions;

public class InvalidTagException extends RuntimeException {
    public InvalidTagException(String error) {
        super("Unable to access Tag data:\n" + error);
    }
}
