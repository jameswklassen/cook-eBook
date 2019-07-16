package com.cook_ebook.tests.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;

public class RecipeTest {

    @Before
    public void setup() {
        System.out.println("Starting test for Recipe");
    }

    @Test
    public void testCreateRecipeWithoutTag() {
        System.out.println("\nStarting testCreateRecipeWithoutTag");

        int expectNumber = 0;

        Recipe recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true);

        assertEquals("Cookies", recipe.getRecipeTitle());
        assertEquals("These cookies are amazing", recipe.getRecipeDescription());
        assertEquals("Flour, Eggs, Butter", recipe.getRecipeIngredients());
        assertEquals(60, recipe.getRecipeCookingTime());
        assertEquals("", recipe.getRecipeImage());
        assertEquals(expectNumber, recipe.getRecipeTagList().size());
        assertEquals(true, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithoutTag");
    }

    @Test
    public void testCreateRecipeWithATag() {
        System.out.println("\nStarting testCreateRecipeWithATag");

        int expectNumber = 0;

        Recipe recipe = new Recipe("Cheese Cake",
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
        assertEquals("", recipe.getRecipeImage());
        assertEquals(expectNumber + 1, recipe.getRecipeTagList().size());
        assertTrue(recipe.getRecipeTagList().contains(new RecipeTag("sugar")));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithATag");
    }

    @Test
    public void testCreateRecipeWithMultipleDifferentTags() {
        System.out.println("\nStarting testCreateRecipeWithMultipleDifferentTags");

        int expectNumber = 0;

        Recipe recipe = new Recipe("Cheese Cake",
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
        assertEquals("", recipe.getRecipeImage());
        assertEquals(expectNumber + 2, recipe.getRecipeTagList().size());
        assertTrue(recipe.getRecipeTagList().contains(new RecipeTag("sugar")));
        assertTrue(recipe.getRecipeTagList().contains(new RecipeTag("egg")));
        assertFalse(recipe.getRecipeTagList().contains(new RecipeTag("hahahahaha")));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithMultipleDifferentTags");
    }

    @Test
    public void testCreateRecipeWithMultipleSameTags() {
        System.out.println("\nStarting testCreateRecipeWithMultipleSameTags");

        int expectNumber = 0;

        Recipe recipe = new Recipe("Cheese Cake",
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
        assertEquals("", recipe.getRecipeImage());
        assertEquals(expectNumber + 2, recipe.getRecipeTagList().size());
        assertTrue(recipe.getRecipeTagList().contains(new RecipeTag("sugar")));
        assertTrue(recipe.getRecipeTagList().contains(new RecipeTag("egg")));
        assertEquals(false, recipe.getRecipeIsFavourite());

        System.out.println("Finished testCreateRecipeWithMultipleSameTags");
    }

    @Test
    public void testDeleteRecipeTag() {
        System.out.println("\nStarting testDeleteRecipeTag");

        int expectNumber = 0;

        Recipe recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true);

        recipe.deleteRecipeTag(new RecipeTag("sugar"));
        assertEquals(expectNumber, recipe.getRecipeTagList().size());

        recipe.addRecipeTag(new RecipeTag("sugar"));
        recipe.addRecipeTag(new RecipeTag("egg"));
        assertEquals(expectNumber + 2, recipe.getRecipeTagList().size());

        recipe.deleteRecipeTag(new RecipeTag("sugar"));
        assertEquals(expectNumber + 1, recipe.getRecipeTagList().size());

        System.out.println("Finished testDeleteRecipeTag");
    }

    @Test
    public void testSetRecipeTitle() {
        System.out.println("\nStarting testSetRecipeTitle");

        String expectTitle = "I have been changed!";

        Recipe recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true);

        String originalTitle = recipe.getRecipeTitle();
        recipe.setRecipeTitle(expectTitle);

        assertNotEquals(originalTitle, expectTitle);
        assertEquals(expectTitle, recipe.getRecipeTitle());

        System.out.println("Finished testSetRecipeTitle");
    }

    @Test
    public void testSetRecipeDescription() {
        System.out.println("\nStarting testSetRecipeDescription");

        String expectDescription = "I have been changed!";

        Recipe recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true);

        String originaDescription = recipe.getRecipeDescription();
        recipe.setRecipeDescription(expectDescription);

        assertNotEquals(originaDescription, recipe.getRecipeDescription());
        assertEquals(expectDescription, recipe.getRecipeDescription());

        System.out.println("Finished testSetRecipeDescription");
    }

    @Test
    public void testSetRecipeIngredients() {
        System.out.println("\nStarting testSetRecipeIngredients");

        String expectIngredients = "I have been changed!";

        Recipe recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true);

        String originalIngredients = recipe.getRecipeIngredients();
        recipe.setRecipeIngredients(expectIngredients);

        assertNotEquals(originalIngredients, recipe.getRecipeIngredients());
        assertEquals(expectIngredients, recipe.getRecipeIngredients());

        System.out.println("Finished testSetRecipeIngredients");
    }

    @Test
    public void testSetRecipeCookingTime() {
        System.out.println("\nStarting testSetRecipeCookingTime");

        int expectCookingTime = 999999;

        Recipe recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true);

        int originalCookingTime = recipe.getRecipeCookingTime();
        recipe.setRecipeCookingTime(expectCookingTime);

        assertNotEquals(originalCookingTime, recipe.getRecipeCookingTime());
        assertEquals(expectCookingTime, recipe.getRecipeCookingTime());

        System.out.println("Finished testSetRecipeCookingTime");
    }

    @Test
    public void testSetRecipeIsFavourite() {
        System.out.println("\nStarting testSetRecipeIsFavourite");

        Recipe recipe = new Recipe("Cookies",
                "These cookies are amazing",
                "Flour, Eggs, Butter",
                60,
                "",
                true);

        boolean originalIsFavourite = recipe.getRecipeIsFavourite();
        boolean expectIsFavourite = !originalIsFavourite;
        recipe.setRecipeIsFavourite(expectIsFavourite);

        assertNotEquals(originalIsFavourite, recipe.getRecipeIsFavourite());
        assertEquals(expectIsFavourite, recipe.getRecipeIsFavourite());

        System.out.println("Finished testSetRecipeIsFavourite");
    }
}
