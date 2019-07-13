package com.cook_ebook.tests.business;

import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.logic.RecipeTagHandler;
import com.cook_ebook.logic.comparators.LatestDateComparator;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.tests.utils.TestUtils;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
        final List<Recipe> recipeList;
        recipeList = recipeHandler.getAllRecipes();

        assertNotNull(recipeList);
        assertEquals(4,recipeList.size());
        for(int i = 0; i < recipeList.size(); i ++) {
            assertNotNull(recipeList.get(i));
            assertNotNull(recipeList.get(i).getRecipeTitle());
            assertNotNull(recipeList.get(i).getRecipeDescription());
            assertNotNull(recipeList.get(i).getRecipeTagList());
            assertNotNull(recipeList.get(i).getRecipeCookingTime());
            assertNotNull(recipeList.get(i).getRecipeDate());
        }
        
        System.out.println("Finished testCreateRecipeList");
    }

    @Test
    public void testDescendingDateCompare(){
        System.out.println("\nStarting testDescendingDateCompare");

        LatestDateComparator testComparator = new LatestDateComparator();
        final List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();
        int result = testComparator.compare(actualRecipeList.get(0), actualRecipeList.get(1));
        assertTrue(result <= 0);

        System.out.println("Finished testDescendingDateCompare");
    }

    @Test
    public void testAscendingDateCompare(){
        System.out.println("\nStarting testAscendingDateCompare");

        System.out.println("Finished testAscendingDateCompare");
    }

    @Test
    public void testDescendingTitleCompare(){
        System.out.println("\nStarting testDescendingTitleCompare");

        System.out.println("Finished testDescendingTitleCompare");
    }

    @Test
    public void testAscendingTitleCompare(){
        System.out.println("\nStarting testAscendingTitleCompare");

        System.out.println("Finished testAscendingTitleCompare");
    }

    @Test
    public void testAscendingDateSort(){
        System.out.println("\nStarting testAscendingDateSort");

        System.out.println("Finished testAscendingDateSort");
    }

    @Test
    public void testDescendingDateSort(){
        System.out.println("\nStarting testDescendingDateSort");

        System.out.println("Finished testDescendingDateSort");
    }

    @Test
    public void testAscendingTitleSort(){
        System.out.println("\nStarting testAscendingTitleSort");

        System.out.println("Finished testAscendingTitleSort");
    }

    @Test
    public void testDescendingTitleSort(){
        System.out.println("\nStarting testDescendingTitleSort");

        System.out.println("Finished testDescendingTitleSort");
    }

    @Test
    public void testGetRecipeById() {
        System.out.println("\nStarting testGetRecipeById");


        System.out.println("Finished testGetRecipeById");
    }

    @Test
    public void testResetFilter() {
        System.out.println("\nStarting testResetFilter");

        System.out.println("Finished testResetFilter");
    }

    @Test
    public void testResetSort() {
        System.out.println("\nStarting testResetSort");

        System.out.println("Finished testResetSort");
    }

    @Test
    public void testResetFavourite() {
        System.out.println("\nStarting testResetFavourite");

        System.out.println("Finished testResetFavourite");
    }

    @Test
    public void testSetFavourite() {
        System.out.println("\nStarting testSetFavourite");

        System.out.println("Finished testSetFavourite");
    }

    @Test
    public void testSetFilter() {
        System.out.println("\nStarting testSetFilter");


        System.out.println("Finished testSetFilter");
    }

    // this will be updated when we change how filter is
    @Test
    public void testFilter() {
        System.out.println("\nStarting testFilter");

        System.out.println("Finished testFilter");
    }

    @Test
    public void testSetSort() {
        System.out.println("\nStarting testSetSort");


        System.out.println("Finished testSetSort");
    }

    @Test
    public void testGetRecipeListByCookingTime() {
        System.out.println("\nStarting testGetRecipeListByCookingTime");


        System.out.println("Finished testGetRecipeListByCookingTime");
    }

    @Test
    public void testGetRecipeListByTagName() {
        System.out.println("\nStarting testGetRecipeListByTagName");



        System.out.println("Finished testGetRecipeListByTagName");
    }

    @Test
    public void testGetRecipeListByTagId() {
        System.out.println("\nStarting testGetRecipeListByTagId");



        System.out.println("Finished testGetRecipeListByTagId");
    }

    @Test
    public void testGetRecipeListByTag() {
        System.out.println("\nStarting testGetRecipeListByTag");


        System.out.println("Finished testGetRecipeListByTag");
    }

    @Test
    public void testGetRecipeListByFavourite() {
        System.out.println("\nStarting testGetRecipeListByFavourite");

        System.out.println("Finished testGetRecipeListByFavourite");
    }

    @Test
    public void testInsertRecipe() {
        System.out.println("\nStarting testInsertRecipe");



        System.out.println("Finished testInsertRecipe");
    }

    @Test
    public void testUpdateRecipe() {
        System.out.println("\nStarting testUpdateRecipe");



        System.out.println("Finished testUpdateRecipe");
    }

    @Test
    public void testDeleteRecipe() {
        System.out.println("\nStarting testDeleteRecipe");



        System.out.println("Finished testDeleteRecipe");
    }

    @Test
    public void testDeleteRecipeById() {
        System.out.println("\nStarting testDeleteRecipeById");



        System.out.println("Finished testDeleteRecipeById");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
