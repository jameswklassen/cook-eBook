package com.cook_ebook.persistence.stubs;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTagSet;
import com.cook_ebook.persistence.RecipePersistence;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Comparator;

public class RecipePersistenceStub implements RecipePersistence {
    private List<Recipe> recipeList;

    public RecipePersistenceStub() {
        this.recipeList = new ArrayList<>();

        recipeList.add(new Recipe(1, "cheese cake",
                "cheese cake description",
                "egg, cream cheese",
                30,
                "cheese cake images",
                new RecipeTagSet(),
                false,
                new Date()));
        recipeList.add(new Recipe(2, "brownies",
                "brownies description",
                "flour, baking power",
                20,
                "brownies images",
                new RecipeTagSet("cake"),
                false,
                new Date()));
        recipeList.add(new Recipe(3, "pasta",
                "pasta description",
                "egg, water",
                10,
                "pasta images",
                new RecipeTagSet(),
                true,
                new Date()));
    }

    @Override
    public List<Recipe> getRecipeList() {
        //default sorted by date in ascending order
        List<Recipe> recipeListSortByDateAscending = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            recipeListSortByDateAscending.add(recipeList.get(i));
        }

        Collections.sort(recipeListSortByDateAscending, new Comparator<Recipe>() {
            public int compare(Recipe recipe1, Recipe recipe2) {
                return Long.valueOf(recipe1.getRecipeDate().getTime()).compareTo(recipe2.getRecipeDate().getTime());
            }
        });

        return recipeListSortByDateAscending;
    }

    @Override
    public List<Recipe> getRecipeListByDescendingDate() {
        //sorted by date in descending order
        List<Recipe> recipeListSortByDateDescending = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            recipeListSortByDateDescending.add(recipeList.get(i));
        }

        Collections.sort(recipeListSortByDateDescending, new Comparator<Recipe>() {
            public int compare(Recipe recipe1, Recipe recipe2) {
                return Long.valueOf(recipe2.getRecipeDate().getTime()).compareTo(recipe1.getRecipeDate().getTime());
            }
        });

        return recipeListSortByDateDescending;
    }

    @Override
    public Recipe getRecipeById(int recipeId){
        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeID() == recipeId) {
                return recipeList.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByCookingTime(int cookingTime){
        List<Recipe> recipeListByCookingTime = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeCookingTime() == cookingTime) {
                recipeListByCookingTime.add(recipeList.get(i));
            }
        }
        return recipeListByCookingTime;
    }

    @Override
    public List<Recipe> getRecipeListByTag(String tag){
        List<Recipe> recipeListByTag = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeTagSet().contains(tag)) {
                recipeListByTag.add(recipeList.get(i));
            }
        }
        return recipeListByTag;
    }

    @Override
    public List<Recipe> getRecipeListByFavourite(boolean isFavourite){
        List<Recipe> recipeListByFavourite = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeIsFavourite() == isFavourite) {
                recipeListByFavourite.add(recipeList.get(i));
            }
        }
        return recipeListByFavourite;
    }

    @Override
    public List<Recipe> getRecipeListByDate(Date date){
        List<Recipe> recipeListByDate = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeDate().equals(date)) {
                recipeListByDate.add(recipeList.get(i));
            }
        }
        return recipeListByDate;
    }

    @Override
    public Recipe insertRecipe(Recipe currentRecipe) {
        // don't bother checking for duplicates
        recipeList.add(currentRecipe);
        return currentRecipe;
    }

    @Override
    public Recipe updateRecipe(Recipe currentRecipe){
        int index;

        index = recipeList.indexOf(currentRecipe);
        if (index >= 0) {
            recipeList.set(index, currentRecipe);
        }
        return currentRecipe;
    }

    @Override
    public void deleteRecipe(Recipe currentRecipe){
        int index;

        index = recipeList.indexOf(currentRecipe);
        if(index >= 0) {
            recipeList.remove(index);
        }
    }
}
