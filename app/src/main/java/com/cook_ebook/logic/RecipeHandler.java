package com.cook_ebook.logic;

import com.cook_ebook.logic.comparators.AscendingDateComparator;
import com.cook_ebook.logic.comparators.AscendingTitleComparator;
import com.cook_ebook.logic.comparators.DescendingDateComparator;
import com.cook_ebook.logic.comparators.DescendingTitleComparator;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipePersistence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class RecipeHandler {

    private RecipePersistence dataAccessRecipe;
    private List<String> filters;
    private Comparator <Recipe> sort;

    public RecipeHandler() {
        //default get recipeList sorted by date in ascending order
        dataAccessRecipe = Services.getRecipePersistence();
        sort = new DescendingDateComparator();
        filters = new ArrayList<>();
    }

    public void setSort(String newSort) {
        switch(newSort)
        {
            case "Date-Ascending":
                sort = new AscendingDateComparator();
                break;
            case "Date-Descending":
                sort = new DescendingDateComparator();
                break;
            case "Title-Ascending":
                sort = new AscendingTitleComparator();
                break;
            default :
                sort = new DescendingTitleComparator();
                break;
        }
    }

    public void setFilter(String newFilter) {
        int index = filters.indexOf(newFilter);
        if(index != 0)
            filters.add(newFilter);
        else
            filters.remove(index);
    }

    public void resetSort() {
        sort = new DescendingDateComparator();
    }

    public void resetFilter() {
        filters = new ArrayList<>();
    }

    public Comparator<Recipe> getSort() {
        return sort;
    }

    public List<String> getFilter() {
        return filters;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = dataAccessRecipe.getRecipeList();
        Collections.sort(recipeList, sort);
        return recipeList;
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
    public List<Recipe> getRecipeListByTagName(String tagName) {
        return dataAccessRecipe.getRecipeListByTagName(tagName);
    }

    // should throw exception if recipe doesn't exist
    public List<Recipe> getRecipeListByTagId(int tagId) {
        return dataAccessRecipe.getRecipeListByTagId(tagId);
    }

    // should throw exception if recipe doesn't exist
    public List<Recipe> getRecipeListByTag(RecipeTag tag) {
        return dataAccessRecipe.getRecipeListByTag(tag);
    }

    // should throw exception if recipe doesn't exist
    public List<Recipe> getRecipeListByFavourite(boolean isFavourite){
        return dataAccessRecipe.getRecipeListByFavourite(isFavourite);
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
