package com.cook_ebook.tests.business;

import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.tests.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class RecipeHandlerIT {
    private RecipeHandler recipeHandler;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for RecipeHandler");
        this.tempDB = TestUtils.copyDB();
        this.recipeHandler = new RecipeHandler(true);

        assertNotNull(this.recipeHandler);
    }

    @Test
    public void testRecipeList() {
        System.out.println("\nStarting testCreateRecipeList");

        System.out.println("Finished testCreateRecipeList");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
