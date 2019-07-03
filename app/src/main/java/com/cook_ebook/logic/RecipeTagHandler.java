package com.cook_ebook.logic;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.exceptions.InvalidTagException;
import com.cook_ebook.logic.exceptions.TagNotFoundException;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.List;

public class RecipeTagHandler {
    private RecipeTagPersistence dataAccessRecipeTag;

    public RecipeTagHandler() {
        //default get recipeTagSet
        dataAccessRecipeTag = Services.getRecipeTagPersistence();
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
        if(RecipeTagValidator.validateRecipeTag(tag)) {
            return dataAccessRecipeTag.insertOneTag(tag);
        } else {
            throw new TagNotFoundException("The tag being inserted does not exist.");
        }
    }

    public void deleteOneTag(RecipeTag tag) throws InvalidTagException  {
        if(RecipeTagValidator.validateRecipeTag(tag)) {
            dataAccessRecipeTag.insertOneTag(tag);
        } else {
            throw new TagNotFoundException("The tag being deleted does not exist.");
        }
    }

    public boolean doesTagExist(RecipeTag tag) throws InvalidTagException  {
        if(RecipeTagValidator.validateRecipeTag(tag)) {
            return dataAccessRecipeTag.doesTagExist(tag);
        } else {
            throw new TagNotFoundException("The tag does not exist.");
        }
    }

    public boolean doesTagNameExist(String tagName)  throws InvalidTagException {
        return dataAccessRecipeTag.doesTagNameExist(tagName);
    }
}
