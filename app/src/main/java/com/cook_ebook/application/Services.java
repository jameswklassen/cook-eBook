package com.cook_ebook.application;

import com.cook_ebook.persistence.RecipePersistence;
import com.cook_ebook.persistence.RecipeTagPersistence;
import com.cook_ebook.persistence.hsqldb.RecipePersistenceHSQLDB;
import com.cook_ebook.persistence.stubs.RecipePersistenceStub;
import com.cook_ebook.persistence.stubs.RecipeTagPersistenceStub;


public class Services {
    private static RecipePersistence recipePersistence = null;
    private static RecipeTagPersistence recipeTagPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence(boolean forProduction) {
        if(recipePersistence == null) {
            if (forProduction) {
                recipePersistence = new RecipePersistenceHSQLDB(Main.getDBPathName());
            } else {
                recipePersistence = new RecipePersistenceStub();
            }
        }

        return recipePersistence;
    }

    public static synchronized RecipeTagPersistence getRecipeTagPersistence(boolean forProduction) {
        if(recipeTagPersistence == null) {
            if (forProduction) {
                // todo: swap for HSQLDB implementation
                recipeTagPersistence = new RecipeTagPersistenceStub();
            } else {
                recipeTagPersistence = new RecipeTagPersistenceStub();
            }
        }

        return recipeTagPersistence;
    }
}
