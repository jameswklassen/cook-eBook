package com.cook_ebook.logic;

import com.cook_ebook.logic.exceptions.InvalidTagException;
import com.cook_ebook.objects.RecipeTag;

public class RecipeTagValidator {

    /* validateRecipeTag()
     *
     * Return false if tag is invalid
     */
    public static boolean validateRecipeTag(RecipeTag tag) throws InvalidTagException {
        return tag !=null;
    }
}
