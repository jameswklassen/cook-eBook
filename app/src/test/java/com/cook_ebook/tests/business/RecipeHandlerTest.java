package com.cook_ebook.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTagSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipeHandlerTest {

    private RecipeHandler recipeHandler;

    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    @Before
    public void setUp() {
        System.out.println("Starting test for RecipeHandler");

        recipeHandler = new RecipeHandler();

        recipe1 = new Recipe(1,
                "cheese cake",
                "cheese cake description",
                "egg, cream cheese",
                30,
                "cheese cake images",
                new RecipeTagSet(),
                false,
                new Date());

        recipe2 = new Recipe(2,
                "brownies",
                "brownies description",
                "flour, baking power",
                20,
                "brownies images",
                new RecipeTagSet("cake"),
                false,
                new Date());

        recipe3 = new Recipe(3,
                "pasta",
                "pasta description",
                "egg, water",
                10,
                "pasta images",
                new RecipeTagSet(),
                true,
                new Date());

        recipe4 = new Recipe(4,
                "pasta2",
                "pasta2 description",
                "egg, water",
                10,
                "pasta2 images",
                new RecipeTagSet(),
                false,
                new Date());
    }

    @Test
    public void testCreateRecipeList() {
        System.out.println("\nStarting testCreateRecipeList");
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertNotNull(actualRecipeList);

        System.out.println("Finished testCreateRecipeList");
    }

    @Test
    public void testGetAllRecipes() {
        System.out.println("\nStarting testGetAllRecipes");
        List<Recipe> testRecipeList = new ArrayList<>();
        testRecipeList.add(recipe1);
        testRecipeList.add(recipe2);
        testRecipeList.add(recipe3);
        testRecipeList.add(recipe4);

        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertNotNull(actualRecipeList);
        assertEquals(4, actualRecipeList.size());

        int trackNum = 0;
        for(int i = 0; i < actualRecipeList.size(); i ++) {
            int index;
            index = testRecipeList.indexOf(actualRecipeList.get(i));
            if(index < 0) {
                break;
            } else {
                trackNum ++;
            }
        }
        assertEquals(4, trackNum);

        assertEquals(1, actualRecipeList.get(0).getRecipeID());
        assertEquals(2, actualRecipeList.get(1).getRecipeID());
        assertEquals(3, actualRecipeList.get(2).getRecipeID());
        assertEquals(4, actualRecipeList.get(3).getRecipeID());

        System.out.println("Finished testGetAllRecipes");
    }

    @Test
    public void testSortRecipeListByDescendingDate() {
        // this is a very basic simply test, just want to make sure the function is good
        // it will be updated after we have true database
        System.out.println("\nStarting testSortRecipeListByDescendingDate");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByDescendingDate();

        assertEquals(4, actualRecipeList.get(0).getRecipeID());
        assertEquals(3, actualRecipeList.get(1).getRecipeID());
        assertEquals(2, actualRecipeList.get(2).getRecipeID());
        assertEquals(1, actualRecipeList.get(3).getRecipeID());

        System.out.println("Finished testSortRecipeListByDescendingDate");
    }

    @Test
    public void testGetRecipeById() {
        System.out.println("\nStarting testGetRecipeById");
        Recipe targetRecipe1 = recipeHandler.getRecipeById(1);

        assertTrue(targetRecipe1.equals(recipe1));

        System.out.println("Finished testGetRecipeById");
    }

    @Test
    public void testGetRecipeListByCookingTime() {
        System.out.println("\nStarting testGetRecipeListByCookingTime");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByCookingTime(10);

        assertEquals(2, actualRecipeList.size());
        assertEquals(3, actualRecipeList.get(0).getRecipeID());
        assertEquals(4, actualRecipeList.get(1).getRecipeID());

        System.out.println("Finished testGetRecipeListByCookingTime");
    }

    @Test
    public void testGetRecipeListByTag() {
        System.out.println("\nStarting testGetRecipeListByTag");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByTag("cake");

        assertEquals(1, actualRecipeList.size());
        assertEquals(2, actualRecipeList.get(0).getRecipeID());

        System.out.println("Finished testGetRecipeListByTag");
    }

    @Test
    public void testGetRecipeListByFavourite() {
        System.out.println("\nStarting testGetRecipeListByFavourite");
        List<Recipe> actualRecipeList = recipeHandler.getRecipeListByFavourite(false);

        assertEquals(3, actualRecipeList.size());
        assertEquals(1, actualRecipeList.get(0).getRecipeID());
        assertEquals(2, actualRecipeList.get(1).getRecipeID());
        assertEquals(4, actualRecipeList.get(2).getRecipeID());

        System.out.println("Finished testGetRecipeListByFavourite");
    }

    @Test
    public void testGetRecipeListByDate() {
        System.out.println("\nStarting testGetRecipeListByDate");
        // this test will be modified after we have true database,
        // because all recipe date are to be assigned as current date
        System.out.println("Finished testGetRecipeListByDate");
    }

    @Test
    public void testInsertRecipe() {
        System.out.println("\nStarting testInsertRecipe");

        recipeHandler.insertRecipe(new Recipe(5,
                "pasta3",
                "pasta3 description",
                "egg, water",
                10,
                "pasta3 images",
                new RecipeTagSet(),
                false,
                new Date()));
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertEquals(5, actualRecipeList.size());
        assertEquals(1, actualRecipeList.get(0).getRecipeID());
        assertEquals(2, actualRecipeList.get(1).getRecipeID());
        assertEquals(3, actualRecipeList.get(2).getRecipeID());
        assertEquals(4, actualRecipeList.get(3).getRecipeID());
        assertEquals(5, actualRecipeList.get(4).getRecipeID());

        //delete the insert recipe
        recipeHandler.deleteRecipeById(5);

        System.out.println("Finished testInsertRecipe");
    }

    @Test
    public void testUpdateRecipe() {
        System.out.println("\nStarting testUpdateRecipe");
        recipeHandler.updateRecipe(new Recipe(4,
                "pasta22",
                "pasta22 description",
                "egg, water, pepper",
                40,
                "pasta22 images",
                new RecipeTagSet("pasta"),
                true,
                new Date()));
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertEquals(4, actualRecipeList.size());
        assertEquals(1, actualRecipeList.get(0).getRecipeID());
        assertEquals(2, actualRecipeList.get(1).getRecipeID());
        assertEquals(3, actualRecipeList.get(2).getRecipeID());
        assertEquals(4, actualRecipeList.get(3).getRecipeID());

        assertEquals("pasta22", actualRecipeList.get(3).getRecipeTitle());
        assertEquals("pasta22 description", actualRecipeList.get(3).getRecipeDescription());
        assertEquals("egg, water, pepper", actualRecipeList.get(3).getRecipeIngredients());
        assertEquals(40, actualRecipeList.get(3).getRecipeCookingTime());
        assertEquals("pasta22", actualRecipeList.get(3).getRecipeTitle());

        assertEquals("pasta",
                actualRecipeList.get(3).getRecipeTagSet().toArray(new String[actualRecipeList.get(3).getRecipeTagSet().size()])[0]);
        assertTrue(actualRecipeList.get(3).getRecipeIsFavourite());
        //need one more test about date after we have true database

        //revert update
        recipeHandler.updateRecipe(new Recipe(4,
                "pasta2",
                "pasta2 description",
                "egg, water",
                10,
                "pasta2 images",
                new RecipeTagSet(),
                false,
                new Date()));

        System.out.println("Finished testUpdateRecipe");
    }

    @Test
    public void testDeleteRecipe() {
        System.out.println("\nStarting testDeleteRecipe");

        recipeHandler.deleteRecipe(new Recipe(1,
                "cheese cake",
                "cheese cake description",
                "egg, cream cheese",
                30,
                "cheese cake images",
                new RecipeTagSet(),
                false,
                new Date()));

        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertEquals(3, actualRecipeList.size());
        assertEquals(2, actualRecipeList.get(0).getRecipeID());
        assertEquals(3, actualRecipeList.get(1).getRecipeID());
        assertEquals(4, actualRecipeList.get(2).getRecipeID());

        //revert delete
        recipeHandler.insertRecipe(new Recipe(1,
                "cheese cake",
                "cheese cake description",
                "egg, cream cheese",
                30,
                "cheese cake images",
                new RecipeTagSet(),
                false,
                new Date()));

        System.out.println("Finished testDeleteRecipe");
    }

    @Test
    public void testDeleteRecipeById() {
        System.out.println("\nStarting testDeleteRecipeById");

        recipeHandler.deleteRecipeById(1);

        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertEquals(3, actualRecipeList.size());
        assertEquals(2, actualRecipeList.get(0).getRecipeID());
        assertEquals(3, actualRecipeList.get(1).getRecipeID());
        assertEquals(4, actualRecipeList.get(2).getRecipeID());

        //revert delete
        recipeHandler.insertRecipe(new Recipe(1,
                "cheese cake",
                "cheese cake description",
                "egg, cream cheese",
                30,
                "cheese cake images",
                new RecipeTagSet(),
                false,
                new Date()));

        System.out.println("Finished testDeleteRecipeById");
    }
}
