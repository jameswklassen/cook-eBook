package com.cook_ebook.logic;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.exceptions.InvalidTagException;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.List;

public class RecipeTagHandler {
    private RecipeTagPersistence dataAccessRecipeTag;

    public RecipeTagHandler(boolean forProduction) {
        //default get recipeTagSet
        dataAccessRecipeTag = Services.getRecipeTagPersistence(forProduction);
    }

    public List<RecipeTag> getAllRecipeTags() throws InvalidTagException {
        return dataAccessRecipeTag.getAllTags();
    }

    public int getTagIdByName(String tagName)  throws InvalidTagException {
        return dataAccessRecipeTag.getTagIdByName(tagName);
    }

    public String getTagNameById(int tagId) throws InvalidTagException  {
        return dataAccessRecipeTag.getTagNameById(tagId);
    }

    public RecipeTag getTagById(int tagId) throws InvalidTagException  {
        return dataAccessRecipeTag.getTagById(tagId);
    }

    public RecipeTag getTagByName(String tagName)  throws InvalidTagException {
        return dataAccessRecipeTag.getTagByName(tagName);
    }

    public RecipeTag insertOneTag(RecipeTag tag) throws InvalidTagException  {
        return dataAccessRecipeTag.insertOneTag(tag);
    }

    public void deleteOneTag(RecipeTag tag) throws InvalidTagException  {
        dataAccessRecipeTag.deleteOneTag(tag);
    }

    public boolean doesTagExist(RecipeTag tag) throws InvalidTagException  {
        return dataAccessRecipeTag.doesTagExist(tag);
    }

    public boolean doesTagNameExist(String tagName)  throws InvalidTagException {
        return dataAccessRecipeTag.doesTagNameExist(tagName);
    }
}
