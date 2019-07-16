package com.cook_ebook.logic;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.exceptions.InvalidTagException;
import com.cook_ebook.logic.exceptions.TagNotFoundException;
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

    public RecipeTag insertOneTag(RecipeTag tag) throws InvalidTagException  {
        if(RecipeTagValidator.validateRecipeTag(tag)) {
            return dataAccessRecipeTag.insertOneTag(tag);
        } else {
            throw new TagNotFoundException("The tag being inserted does not exist.");
        }
    }

    public void deleteOneTag(RecipeTag tag) throws InvalidTagException  {
        if(RecipeTagValidator.validateRecipeTag(tag)) {
            dataAccessRecipeTag.deleteOneTag(tag);
        } else {
            throw new TagNotFoundException("The tag being deleted does not exist.");
        }
    }
}
