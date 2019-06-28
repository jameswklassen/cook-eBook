package com.cook_ebook.logic;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.List;
import java.util.Set;

public class RecipeTagHandler {
    private RecipeTagPersistence dataAccessRecipeTag;

    public RecipeTagHandler() {
        //default get recipeTagSet
        dataAccessRecipeTag = Services.getRecipeTagPersistence();
    }

    public List<RecipeTag> getAllRecipeTags() {
        return dataAccessRecipeTag.getAllTags();
    }

    public int getTagIdByName(String tagName) {
        return dataAccessRecipeTag.getTagIdByName(tagName);
    }

    public String getTagNameById(int tagId) {
        return dataAccessRecipeTag.getTagNameById(tagId);
    }

    public RecipeTag getTagById(int tagId) {
        return dataAccessRecipeTag.getTagById(tagId);
    }

    public RecipeTag getTagByName(String tagName) {
        return dataAccessRecipeTag.getTagByName(tagName);
    }

    public RecipeTag insertOneTag(RecipeTag tag) {
        return dataAccessRecipeTag.insertOneTag(tag);
    }

    public void deleteOneTag(RecipeTag tag) {
        dataAccessRecipeTag.deleteOneTag(tag);
    }

    public boolean doesTagExist(RecipeTag tag) {
        return dataAccessRecipeTag.doesTagExist(tag);
    }

    public boolean doesTagNameExist(String tagName) {
        return dataAccessRecipeTag.doesTagNameExist(tagName);
    }
}
