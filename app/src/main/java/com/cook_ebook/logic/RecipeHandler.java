package com.cook_ebook.logic;

import android.util.Log;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.comparators.OldestDateComparator;
import com.cook_ebook.logic.comparators.AscendingTitleComparator;
import com.cook_ebook.logic.comparators.LatestDateComparator;
import com.cook_ebook.logic.comparators.DescendingTitleComparator;
import com.cook_ebook.logic.exceptions.InvalidRecipeException;
import com.cook_ebook.logic.exceptions.RecipeNotFoundException;
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
    private String search;
    private Comparator <Recipe> sort;

    public RecipeHandler(boolean forProduction) {
        //default get recipeList sorted by date in ascending order
        dataAccessRecipe = Services.getRecipePersistence(forProduction);
        sort = new LatestDateComparator();
        filters = new ArrayList<>();
        favourite = false;
        search = null;
    }

    public RecipeHandler(RecipePersistence persistence) {
        dataAccessRecipe = persistence;
        sort = new LatestDateComparator();
        filters = new ArrayList<>();
        favourite = false;
        search = null;
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

    public void setSearch(String searchTerm) {
        search = searchTerm;
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

    public void resetSearch() { this.search = null; }

    public boolean getFavourite() {
        return this.favourite;
    }

    public String getSearchString() {
        return search;
    }

    public Comparator<Recipe> getSort() {
        return sort;
    }

    public List<String> getFilter() {
        return filters;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = dataAccessRecipe.getRecipeList();
        if(favourite)
            recipeList = getRecipeListByFavourite(favourite);
        else if(filters.size() > 0)
            recipeList = getRecipeListByFilter(recipeList);
        else
            recipeList = getRecipeListBySearch(recipeList);

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

    public List<Recipe> getRecipeListBySearch(List<Recipe> recipeList) {
        List<Recipe> searchResults = new ArrayList<>(recipeList.size());

        if(search != null)
        {
            String searchTerm = search.toLowerCase();
            for (Recipe recipe : recipeList)
                if(recipe.getRecipeTitle().toLowerCase().contains(searchTerm))
                    searchResults.add(recipe);
        }
        else
            searchResults = recipeList;

        return searchResults;
    }

    public Recipe getRecipeById(int recipeId) {
        try{
           return dataAccessRecipe.getRecipeById(recipeId);
        }catch(RecipeNotFoundException e) {
            Log.e("Recipe Not found: id: " + recipeId, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Recipe> getRecipeListByFavourite(boolean isFavourite) {
        return dataAccessRecipe.getRecipeListByFavourite(isFavourite);
    }

    public Recipe insertRecipe(Recipe recipe) {
        if(RecipeValidator.validateRecipe(recipe)) {
            return dataAccessRecipe.insertRecipe(recipe);
        }
        return null;
    }

    public Recipe updateRecipe(Recipe newRecipe) {
        if(RecipeValidator.validateRecipe(newRecipe)) {
            return dataAccessRecipe.updateRecipe(newRecipe);
        }
        return null;
    }

    public void deleteRecipe(Recipe recipe) throws RecipeNotFoundException {
        try{
            dataAccessRecipe.deleteRecipe(recipe);
        }catch(RecipeNotFoundException e) {
            Log.e("Recipe Not found: id: " + recipe.getRecipeID(), e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteRecipeById(int recipeId) throws RecipeNotFoundException {
        try{
            dataAccessRecipe.deleteRecipeById(recipeId);
        }catch(RecipeNotFoundException e) {
            Log.e("Recipe Not found: id: " + recipeId, e.getMessage());
            e.printStackTrace();
        }
    }
}
