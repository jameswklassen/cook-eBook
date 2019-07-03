package com.cook_ebook.tests.business;
import com.cook_ebook.logic.exceptions.InvalidRecipeTitle;
import com.cook_ebook.logic.exceptions.InvalidRecipeException;
import com.cook_ebook.logic.exceptions.InvalidCookingTimeException;
import com.cook_ebook.logic.exceptions.RecipeNotFoundException;
import com.cook_ebook.logic.exceptions.NotATimeException;
import com.cook_ebook.logic.exceptions.NonPositiveCookingTimeException;
import com.cook_ebook.logic.exceptions.TagNotFoundException;
import com.cook_ebook.logic.exceptions.InvalidTagException;
import org.junit.Test;
import static org.junit.Assert.*;


public class ExceptionsTest {

    @Test
    public void testInvalidRecipeTitle() {
        System.out.println("\nStarting testInvalidRecipeTitle");
        boolean caught = false;

        try {
            invalidRecipeTitle();
        } catch (InvalidRecipeTitle e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testInvalidRecipeTitle");
    }

    private void invalidRecipeTitle() {
        throw new InvalidRecipeTitle("Invalid");
    }

    @Test
    public void testInvalidCookingTime() {
        System.out.println("\nStarting testInvalidCookingTime");
        boolean caught = false;

        try {
            invalidCookingTime();
        } catch (InvalidCookingTimeException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testInvalidCookingTime");
    }

    private void invalidCookingTime() {
        throw new InvalidCookingTimeException("Invalid");
    }

    @Test
    public void testRecipeNotFound() {
        System.out.println("\nStarting testRecipeNotFound");
        boolean caught = false;

        try {
            recipeNotFound();
        } catch (RecipeNotFoundException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testRecipeNotFound");
    }

    private void recipeNotFound() {
        throw new RecipeNotFoundException("Invalid");
    }

    @Test
    public void testNotATime() {
        System.out.println("\nStarting testNotATime");
        boolean caught = false;

        try {
            NotATime();
        } catch (NotATimeException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testNotATime");
    }

    private void NotATime() {
        throw new NotATimeException("Invalid");
    }

    @Test
    public void testNonPositiveCookingTime() {
        System.out.println("\nStarting testNonPositiveCookingTime");
        boolean caught = false;

        try {
            nonPositiveCookingTime();
        } catch (NonPositiveCookingTimeException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testNonPositiveCookingTime");
    }

    private void nonPositiveCookingTime() {
        throw new NonPositiveCookingTimeException("Invalid");
    }

    @Test
    public void testInvalidRecipeException() {
        System.out.println("\nStarting testInvalidRecipeException");
        boolean caught = false;

        try {
            invalidRecipeException();
        } catch (InvalidRecipeException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testInvalidRecipeException");
    }

    private void invalidRecipeException() {
        throw new InvalidRecipeException("Invalid");
    }

    @Test
    public void testInvalidTagException() {
        System.out.println("\nStarting testInvalidTagException");
        boolean caught = false;

        try {
            invalidTag();
        } catch (InvalidTagException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testInvalidTagException");
    }

    private void invalidTag() {
        throw new InvalidTagException("Invalid");
    }

    @Test
    public void testTagNotFoundException() {
        System.out.println("\nStarting testTagNotFoundException");
        boolean caught = false;

        try {
            tagNotFound();
        } catch (TagNotFoundException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testTagNotFoundException");
    }

    private void tagNotFound() {
        throw new TagNotFoundException("Invalid");
    }
}
