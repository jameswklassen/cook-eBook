package com.cook_ebook.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTagSet;

import java.util.List;

public class RecipeHandlerTest {

    private RecipeHandler recipeHandler;

    @Before
    public void setUp() {
        System.out.println("Starting test for RecipeHandler");
        recipeHandler = new RecipeHandler();
    }

    @Test
    public void testRecipeList() {
        // this test will be updated after we have true database,
        // because recipe date is assigned as current date(),
        // our mock data can't identify it right now.
        // as the result, we only test that we can get all recipes in the list

        System.out.println("\nStarting testCreateRecipeList");
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertNotNull(actualRecipeList);
        assertEquals(4, actualRecipeList.size());

        System.out.println("Finished testCreateRecipeList");
    }

    @Test
    public void testSortRecipeListByDescendingDate() {
        // this test will be updated after we have true database,
        // because recipe date is assigned as current date(),
        // our mock data can't identify it right now.
        // as the result, we only test that we can get all recipes in the list

        System.out.println("\nStarting testSortRecipeListByDescendingDate");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByDescendingDate();

        assertNotNull(actualRecipeList);
        assertEquals(4, actualRecipeList.size());

        System.out.println("Finished testSortRecipeListByDescendingDate");
    }

    @Test
    public void testGetRecipeById() {
        System.out.println("\nStarting testGetRecipeById");
        Recipe targetRecipe1 = recipeHandler.getRecipeById(3);

        assertEquals(3, targetRecipe1.getRecipeID());

        System.out.println("Finished testGetRecipeById");
    }

    @Test
    public void testGetRecipeListByCookingTime() {
        System.out.println("\nStarting testGetRecipeListByCookingTime");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByCookingTime(30);

        assertEquals(2, actualRecipeList.size());

        for(int i = 0; i < actualRecipeList.size(); i ++) {
            assertEquals(30, actualRecipeList.get(i).getRecipeCookingTime());
        }

        System.out.println("Finished testGetRecipeListByCookingTime");
    }

    @Test
    public void testGetRecipeListByTag() {
        System.out.println("\nStarting testGetRecipeListByTag");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByTag("cake");

        assertEquals(1, actualRecipeList.size());

        for(int i = 0; i < actualRecipeList.size(); i ++) {
            assertEquals("cake", actualRecipeList.get(i).getRecipeTagSet().get(0));
        }

        System.out.println("Finished testGetRecipeListByTag");
    }

    @Test
    public void testGetRecipeListByFavourite() {
        System.out.println("\nStarting testGetRecipeListByFavourite");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByFavourite(false);

        assertEquals(3, actualRecipeList.size());

        for(int i = 0; i < actualRecipeList.size(); i ++) {
            assertFalse(actualRecipeList.get(i).getRecipeIsFavourite());
        }

        System.out.println("Finished testGetRecipeListByFavourite");
    }

    @Test
    public void testGetRecipeListByDate() {
        System.out.println("\nStarting testGetRecipeListByDate");
        // this test will be modified after we have true database,
        // because all recipes' date are assigned as current date
        System.out.println("Finished testGetRecipeListByDate");
    }

    @Test
    public void testInsertRecipe() {
        System.out.println("\nStarting testInsertRecipe");

        Recipe recipe = new Recipe("pasta3",
                "pasta3 description",
                "egg, water",
                10,
                "pasta3 images",
                new RecipeTagSet(),
                true);

        recipeHandler.insertRecipe(recipe);
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertEquals(5, actualRecipeList.size());
        assertEquals(recipe, recipeHandler.insertRecipe(recipe));

        System.out.println("Finished testInsertRecipe");
    }

    @Test
    public void testUpdateRecipe() {
        System.out.println("\nStarting testUpdateRecipe");

        Recipe recipe = recipeHandler.getAllRecipes().get(0);
        recipe.setRecipeDescription("I'm a test description.");

        recipeHandler.updateRecipe(recipe);
        assertEquals("I'm a test description.", recipeHandler.getAllRecipes().get(0).getRecipeDescription());

        System.out.println("Finished testUpdateRecipe");
    }

    @Test
    public void testDeleteRecipe() {
        System.out.println("\nStarting testDeleteRecipe");

        Recipe recipe = recipeHandler.getAllRecipes().get(0);
        recipeHandler.deleteRecipe(recipe);
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertEquals(3, actualRecipeList.size());

        System.out.println("Finished testDeleteRecipe");
    }

    @Test
    public void testDeleteRecipeById() {
        System.out.println("\nStarting testDeleteRecipeById");

        Recipe recipe = recipeHandler.getRecipeById(3);
        recipeHandler.deleteRecipeById(recipe.getRecipeID());

        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertEquals(3, actualRecipeList.size());

        System.out.println("Finished testDeleteRecipeById");
    }
}
