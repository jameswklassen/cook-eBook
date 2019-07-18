package com.cook_ebook.logic;

import com.cook_ebook.application.Services;
import com.cook_ebook.logic.exceptions.InvalidTagException;
import com.cook_ebook.logic.exceptions.TagNotFoundException;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipeTagPersistence;
import android.util.Log;

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

    public RecipeTag insertOneTag(RecipeTag tag)  {
        if(RecipeTagValidator.validateRecipeTag(tag)) {
            return dataAccessRecipeTag.insertOneTag(tag);
        }
        return null;
    }

    public void deleteOneTag(RecipeTag tag) throws InvalidTagException  {

        try{
            if(RecipeTagValidator.validateRecipeTag(tag)) {
                dataAccessRecipeTag.deleteOneTag(tag);
            }
        }catch(TagNotFoundException e) {
            Log.e("Tag Not found: id: " + tag.getTagID(), e.getMessage());
            e.printStackTrace();
        }
    }
}
