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

        recipeList.add(new Recipe(1,
                "cheese cake",
                "cheese cake description",
                "egg, cream cheese",
                30,
                "cheese cake images",
                new RecipeTagSet(),
                false,
                new Date()));
        recipeList.add(new Recipe(2,
                "brownies",
                "brownies description",
                "flour, baking power",
                20,
                "brownies images",
                new RecipeTagSet("cake"),
                false,
                new Date()));
        recipeList.add(new Recipe(3,
                "pasta",
                "pasta description",
                "egg, water",
                10,
                "pasta images",
                new RecipeTagSet(),
                true,
                new Date()));
        recipeList.add(new Recipe(4,
                "pasta2",
                "pasta2 description",
                "egg, water",
                10,
                "pasta2 images",
                new RecipeTagSet(),
                false,
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
                return Integer.valueOf(recipe1.getRecipeID()).compareTo(recipe2.getRecipeID());
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
                return Integer.valueOf(recipe2.getRecipeID()).compareTo(recipe1.getRecipeID());
            }
        });

        return recipeListSortByDateDescending;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeID() == recipeId) {
                return recipeList.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByCookingTime(int cookingTime) {
        List<Recipe> recipeListByCookingTime = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeCookingTime() == cookingTime) {
                recipeListByCookingTime.add(recipeList.get(i));
            }
        }

        //sort the result by recipeId in ascending order
        Collections.sort(recipeListByCookingTime, new Comparator<Recipe>() {
            public int compare(Recipe recipe1, Recipe recipe2) {
                return Integer.valueOf(recipe1.getRecipeID()).compareTo(recipe2.getRecipeID());
            }
        });

        return recipeListByCookingTime;
    }

    @Override
    public List<Recipe> getRecipeListByTag(String tag) {
        List<Recipe> recipeListByTag = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeTagSet().contains(tag)) {
                recipeListByTag.add(recipeList.get(i));
            }
        }
        return recipeListByTag;
    }

    @Override
    public List<Recipe> getRecipeListByFavourite(boolean isFavourite) {
        List<Recipe> recipeListByFavourite = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeIsFavourite() == isFavourite) {
                recipeListByFavourite.add(recipeList.get(i));
            }
        }

        //sort the result by recipeId in ascending order
        Collections.sort(recipeListByFavourite, new Comparator<Recipe>() {
            public int compare(Recipe recipe1, Recipe recipe2) {
                return Integer.valueOf(recipe1.getRecipeID()).compareTo(recipe2.getRecipeID());
            }
        });

        return recipeListByFavourite;
    }

    @Override
    public List<Recipe> getRecipeListByDate(Date date) {
        List<Recipe> recipeListByDate = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeDate().equals(date)) {
                recipeListByDate.add(recipeList.get(i));
            }
        }

        //sort the result by recipeId in ascending order
        Collections.sort(recipeListByDate, new Comparator<Recipe>() {
            public int compare(Recipe recipe1, Recipe recipe2) {
                return Integer.valueOf(recipe1.getRecipeID()).compareTo(recipe2.getRecipeID());
            }
        });

        return recipeListByDate;
    }

    @Override
    public boolean insertRecipe(Recipe currentRecipe) {
        int index;
        index = recipeList.indexOf(currentRecipe);

        if(index >= 0) {
            // check for duplicates
            return false;
        }

        recipeList.add(currentRecipe);
        return true;
    }

    @Override
    public boolean updateRecipe(Recipe currentRecipe) {
        int index;
        index = recipeList.indexOf(currentRecipe);

        if (index < 0) {
            //if currentRecipe does not exist in the list
            return false;
        }

        recipeList.set(index, currentRecipe);
        return true;
    }

    @Override
    public boolean deleteRecipe(Recipe currentRecipe) {
        int index;
        index = recipeList.indexOf(currentRecipe);

        if(index < 0) {
            //if currentRecipe does not exist in the list
            return false;
        }

        recipeList.remove(index);
        return true;
    }

    @Override
    public boolean deleteRecipeById(int recipeId) {
        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeID() == recipeId) {
                recipeList.remove(i);
                return true;
            }
        }

        return false;
    }
}
