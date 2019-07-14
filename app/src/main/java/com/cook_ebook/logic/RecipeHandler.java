package com.cook_ebook.logic;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.comparators.OldestDateComparator;
import com.cook_ebook.logic.comparators.AscendingTitleComparator;
import com.cook_ebook.logic.comparators.LatestDateComparator;
import com.cook_ebook.logic.comparators.DescendingTitleComparator;
import com.cook_ebook.logic.exceptions.InvalidRecipeException;
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
        sort = new LatestDateComparator();
        filters = new ArrayList<>();
        favourite = false;
    }

    public void setSort(String newSort) {
        switch(newSort)
        {
            case "Date-Oldest":
                sort = new OldestDateComparator();
                break;
            case "Date-Latest":
                sort = new LatestDateComparator();
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
        sort = new LatestDateComparator();
    }

    public void resetFilter() {
        filters = new ArrayList<>();
    }

    public void resetFavourite() {
        this.favourite = false;
    }

    public boolean getFavourite() {
        return this.favourite;
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
        }else
        {
            recipeList = getRecipeListByFilter(recipeList);
        }
        Collections.sort(recipeList, sort);
        return recipeList;
    }

    public void filter(String[] tagList, boolean[] checkedArray) {
        for (int i = 0; i < checkedArray.length; i++) {
            if (checkedArray[i]) {
                setFilter(tagList[i]);
            }
        }
    }

    public List<Recipe> getRecipeListByFilter(List<Recipe> recipeList) {

        List<Recipe> filtered = new ArrayList<>(recipeList.size());
        for (Recipe r : recipeList) filtered.add(r);

        List<String> filters = getFilter();

        if (filters.size() > 0) {
            for (Iterator<Recipe> iter = filtered.iterator(); iter.hasNext(); ) {
                Recipe next = iter.next();

                boolean hasTagFromFilter = false;

                for (String tag : filters)
                    if (next.getRecipeTagList().contains(new RecipeTag(tag))) {
                        hasTagFromFilter = true;
                    }

                if (!hasTagFromFilter)
                    iter.remove();
            }
        }
        return filtered;
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
        if(RecipeValidator.validateRecipe(recipe)) {
            return dataAccessRecipe.insertRecipe(recipe);
        } else {
            throw new InvalidRecipeException("The new recipe is invalid!");
        }
    }

    public Recipe updateRecipe(Recipe newRecipe) throws InvalidRecipeException {
        if(RecipeValidator.validateRecipe(newRecipe)) {
            return dataAccessRecipe.updateRecipe(newRecipe);
        } else {
            throw new InvalidRecipeException("The new recipe is invalid!");
        }
    }

    public void deleteRecipe(Recipe recipe) throws InvalidRecipeException {
        if(RecipeValidator.validateRecipe(recipe)) {
            dataAccessRecipe.deleteRecipe(recipe);
        } else {
            throw new InvalidRecipeException("The new recipe is invalid!");
        }
    }

    public void deleteRecipeById(int recipeId) throws InvalidRecipeException {
        dataAccessRecipe.deleteRecipeById(recipeId);
    }
}
