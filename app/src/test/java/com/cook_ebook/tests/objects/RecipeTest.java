package com.cook_ebook.tests.objects;

import org.junit.Test;
import static org.junit.Assert.*;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;

public class RecipeTest {

    @Test
    public void testCreateRecipeWithoutTag() {
        Recipe recipe;

        System.out.println("\nStarting testCreateRecipeWithoutTag");

        recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                new RecipeTag(),
                true);

        assertEquals("Cookies", recipe.getRecipeTitle());
        assertEquals("These cookies are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(60, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImages());
        assertEquals(0, recipe.getRecipeTagSet().size());
        assertEquals(true, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithoutTag");
    }

    @Test
    public void testCreateRecipeWithATag() {
        Recipe recipe;

        System.out.println("\nStarting testCreateRecipeWithATag");

        recipe = new Recipe("Cheese Cake",
                "These cakes are amazing",
                "Flour, Eggs, Butter",
                330,
                "",
                new RecipeTag("sugar"),
                false);

        assertEquals("Cheese Cake", recipe.getRecipeTitle());
        assertEquals("These cakes are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(330, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImages());
        assertTrue(recipe.getRecipeTagSet().contains("sugar"));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithATag");
    }

    @Test
    public void testCreateRecipeWithMultipleTag() {
        Recipe recipe;

        System.out.println("\nStarting testCreateRecipeWithMultipleTag");

        RecipeTag recipeTag = new RecipeTag();
        recipeTag.addTag("sugar");
        recipeTag.addTag("egg");

        recipe = new Recipe("Cheese Cake",
                "These cakes are amazing",
                "Flour, Eggs, Butter",
                320,
                "",
                recipeTag,
                false);

        assertEquals("Cheese Cake", recipe.getRecipeTitle());
        assertEquals("These cakes are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(320, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImages());
        assertTrue(recipe.getRecipeTagSet().contains("sugar"));
        assertTrue(recipe.getRecipeTagSet().contains("egg"));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithMultipleTag");
    }
}
