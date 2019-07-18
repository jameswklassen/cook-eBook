package com.cook_ebook.tests.business;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.RecipeTagHandler;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.tests.utils.TestUtils;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecipeTagHandlerIT {
    private RecipeTagHandler recipeTagHandler;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for RecipeTagHandler");
        this.tempDB = TestUtils.copyDB();
        this.recipeTagHandler = new RecipeTagHandler(true);

        assertNotNull(this.recipeTagHandler);
    }

    @Test
    public void testGetAllTags() {
        System.out.println("\nStarting testGetAllTags");
        final List<RecipeTag> recipeTagList;
        recipeTagList = recipeTagHandler.getAllRecipeTags();

        assertNotNull(recipeTagList);
        assertEquals(5, recipeTagList.size());
        assertTrue(recipeTagList.contains(new RecipeTag("meal")));
        assertTrue(recipeTagList.contains(new RecipeTag("dessert")));
        assertTrue(recipeTagList.contains(new RecipeTag("salty")));
        assertTrue(recipeTagList.contains(new RecipeTag("savoury")));
        assertTrue(recipeTagList.contains(new RecipeTag("breakfast")));

        assertFalse(recipeTagList.contains(new RecipeTag("not an existed tag")));

        System.out.println("Finished testGetAllTags.");
    }

    @Test
    public void testInsertTag() {
        System.out.println("\nStarting testInsertTag");
        List<RecipeTag> recipeTagList;
        recipeTagList = recipeTagHandler.getAllRecipeTags();
        assertEquals(5, recipeTagList.size());

        recipeTagHandler.insertOneTag(new RecipeTag("dessert"));
        recipeTagList = recipeTagHandler.getAllRecipeTags();
        assertEquals(5, recipeTagList.size());

        recipeTagHandler.insertOneTag(new RecipeTag("I am a new tag"));
        recipeTagList = recipeTagHandler.getAllRecipeTags();
        assertEquals(6, recipeTagList.size());

        System.out.println("Finished testInsertTag.");
    }

    @After
    public void tearDown() {
        System.out.println("Reset database.");
        // reset DB
        this.tempDB.delete();

        //clear Services
        Services.clean();
    }
}
