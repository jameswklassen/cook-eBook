package com.cook_ebook.tests.business;

import com.cook_ebook.logic.RecipeTagSetHandler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecipeTagSetHandlerTest {
    private RecipeTagSetHandler tagSetHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for RecipeTagSetHandler");
        tagSetHandler = new RecipeTagSetHandler();
    }

    @Test
    public void testTagSet() {
        System.out.println("\nStarting testTagSet");

        assertNotNull(tagSetHandler.getAllRecipeTags());
        assertEquals(4, tagSetHandler.getAllRecipeTags().size());

        System.out.println("Finished testTagSet.");
    }

    @Test
    public void testInsertTag() {
        System.out.println("\nStarting testInsertTag");

        assertFalse(tagSetHandler.insertOneTag("dessert"));

        assertTrue(tagSetHandler.insertOneTag("sweet"));
        assertEquals(5, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.deleteOneTag("sweet");
        System.out.println("Finished testInsertTag.");
    }

    @Test
    public void testDeleteTag() {
        System.out.println("\nStarting testDeleteTag");

        assertFalse(tagSetHandler.deleteOneTag("haha"));

        assertTrue(tagSetHandler.deleteOneTag("cake"));
        assertEquals(3, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.insertOneTag("cake");
        System.out.println("Finished testDeleteTag.");
    }

    @Test
    public void testDoesTagExist() {
        System.out.println("\nStarting testDoesTagExist");

        assertTrue(tagSetHandler.doesTagExist("salad"));
        assertFalse(tagSetHandler.doesTagExist("haha"));

        System.out.println("Finished testDoesTagExist.");
    }
}
