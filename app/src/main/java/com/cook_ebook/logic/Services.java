package com.cook_ebook.logic;

import com.cook_ebook.persistence.RecipePersistence;
import com.cook_ebook.persistence.RecipeTagPersistence;
import com.cook_ebook.persistence.stubs.RecipePersistenceStub;
import com.cook_ebook.persistence.stubs.RecipeTagPersistenceStub;


public class Services {
    private static RecipePersistence recipePersistence = null;
    private static RecipeTagPersistence recipeTagPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence(){
        if (recipePersistence == null){
            recipePersistence = new RecipePersistenceStub();
        }

        return recipePersistence;
    }

    public static synchronized RecipeTagPersistence getRecipeTagPersistence(){
        if (recipeTagPersistence == null){
            recipeTagPersistence = new RecipeTagPersistenceStub();
        }

        return recipeTagPersistence;
    }

    /**
     * clean
     *
     * Reset all services so to be reloaded from scratch next time they are referenced
     */
    public static synchronized void clean() {
        recipePersistence = null;
        recipeTagPersistence = null;
    }
}
