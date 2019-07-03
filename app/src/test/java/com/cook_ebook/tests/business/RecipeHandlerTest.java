package com.cook_ebook.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.logic.comparators.AscendingDateComparator;
import com.cook_ebook.logic.comparators.DescendingDateComparator;
import com.cook_ebook.logic.comparators.AscendingTitleComparator;
import com.cook_ebook.logic.comparators.DescendingTitleComparator;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;

import java.util.Date;
import java.util.List;

public class RecipeHandlerTest {

    private RecipeHandler recipeHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for RecipeHandler");
        recipeHandler = new RecipeHandler(false);
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
    public void testDescendingDateCompare(){
        System.out.println("\nStarting testDescendingDateCompare");
        DescendingDateComparator testComparator = new DescendingDateComparator();
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();
        int result = testComparator.compare(actualRecipeList.get(0), actualRecipeList.get(1));
        assertTrue(result <= 0);
        System.out.println("Finished testDescendingDateCompare");
    }

    @Test
    public void testAscendingDateCompare(){
        System.out.println("\nStarting testAscendingDateCompare");
        AscendingDateComparator testComparator = new AscendingDateComparator();
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();
        int result = testComparator.compare(actualRecipeList.get(0), actualRecipeList.get(1));
        assertTrue(result >= 0);
        System.out.println("Finished testAscendingDateCompare");
    }

    @Test
    public void testDescendingTitleCompare(){
        System.out.println("\nStarting testDescendingTitleCompare");
        DescendingTitleComparator testComparator = new DescendingTitleComparator();
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();
        int result = testComparator.compare(actualRecipeList.get(0), actualRecipeList.get(1));
        assertTrue(result <= 0);
        System.out.println("Finished testDescendingTitleCompare");
    }

    @Test
    public void testAscendingTitleCompare(){
        System.out.println("\nStarting testAscendingTitleCompare");
        AscendingTitleComparator testComparator = new AscendingTitleComparator();
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();
        int result = testComparator.compare(actualRecipeList.get(0), actualRecipeList.get(1));
        assertTrue(result >= 0);
        System.out.println("Finished testAscendingTitleCompare");
    }

    @Test
    public void testAscendingDateSort(){
        System.out.println("\nStarting testAscendingDateSort");
        recipeHandler.setSort("Date-Ascending");
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        for(int i = 1; i < actualRecipeList.size(); i++) {
            Recipe r1 = actualRecipeList.get(i-1);
            Recipe r2 = actualRecipeList.get(i);
            Date d1 = r1.getRecipeDate();
            Date d2 = r2.getRecipeDate();
            assertTrue(d1.after(d2) || d1.equals(d2));
        }
        System.out.println("Finished testAscendingDateSort");
    }

    @Test
    public void testDescendingDateSort(){
        System.out.println("\nStarting testDescendingDateSort");
        recipeHandler.setSort("Date-Descending");
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        for(int i = 1; i < actualRecipeList.size(); i++) {
            Recipe r1 = actualRecipeList.get(i-1);
            Recipe r2 = actualRecipeList.get(i);
            Date d1 = r1.getRecipeDate();
            Date d2 = r2.getRecipeDate();
            assertTrue(d1.before(d2) || d1.equals(d2));
        }
        System.out.println("Finished testDescendingDateSort");
    }

    @Test
    public void testAscendingTitleSort(){
        System.out.println("\nStarting testAscendingTitleSort");
        recipeHandler.setSort("Title-Ascending");
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        for(int i = 1; i < actualRecipeList.size(); i++) {
            Recipe r1 = actualRecipeList.get(i-1);
            Recipe r2 = actualRecipeList.get(i);
            String s1 = r1.getRecipeTitle();
            String s2 = r2.getRecipeTitle();
            assertTrue(s1.compareTo(s2) < 0 || s1.equals(s2));
        }
        System.out.println("Finished testAscendingTitleSort");
    }

    @Test
    public void testDescendingTitleSort(){
        System.out.println("\nStarting testDescendingTitleSort");
        recipeHandler.setSort("Title-Descending");
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        for(int i = 1; i < actualRecipeList.size(); i++) {
            Recipe r1 = actualRecipeList.get(i-1);
            Recipe r2 = actualRecipeList.get(i);
            String s1 = r1.getRecipeTitle();
            String s2 = r2.getRecipeTitle();
            assertTrue(s1.compareTo(s2) > 0 || s1.equals(s2));
        }
        System.out.println("Finished testDescendingTitleSort");
    }

    @Test
    public void testGetRecipeById() {
        System.out.println("\nStarting testGetRecipeById");
        Recipe targetRecipe1 = recipeHandler.getRecipeById(3);

        //this method will be implenmented after we have true database

        System.out.println("Finished testGetRecipeById");
    }

    @Test
    public void testResetFilter() {
        System.out.println("\nStarting testResetFilter");
        recipeHandler.resetFilter();
        int length = recipeHandler.getFilter().size();
        assertEquals(0, length);
        System.out.println("Finished testResetFilter");
    }

    @Test
    public void testResetSort() {
        System.out.println("\nStarting testResetSort");
        recipeHandler.resetSort();
        assertTrue(recipeHandler.getSort() instanceof DescendingDateComparator);
        System.out.println("Finished testResetSort");
    }

    @Test
    public void testSetFilter() {
        System.out.println("\nStarting testSetFilter");
        recipeHandler.setFilter("Cake");
        int length = recipeHandler.getFilter().size();
        assertEquals(1, length);

        recipeHandler.setFilter("Chicken");
        length = recipeHandler.getFilter().size();
        assertEquals(2, length);

        recipeHandler.setFilter("Cake");
        length = recipeHandler.getFilter().size();
        assertEquals(1, length);

        System.out.println("Finished testSetFilter");
    }

    @Test
    public void testSetSort() {
        System.out.println("\nStarting testSetSort");
        recipeHandler.setSort("Date-Ascending");
        assertTrue(recipeHandler.getSort() instanceof AscendingDateComparator);

        recipeHandler.setSort("Date-Descending");
        assertTrue(recipeHandler.getSort() instanceof DescendingDateComparator);

        recipeHandler.setSort("Title-Ascending");
        assertTrue(recipeHandler.getSort() instanceof AscendingTitleComparator);

        recipeHandler.setSort("Title-Descending");
        assertTrue(recipeHandler.getSort() instanceof DescendingTitleComparator);

        System.out.println("Finished testSetSort");
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
    public void testGetRecipeListByTagName() {
        System.out.println("\nStarting testGetRecipeListByTagName");

        List<Recipe> actualRecipeList1 = recipeHandler.getRecipeListByTagName("cake");
        assertEquals(1, actualRecipeList1.size());

        List<Recipe> actualRecipeList2 = recipeHandler.getRecipeListByTagName("dessert");
        assertEquals(2, actualRecipeList2.size());

        System.out.println("Finished testGetRecipeListByTagName");
    }

    @Test
    public void testGetRecipeListByTagId() {
        System.out.println("\nStarting testGetRecipeListByTagId");

        // this test will be implemented after we have true database
        List<Recipe> actualRecipeList1 = recipeHandler.getRecipeListByTagId(-1);
        assertEquals(0, actualRecipeList1.size());

        System.out.println("Finished testGetRecipeListByTagId");
    }

    @Test
    public void testGetRecipeListByTag() {
        System.out.println("\nStarting testGetRecipeListByTag");

        List<Recipe> actualRecipeList1 = recipeHandler.getRecipeListByTag(new RecipeTag("cake"));
        assertEquals(1, actualRecipeList1.size());

        List<Recipe> actualRecipeList2 = recipeHandler.getRecipeListByTag(new RecipeTag("dessert"));
        assertEquals(2, actualRecipeList2.size());

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
    public void testInsertRecipe() {
        System.out.println("\nStarting testInsertRecipe");

        int initialSize = recipeHandler.getAllRecipes().size();
        int expectedSize = initialSize + 1;

        Recipe recipe = new Recipe("pasta3",
                "pasta3 description",
                "egg, water",
                10,
                "pasta3 images",
                true);

        recipeHandler.insertRecipe(recipe);

        assertEquals(expectedSize, recipeHandler.getAllRecipes().size());

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

        List<Recipe> initialList = recipeHandler.getAllRecipes();

        int initialSize = initialList.size();
        int expectedSize = initialSize - 1;
        Recipe recipe = initialList.get(0);

        recipeHandler.deleteRecipe(recipe);

        assertEquals(expectedSize, recipeHandler.getAllRecipes().size());
        assertEquals(recipe, recipeHandler.insertRecipe(recipe));

        System.out.println("Finished testDeleteRecipe");
    }

    @Test
    public void testDeleteRecipeById() {
        System.out.println("\nStarting testDeleteRecipeById");

        List<Recipe> initialRecipeList = recipeHandler.getAllRecipes();

        int initialSize = initialRecipeList.size();
        int expectedSize = initialSize - 1;
        Recipe recipe = initialRecipeList.get(0);

        recipeHandler.deleteRecipeById(recipe.getRecipeID());

        assertEquals(expectedSize, recipeHandler.getAllRecipes().size());

        System.out.println("Finished testDeleteRecipeById");
    }
}
