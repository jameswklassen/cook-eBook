package com.cook_ebook.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.logic.comparators.OldestDateComparator;
import com.cook_ebook.logic.comparators.LatestDateComparator;
import com.cook_ebook.logic.comparators.AscendingTitleComparator;
import com.cook_ebook.logic.comparators.DescendingTitleComparator;
import com.cook_ebook.logic.exceptions.InvalidRecipeException;
import com.cook_ebook.logic.exceptions.RecipeNotFoundException;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipePersistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        System.out.println("\nStarting testCreateRecipeList");
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();

        assertNotNull(actualRecipeList);
        assertEquals(4, actualRecipeList.size());

        System.out.println("Finished testCreateRecipeList");
    }

    @Test
    public void testLatestDateCompare(){
        System.out.println("\nStarting testLatestDateCompare");
        LatestDateComparator testComparator = new LatestDateComparator();
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();
        Recipe r1 = actualRecipeList.get(0);
        Recipe r2 = actualRecipeList.get(1);
        r1.updateRecipeDate();
        int result = testComparator.compare(r1, r2);
        assertTrue(result <= 0);
        result = testComparator.compare(r2, r1);
        assertTrue(result > 0);
        System.out.println("Finished testLatestDateCompare");
    }

    @Test
    public void testOldestDateCompare(){
        System.out.println("\nStarting testOldestDateCompare");
        OldestDateComparator testComparator = new OldestDateComparator();
        List<Recipe> actualRecipeList = recipeHandler.getAllRecipes();
        Recipe r1 = actualRecipeList.get(0);
        Recipe r2 = actualRecipeList.get(1);
        r1.updateRecipeDate();
        int result = testComparator.compare(r1, r2);
        assertTrue(result >= 0);
        result = testComparator.compare(r2, r1);
        assertTrue(result < 0);
        System.out.println("Finished testOldestDateCompare");
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
        recipeHandler.setSort("Date-Oldest");
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
        recipeHandler.setSort("Date-Latest");
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
        assertNotNull(targetRecipe1);
        Recipe targetRecipe2 = recipeHandler.getRecipeById(3000);
        assertNull(targetRecipe2);
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
        assertTrue(recipeHandler.getSort() instanceof LatestDateComparator);
        System.out.println("Finished testResetSort");
    }

    @Test
    public void testResetFavourite() {
        System.out.println("\nStarting testResetFavourite");
        recipeHandler.resetFavourite();
        assertFalse(recipeHandler.getFavourite());
        System.out.println("Finished testResetFavourite");
    }

    @Test
    public void testResetSearch() {
        System.out.println("\nStarting testResetSearch");
        recipeHandler.resetSearch();
        assertNull(recipeHandler.getSearchString());
        System.out.println("Finished testResetSearch");
    }

    @Test
    public void testSetFavourite() {
        System.out.println("\nStarting testSetFavourite");
        recipeHandler.setFavourite(true);
        assertTrue(recipeHandler.getFavourite());
        List<Recipe> recipeList = recipeHandler.getAllRecipes();
        for (Recipe recipe: recipeList) {
            assertTrue(recipe.getRecipeIsFavourite());
        }
        System.out.println("Finished testSetFavourite");
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

    // this will be updated when we change how filter is
    @Test
    public void testFilter() {
        System.out.println("\nStarting testFilter");
        String [] tags = {"Chicken", "Cake"};
        boolean [] checked = {true, false};
        RecipeTag newTag = new RecipeTag("Chicken");
        recipeHandler.filter(tags, checked);
        List <Recipe> recipeList = recipeHandler.getAllRecipes();
        for (Recipe recipe: recipeList) {
            List<RecipeTag> tagList = recipe.getRecipeTagList();
            assertTrue(tagList.contains(newTag));
        }
        System.out.println("Finished testFilter");
    }

    @Test
    public void testSetSort() {
        System.out.println("\nStarting testSetSort");
        recipeHandler.setSort("Date-Oldest");
        assertTrue(recipeHandler.getSort() instanceof OldestDateComparator);

        recipeHandler.setSort("Date-Latest");
        assertTrue(recipeHandler.getSort() instanceof LatestDateComparator);

        recipeHandler.setSort("Title-Ascending");
        assertTrue(recipeHandler.getSort() instanceof AscendingTitleComparator);

        recipeHandler.setSort("Title-Descending");
        assertTrue(recipeHandler.getSort() instanceof DescendingTitleComparator);

        System.out.println("Finished testSetSort");
    }

    @Test
    public void testSetSearch() {
        System.out.println("\nStarting testSetSearch");

        String[] testSearches = {
                "Test0",
                "tEsT1",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        };

        for(String search : testSearches) {
            recipeHandler.setSearch(search);
            assertEquals(recipeHandler.getSearchString(), search);
        }
        System.out.println("Finished testSetSearch");
    }

    @Test
    public void testSearch() {
        RecipePersistence mockedPersistence = mock(RecipePersistence.class);

        RecipeHandler toTest = new RecipeHandler(mockedPersistence);
        List<Recipe> mockList = new ArrayList<>();
        String[] titles = {
                "zero one two three four",
                "one two three four",
                "two three four",
                "three four",
                "four"
        };

        for(String title : titles)
            mockList.add(new Recipe(title, "a", "a", 1, "a", false));

        // Store a list of searches with known result counts
        HashMap<String, Integer> knownSearches = new HashMap<>();
        knownSearches.put("test string", 0);
        knownSearches.put("er", 1);
        knownSearches.put("ne", 2);
        knownSearches.put("wo", 3);
        knownSearches.put("ee", 4);
        knownSearches.put("ou", 5);

        when(mockedPersistence.getRecipeList()).thenReturn(mockList);

        assertEquals(toTest.getAllRecipes(), mockList);

        for(String searchTerm : knownSearches.keySet()) {
            toTest.setSearch(searchTerm);
            assertEquals(toTest.getAllRecipes().size(), (int)knownSearches.get(searchTerm));
        }

        System.out.println("Finished testSearch");
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
        int expectedSize = initialSize + 2;

        Recipe recipe = recipeHandler.buildRecipe("10", "pasta3","cake","egg, water", "pasta3 description");
        recipeHandler.insertRecipe(recipe);

        Recipe recipe2 = recipeHandler.buildRecipe(1000, "10", "pasta3","cake","egg, water", "pasta3 description", new Date());
        recipeHandler.insertRecipe(recipe2);

        assertEquals(expectedSize, recipeHandler.getAllRecipes().size());
        recipe = recipeHandler.insertRecipe(null);
        assertNull(recipe);

        System.out.println("Finished testInsertRecipe");
    }

    @Test
    public void testUpdateRecipe() {
        System.out.println("\nStarting testUpdateRecipe");

        Recipe recipe = recipeHandler.getAllRecipes().get(0);
        recipe.setRecipeDescription("I'm a test description.");

        recipeHandler.updateRecipe(recipe);
        assertEquals("I'm a test description.", recipeHandler.getAllRecipes().get(0).getRecipeDescription());

        recipe = recipeHandler.updateRecipe(null);
        assertNull(recipe);

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
