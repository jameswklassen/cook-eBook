package com.cook_ebook.persistence.stubs;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTagSet;
import com.cook_ebook.persistence.RecipePersistence;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class RecipePersistenceStub implements RecipePersistence {
    private List<Recipe> recipes;

    public RecipePersistenceStub() {
        this.recipes = new ArrayList<>();

        recipes.add(new Recipe(1, "cheese cake",
                "cheese cake description",
                "egg, cream cheese",
                30,
                "cheese cake images",
                new RecipeTagSet(),
                false,
                new Date()));
        recipes.add(new Recipe(2, "brownies",
                "brownies description",
                "flour, baking power",
                20,
                "brownies images",
                new RecipeTagSet("cake"),
                false,
                new Date()));
        recipes.add(new Recipe(3, "pasta",
                "pasta description",
                "egg, water",
                10,
                "pasta images",
                new RecipeTagSet(),
                true,
                new Date()));
    }

    @Override
    public Recipe insertRecipe(Recipe currentRecipe) {
        // don't bother checking for duplicates
        recipes.add(currentRecipe);
        return currentRecipe;
    }

    @Override
    public Recipe updateRecipe(Recipe currentRecipe){
        int index;

        index = recipes.indexOf(currentRecipe);
        if (index >= 0) {
            recipes.set(index, currentRecipe);
        }
        return currentRecipe;
    }

    @Override
    public void deleteRecipe(Recipe currentRecipe){
        int index;

        index = recipes.indexOf(currentRecipe);
        if(index >= 0) {
            recipes.remove(index);
        }
    }
}
