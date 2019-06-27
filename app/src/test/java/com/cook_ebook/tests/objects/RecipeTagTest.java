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

        assertTrue(recipeTag.getAllTags().contains("egg"));

        System.out.println("Finished testCreateATag");
    }

    @Test
    public void testAddTag() {
        RecipeTag recipeTag;

        System.out.println("\nStarting testAddTag");
        recipeTag = new RecipeTag("egg");
        recipeTag.addTag("sugar");

        assertTrue(recipeTag.getAllTags().contains("egg"));
        assertTrue(recipeTag.getAllTags().contains("sugar"));

        System.out.println("Finished testAddTag");
    }

    @Test
    public void testDeleteTag() {
        RecipeTag recipeTag;

        System.out.println("\nStarting testDeleteTag");
        recipeTag = new RecipeTag("egg");
        recipeTag.deleteTag("egg");

        assertEquals(0, recipeTag.getAllTags().size());

        recipeTag.addTag("egg");
        recipeTag.addTag("sugar");
        recipeTag.deleteTag("sugar");

        assertTrue(recipeTag.getAllTags().contains("egg"));
        assertTrue(!recipeTag.getAllTags().contains("sugar"));

        System.out.println("Finished testDeleteTag");
    }
}
