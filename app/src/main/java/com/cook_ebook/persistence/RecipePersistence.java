package com.cook_ebook.persistence;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;

import java.util.*;

public interface RecipePersistence {

    List<Recipe> getRecipeList(); //default sorted by date in ascending order

    List<Recipe> getRecipeListByDescendingDate();

    Recipe getRecipeById(int recipeId);

    List<Recipe> getRecipeListByCookingTime(int cookingTime);

    List<Recipe> getRecipeListByTagName(String tagName);

    List<Recipe> getRecipeListByTagId(int tagId);

    List<Recipe> getRecipeListByTag(RecipeTag tag);

    List<Recipe> getRecipeListByFavourite(boolean isFavourite);

    List<Recipe> getRecipeListByDate(Date date);

    Recipe insertRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe newRecipe);

    void deleteRecipe(Recipe recipe);

    void deleteRecipeById(int recipeId);

    void deleteTagInRecipeTagByTagId();

    void deleteRecipeTagByTagName();

    void deleteRecipeTag ();
}
