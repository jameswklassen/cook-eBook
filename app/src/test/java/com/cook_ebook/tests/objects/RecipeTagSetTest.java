package com.cook_ebook.tests.objects;

import com.cook_ebook.objects.RecipeTagSet;

import org.junit.Test;
import static org.junit.Assert.*;


public class RecipeTagSetTest {
    @Test
    public void testCreateATag() {
        RecipeTagSet recipeTagSet;

        System.out.println("\nStarting testCreateATag");
        recipeTagSet = new RecipeTagSet("egg");

        assertTrue(recipeTagSet.getAllTags().contains("egg"));

        System.out.println("Finished testCreateATag");
    }

    @Test
    public void testAddTag() {
        RecipeTagSet recipeTagSet;

        System.out.println("\nStarting testAddTag");
        recipeTagSet = new RecipeTagSet("egg");
        recipeTagSet.addTag("sugar");

        assertTrue(recipeTagSet.getAllTags().contains("egg"));
        assertTrue(recipeTagSet.getAllTags().contains("sugar"));

        System.out.println("Finished testAddTag");
    }

    @Test
    public void testDeleteTag() {
        RecipeTagSet recipeTagSet;

        System.out.println("\nStarting testDeleteTag");
        recipeTagSet = new RecipeTagSet("egg");
        recipeTagSet.deleteTag("egg");

        assertEquals(0, recipeTagSet.getAllTags().size());

        recipeTagSet.addTag("egg");
        recipeTagSet.addTag("sugar");
        recipeTagSet.deleteTag("sugar");

        assertTrue(recipeTagSet.getAllTags().contains("egg"));
        assertTrue(!recipeTagSet.getAllTags().contains("sugar"));

        System.out.println("Finished testDeleteTag");
    }
}
