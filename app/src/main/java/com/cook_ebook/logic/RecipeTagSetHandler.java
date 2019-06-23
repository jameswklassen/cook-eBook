package com.cook_ebook.logic;

import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.Set;

public class RecipeTagSetHandler {
    private RecipeTagPersistence dataAccessRecipeTag;

    public RecipeTagSetHandler() {
        //default get recipeTagSet
        dataAccessRecipeTag = Services.getRecipeTagPersistence();
    }

    public Set<String> getAllRecipeTags() {
        return dataAccessRecipeTag.getAllTags();
    }

    public boolean insertOneTag(String tag) {
        return dataAccessRecipeTag.insertOneTag(tag);
    }

    public boolean deleteOneTag(String tag) {
        return dataAccessRecipeTag.deleteOneTag(tag);
    }

    public boolean doesTagExist(String tag) {
        return dataAccessRecipeTag.doesTagExist(tag);
    }
}
