package com.cook_ebook.logic;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.comparators.AscendingDateComparator;
import com.cook_ebook.logic.comparators.AscendingTitleComparator;
import com.cook_ebook.logic.comparators.DescendingDateComparator;
import com.cook_ebook.logic.comparators.DescendingTitleComparator;
import com.cook_ebook.logic.exceptions.InvalidRecipeException;
import com.cook_ebook.logic.exceptions.InvalidRecipeTitle;
import com.cook_ebook.logic.exceptions.NonPositiveCookingTimeException;
import com.cook_ebook.logic.exceptions.NotATimeException;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipePersistence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

public class RecipeHandler {

    private RecipePersistence dataAccessRecipe;
    private List<String> filters;
    private boolean favourite;
    private Comparator <Recipe> sort;

    public RecipeHandler(boolean forProduction) {
        //default get recipeList sorted by date in ascending order
        dataAccessRecipe = Services.getRecipePersistence(forProduction);
        sort = new DescendingDateComparator();
        filters = new ArrayList<>();
        favourite = false;
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

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public void resetSort() {
        sort = new DescendingDateComparator();
    }

    public void resetFilter() {
        filters = new ArrayList<>();
    }

    public void resetFavourite() {
        this.favourite = false;
    }

    public Comparator<Recipe> getSort() {
        return sort;
    }

    public List<String> getFilter() {
        return filters;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = dataAccessRecipe.getRecipeList();
        if(favourite) {
            recipeList = getRecipeListByFavourite(favourite);
        }
        Collections.sort(recipeList, sort);
        return recipeList;
    }

    // will be changed to use database queries
    public List<Recipe> filter(String[] tagList, boolean[] checkedArray) {
        for (int i = 0; i < checkedArray.length; i++) {
            if (checkedArray[i]) {
                setFilter(tagList[i]);
            }
        }

        //Apply the actual filtering operation
        List<Recipe> allRecipes = getAllRecipes();
        List<String> filters = getFilter();

        if (filters.size() > 0) {
            for (Iterator<Recipe> iter = allRecipes.iterator(); iter.hasNext(); ) {
                Recipe next = iter.next();

                boolean hasTagFromFilter = false;

                for (String tag : filters)
                    if (next.getRecipeTagList().contains(new RecipeTag(tag)))
                        hasTagFromFilter = true;

                if (!hasTagFromFilter)
                    iter.remove();
            }
        }
        return allRecipes;
    }

    public Recipe getRecipeById(int recipeId) throws InvalidRecipeException {
        return dataAccessRecipe.getRecipeById(recipeId);
    }

    public List<Recipe> getRecipeListByCookingTime(int cookingTime) {
        return dataAccessRecipe.getRecipeListByCookingTime(cookingTime);
    }

    public List<Recipe> getRecipeListByTagName(String tagName) {
        return dataAccessRecipe.getRecipeListByTagName(tagName);
    }

    public List<Recipe> getRecipeListByTagId(int tagId) {
        return dataAccessRecipe.getRecipeListByTagId(tagId);
    }

    public List<Recipe> getRecipeListByTag(RecipeTag tag) {
        return dataAccessRecipe.getRecipeListByTag(tag);
    }

    public List<Recipe> getRecipeListByFavourite(boolean isFavourite) {
        return dataAccessRecipe.getRecipeListByFavourite(isFavourite);
    }

    public Recipe insertRecipe(Recipe recipe) throws InvalidRecipeException {
        return dataAccessRecipe.insertRecipe(recipe);
    }

    // should throw an exception for an invalid recipe
    // should take care if two recipes have same Id
    public Recipe updateRecipe(Recipe newRecipe) throws InvalidRecipeException {
        return dataAccessRecipe.updateRecipe(newRecipe);
    }

    public void deleteRecipe(Recipe recipe) throws InvalidRecipeException {
        dataAccessRecipe.deleteRecipe(recipe);
    }

    public void deleteRecipeById(int recipeId) throws InvalidRecipeException {
        dataAccessRecipe.deleteRecipeById(recipeId);
    }
}
