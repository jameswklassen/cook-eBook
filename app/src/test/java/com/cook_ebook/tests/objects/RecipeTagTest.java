package com.cook_ebook.tests.objects;

import com.cook_ebook.objects.RecipeTag;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class RecipeTagTest {

    @Before
    public void setup() {
        System.out.println("Starting test for RecipeTag");
    }

    @Test
    public void testCreateATag() {
        System.out.println("\nStarting testCreateATag");
        RecipeTag recipeTag = new RecipeTag("egg");

        assertNotNull(recipeTag);
        assertEquals("egg", recipeTag.getTagName());

        System.out.println("Finished testCreateATag");
    }
}
