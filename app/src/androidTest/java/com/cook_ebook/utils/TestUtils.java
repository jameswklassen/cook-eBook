package com.cook_ebook.utils;

import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.logic.RecipeTagHandler;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;

public class TestUtils {
    private RecipeHandler recipeHandler;
    private RecipeTagHandler recipeTagHandler;

    public TestUtils() {
        recipeHandler = new RecipeHandler(true);
        recipeTagHandler = new RecipeTagHandler(true);
    }

    public Recipe getRecipeByPosition(int recipePosition) {
        return recipeHandler.getAllRecipes().get(recipePosition);
    }

    public int getTotalNumberRecipes() {
        return recipeHandler.getAllRecipes().size();
    }

    public Recipe getRecipeById(int targetRecipeId) {
        return recipeHandler.getRecipeById(targetRecipeId);
    }

    public void insertRecipe(Recipe newRecipe) {
        recipeHandler.insertRecipe(newRecipe);
    }

    public void deleteRecipe(Recipe targetRecipe) {
        recipeHandler.deleteRecipe(targetRecipe);
    }

    public void deleteRecipeById(int targetRecipeId) {
        recipeHandler.deleteRecipeById(targetRecipeId);
    }

    public void updateRecipe(Recipe targetRecipe) {
        recipeHandler.updateRecipe(targetRecipe);
    }

    public RecipeTag getRecipeTagByPosition(int recipeTagPosition) {
        return recipeTagHandler.getAllRecipeTags().get(recipeTagPosition);
    }

    public int getTotalNumberRecipeTags() {
        return recipeTagHandler.getAllRecipeTags().size();
    }

    public void insertRecipeTag(RecipeTag newRecipeTag) {
        recipeTagHandler.insertOneTag(newRecipeTag);
    }
}
