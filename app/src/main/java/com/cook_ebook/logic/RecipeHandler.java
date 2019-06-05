package com.cook_ebook.logic;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.persistence.RecipePersistence;
import com.cook_ebook.persistence.stubs.RecipePersistenceStub;

import java.util.List;

public class RecipeHandler {

    public List<Recipe> getAllRecipes() {
        //return RecipePersistenceStub.getRecipeList();
        return null;
    }

    // should throw exception if recipe doesn't exist
    public Recipe getRecipe(int id) {
        //return RecipePersistenceStub.getRecipe(id);
        return null;
    }

    // should throw an exception for an invalid recipe
    public void insertRecipe(Recipe newRecipe) {
        //RecipePersistence.insertRecipe(newRecipe);
    }

    // should throw an exception for an invalid recipe
    public void updateRecipe(Recipe newRecipe) {
        //RecipePersistence.updateRecipe(newRecipe);
    }

    // should throw an exception if the recipe doesn't exist
    public void deleteRecipe(int id) {
        //RecipePersistence.deleteRecipe(id);
    }

}
