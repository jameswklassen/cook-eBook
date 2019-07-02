package com.cook_ebook.application;

import com.cook_ebook.persistence.RecipePersistence;
import com.cook_ebook.persistence.RecipeTagPersistence;
import com.cook_ebook.persistence.hsqldb.RecipePersistenceHSQLDB;
import com.cook_ebook.persistence.stubs.RecipePersistenceStub;
import com.cook_ebook.persistence.stubs.RecipeTagPersistenceStub;


public class Services {
    private static RecipePersistence recipePersistence = null;
    private static RecipeTagPersistence recipeTagPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence() {
        if(recipePersistence == null) {
//            recipePersistence = new RecipePersistenceStub(recipeTagPersistence);
            recipePersistence = new RecipePersistenceHSQLDB(Main.getDBPathName());
        }

        return recipePersistence;
    }

    public static synchronized RecipeTagPersistence getRecipeTagPersistence() {
        if(recipeTagPersistence == null) {
            recipeTagPersistence = new RecipeTagPersistenceStub();
        }

        return recipeTagPersistence;
    }
}
