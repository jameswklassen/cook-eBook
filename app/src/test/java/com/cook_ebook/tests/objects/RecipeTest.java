package com.cook_ebook.tests.objects;

import org.junit.Test;
import static org.junit.Assert.*;

import com.cook_ebook.objects.Recipe;

import java.util.*;

public class RecipeTest {
    @Test
    public void testCreateRecipe() {
        Recipe recipe;

        System.out.println("\nStarting testCreateRecipe");

        Set<String> tags = new HashSet<>();
        tags.add("sweet");
        tags.add("desert");

        recipe = new Recipe(
                1,
                "Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                tags,
                true);

        assertEquals(1, recipe.getRecipeID());
        assertEquals("Cookies", recipe.getRecipeTitle());
        assertEquals("These cookies are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(60, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImages());
        assertEquals(true, tags.equals(recipe.getRecipeTags()));
        assertEquals(true, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipe");
    }

    @Test
    public void testAddRecipeTags() {
        System.out.println("\nStarting testAddRecipeTags");

        Recipe recipe = new Recipe(1);

        Set<String> testSet = new HashSet<>();

        recipe.addRecipeTag("sweet");
        testSet.add("sweet");

        assertEquals(true, testSet.equals(recipe.getRecipeTags()));

        System.out.println("Finished testAddRecipeTags");
    }

    @Test
    public void testRemoveRecipeTags() {
        System.out.println("\nStarting testRemoveRecipeTags");

        Recipe recipe = new Recipe(1);
        recipe.addRecipeTag("sweet");

        Set<String> testSet = new HashSet<>();
        testSet.add("sweet");


        assertEquals(true, testSet.equals(recipe.getRecipeTags()));

        recipe.removeRecipeTag("sweet");

        assertEquals(true, recipe.getRecipeTags().isEmpty());

        System.out.println("Finished testRemoveRecipeTags");
    }
}
