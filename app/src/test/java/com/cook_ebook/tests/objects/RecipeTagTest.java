package com.cook_ebook.tests.objects;

import com.cook_ebook.objects.RecipeTag;

import org.junit.Test;
import static org.junit.Assert.*;


public class RecipeTagTest {
    @Test
    public void testCreateATag() {
        RecipeTag recipeTag;

        System.out.println("\nStarting testCreateATag");
        recipeTag = new RecipeTag("egg");

        assertNotNull(recipeTag);
        assertEquals("egg", recipeTag.getTagName());

        System.out.println("Finished testCreateATag");
    }
}
