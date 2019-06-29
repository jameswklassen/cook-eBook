package com.cook_ebook.tests.business;

import com.cook_ebook.logic.RecipeTagHandler;
import com.cook_ebook.objects.RecipeTag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RecipeTagHandlerTest {
    private RecipeTagHandler tagSetHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for RecipeTagHandler");
        tagSetHandler = new RecipeTagHandler();
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
    public void testGetTagIdByName() {
        System.out.println("\nStarting testGetTagIdByName");

        // this test will be implemented after we have true database
        assertNotEquals(-1, tagSetHandler.getTagIdByName("dessert"));
        assertEquals(-1, tagSetHandler.getTagIdByName("haha"));

        System.out.println("Finished testGetTagIdByName.");
    }


    @Test
    public void testGetTagNameById() {
        System.out.println("\nStarting testGetTagNameById");

        // this test will be implemented after we have true database
        assertEquals("Tag Id does not exist!", tagSetHandler.getTagNameById(-1));

        System.out.println("Finished testGetTagNameById.");
    }

    @Test
    public void testGetTagById() {
        System.out.println("\nStarting testGetTagById");

        // this test will be implemented after we have true database
        assertNull(tagSetHandler.getTagById(-1));

        System.out.println("Finished testGetTagById.");
    }

    @Test
    public void testGetTagByName() {
        System.out.println("\nStarting testGetTagByName");

        assertEquals(new RecipeTag("dessert"), tagSetHandler.getTagByName("dessert"));
        assertNotEquals(new RecipeTag("dessert"), tagSetHandler.getTagByName("cake"));
        assertNotEquals(new RecipeTag("dessert"), tagSetHandler.getTagByName("sweet"));

        System.out.println("Finished testGetTagByName.");
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

        tagSetHandler.insertOneTag(new RecipeTag("dessert"));

        System.out.println("Finished testDeleteTag.");
    }

    @Test
    public void testDoesTagExist() {
        System.out.println("\nStarting testDoesTagExist");

        assertTrue(tagSetHandler.doesTagExist(new RecipeTag("salad")));
        assertFalse(tagSetHandler.doesTagExist(new RecipeTag("I am not an existed tag")));

        System.out.println("Finished testDoesTagExist.");
    }

    @Test
    public void testDoesTagNameExist() {
        System.out.println("\nStarting testDoesTagNameExist");

        assertTrue(tagSetHandler.doesTagNameExist("salad"));
        assertFalse(tagSetHandler.doesTagNameExist("I am not an existed tag"));

        System.out.println("Finished testDoesTagNameExist.");
    }
}