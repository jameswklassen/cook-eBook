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
                false);

        recipe.addRecipeTag(new RecipeTag("sugar"));

        assertEquals("Cheese Cake", recipe.getRecipeTitle());
        assertEquals("These cakes are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(330, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImages());
        assertEquals(1, recipe.getRecipeTagSet().size());
        assertTrue(recipe.doesTagBelongsToRecipe(new RecipeTag("sugar")));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithATag");
    }

    @Test
    public void testCreateRecipeWithMultipleDifferentTags() {
        Recipe recipe;

        System.out.println("\nStarting testCreateRecipeWithMultipleDifferentTags");

        recipe = new Recipe("Cheese Cake",
                "These cakes are amazing",
                "Flour, Eggs, Butter",
                320,
                "",
                false);

        recipe.addRecipeTag(new RecipeTag("sugar"));
        recipe.addRecipeTag(new RecipeTag("egg"));

        assertEquals("Cheese Cake", recipe.getRecipeTitle());
        assertEquals("These cakes are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(320, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImages());
        assertEquals(2, recipe.getRecipeTagSet().size());
        assertTrue(recipe.doesTagBelongsToRecipe(new RecipeTag("sugar")));
        assertTrue(recipe.doesTagBelongsToRecipe(new RecipeTag("egg")));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithMultipleDifferentTags");
    }

    @Test
    public void testCreateRecipeWithMultipleSameTags() {
        Recipe recipe;

        System.out.println("\nStarting testCreateRecipeWithMultipleSameTags");

        recipe = new Recipe("Cheese Cake",
                "These cakes are amazing",
                "Flour, Eggs, Butter",
                320,
                "",
                false);

        recipe.addRecipeTag(new RecipeTag("sugar"));
        recipe.addRecipeTag(new RecipeTag("egg"));
        recipe.addRecipeTag(new RecipeTag("egg"));

        assertEquals("Cheese Cake", recipe.getRecipeTitle());
        assertEquals("These cakes are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(320, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImages());
        assertEquals(2, recipe.getRecipeTagSet().size());
        assertTrue(recipe.doesTagBelongsToRecipe(new RecipeTag("sugar")));
        assertTrue(recipe.doesTagBelongsToRecipe(new RecipeTag("egg")));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithMultipleSameTags");
    }
}
