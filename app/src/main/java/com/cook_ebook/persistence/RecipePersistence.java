package com.cook_ebook.persistence;

import com.cook_ebook.objects.Recipe;

import java.util.*;

public interface RecipePersistence {

    List<Recipe> getRecipeList(); //default sorted by date in ascending order

    List<Recipe> getRecipeListByDescendingDate();

    Recipe getRecipeById(int recipeId);

    List<Recipe> getRecipeListByCookingTime(int cookingTime);

    List<Recipe> getRecipeListByTag(String tag);

    List<Recipe> getRecipeListByFavourite(boolean isFavourite);

    List<Recipe> getRecipeListByDate(Date date);

    Recipe insertRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);
}
