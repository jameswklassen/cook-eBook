package com.cook_ebook.tests.business;

import com.cook_ebook.logic.RecipeValidator;
import com.cook_ebook.objects.Recipe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecipeValidatorTest {

    @Before
    public void setup() {
        System.out.println("Starting test for RecipeValidator");
    }

    @Test
    public void testValidateRecipe() {
        System.out.println("\nStarting testValidateRecipe");

        assertFalse(RecipeValidator.validateRecipe(null));
        assertTrue(RecipeValidator.validateRecipe(new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true)));
        assertTrue(RecipeValidator.validateRecipe(new Recipe(1000)));

        System.out.println("Finished testValidateRecipe");
    }

    @Test
    public void testValidateTitle() {
        System.out.println("\nStarting testValidateTitle");

        assertFalse(RecipeValidator.validateTitle(""));
        assertTrue(RecipeValidator.validateTitle("Haha"));

        System.out.println("Finished testValidateTitle");
    }

    @Test
    public void testValidateCookingTimeNumeric() {
        System.out.println("\nStarting testValidateCookingTimeNumeric");

        assertFalse(RecipeValidator.validateCookingTimeNumeric("haha"));
        assertTrue(RecipeValidator.validateCookingTimeNumeric("100"));

        System.out.println("Finished testValidateCookingTimeNumeric");
    }

    @Test
    public void testValidateCookingTimePositive() {
        System.out.println("\nStarting testValidateCookingTimePositive");

        assertFalse(RecipeValidator.validateCookingTimePositive("-100"));
        assertTrue(RecipeValidator.validateCookingTimeNumeric("999"));

        System.out.println("Finished testValidateCookingTimePositive");
    }
}
