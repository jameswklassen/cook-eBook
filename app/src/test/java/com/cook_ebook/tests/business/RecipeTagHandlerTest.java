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
        tagSetHandler = new RecipeTagHandler();
    }

    @Test
    public void testTagSet() {
        System.out.println("\nStarting testTagSet");

        assertNotNull(tagSetHandler.getAllRecipeTags());
        assertEquals(4, tagSetHandler.getAllRecipeTags().size());

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

        tagSetHandler.insertOneTag(new RecipeTag("dessert"));
        assertEquals(4, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.insertOneTag(new RecipeTag("sweet"));
        assertEquals(5, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.deleteOneTag(new RecipeTag("sweet"));
        System.out.println("Finished testInsertTag.");
    }

    @Test
    public void testDeleteTag() {
        System.out.println("\nStarting testDeleteTag");

        tagSetHandler.deleteOneTag(new RecipeTag("haha"));
        assertEquals(4, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.deleteOneTag(new RecipeTag("cake"));
        assertEquals(3, tagSetHandler.getAllRecipeTags().size());

        tagSetHandler.insertOneTag(new RecipeTag("cake"));
        System.out.println("Finished testDeleteTag.");
    }

    @Test
    public void testDoesTagExist() {
        System.out.println("\nStarting testDoesTagExist");

        assertTrue(tagSetHandler.doesTagExist(new RecipeTag("salad")));
        assertFalse(tagSetHandler.doesTagExist(new RecipeTag("haha")));

        System.out.println("Finished testDoesTagExist.");
    }

    @Test
    public void testDoesTagNameExist() {
        System.out.println("\nStarting testDoesTagNameExist");

        assertTrue(tagSetHandler.doesTagNameExist("salad"));
        assertFalse(tagSetHandler.doesTagNameExist("haha"));

        System.out.println("Finished testDoesTagNameExist.");
    }
}
