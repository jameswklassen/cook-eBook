package com.cook_ebook.tests.business;

import com.cook_ebook.logic.RecipeTagHandler;
import com.cook_ebook.objects.RecipeTag;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecipeTagHandlerTest {
    private RecipeTagHandler tagSetHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for RecipeTagHandler");
        tagSetHandler = new RecipeTagHandler(false);
    }

    @Test
    public void testTagSet() {
        System.out.println("\nStarting testTagSet");

        assertNotNull(tagSetHandler.getAllRecipeTags());

        assertEquals(4, tagSetHandler.getAllRecipeTags().size());
        assertTrue(tagSetHandler.getAllRecipeTags().contains(new RecipeTag("dessert")));
        assertTrue(tagSetHandler.getAllRecipeTags().contains(new RecipeTag("cake")));
        assertTrue(tagSetHandler.getAllRecipeTags().contains(new RecipeTag("pasta")));
        assertTrue(tagSetHandler.getAllRecipeTags().contains(new RecipeTag("salad")));

        assertFalse(tagSetHandler.getAllRecipeTags().contains(new RecipeTag("haha")));

        System.out.println("Finished testTagSet.");
    }

    @Test
    public void testInsertTag() {
        System.out.println("\nStarting testInsertTag");

        int expectNum = 4;

        tagSetHandler.insertOneTag(new RecipeTag("dessert"));
        assertEquals(expectNum, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.insertOneTag(new RecipeTag("I am a new tag"));
        assertEquals(expectNum + 1, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.deleteOneTag(new RecipeTag("I am a new tag"));

        System.out.println("Finished testInsertTag.");
    }

    @Test
    public void testDeleteTag() {
        System.out.println("\nStarting testDeleteTag");

        int expectNum = 4;

        tagSetHandler.deleteOneTag(new RecipeTag("I am not an existed tag"));
        assertEquals(4, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.deleteOneTag(new RecipeTag("dessert"));
        assertEquals(expectNum - 1, tagSetHandler.getAllRecipeTags().size());

        RecipeTag tag = tagSetHandler.insertOneTag(new RecipeTag("dessert"));
        assertNotNull(tag);

        RecipeTag tag2 = tagSetHandler.insertOneTag(null);
        assertNull(tag2);

        System.out.println("Finished testDeleteTag.");
    }
}
