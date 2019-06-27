package com.cook_ebook.logic;

import com.cook_ebook.application.Services;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.persistence.RecipePersistence;

import java.util.Date;
import java.util.List;

public class RecipeHandler {

    private RecipePersistence dataAccessRecipe;

    public RecipeHandler() {
        //default get recipeList sorted by date in ascending order
        dataAccessRecipe = Services.getRecipePersistence();
    }

    public List<Recipe> getAllRecipes() {
        return dataAccessRecipe.getRecipeList();
    }

    public List<Recipe> getRecipeListByDescendingDate() {
        return dataAccessRecipe.getRecipeListByDescendingDate();
    }

    // should throw exception if recipe doesn't exist
    public Recipe getRecipeById(int recipeId) {
        return dataAccessRecipe.getRecipeById(recipeId);
    }

    // should throw exception if recipe doesn't exist
    public List<Recipe> getRecipeListByCookingTime(int cookingTime) {
        return dataAccessRecipe.getRecipeListByCookingTime(cookingTime);
    }

    // should throw exception if recipe doesn't exist
    public List<Recipe> getRecipeListByTag(String tag) {
        return dataAccessRecipe.getRecipeListByTag(tag);
    }

    // should throw exception if recipe doesn't exist
    public List<Recipe> getRecipeListByFavourite(boolean isFavourite){
        return dataAccessRecipe.getRecipeListByFavourite(isFavourite);
    }

    // should throw exception if recipe doesn't exist
    public List<Recipe> getRecipeListByDate(Date date) {
        return dataAccessRecipe.getRecipeListByDate(date);
    }

    // should throw an exception for an invalid recipe
    public Recipe insertRecipe(Recipe recipe) {
        return dataAccessRecipe.insertRecipe(recipe);
    }

    // should throw an exception for an invalid recipe
    // should take care if two recipes have same Id
    public Recipe updateRecipe(Recipe newRecipe) {
        return dataAccessRecipe.updateRecipe(newRecipe);
    }

    // should throw an exception if the recipe doesn't exist
    public void deleteRecipe(Recipe recipe) {
        dataAccessRecipe.deleteRecipe(recipe);
    }

    // should throw an exception if the recipe doesn't exist
    public void deleteRecipeById(int recipeId) {
        dataAccessRecipe.deleteRecipeById(recipeId);
    }
}
