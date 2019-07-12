package com.cook_ebook.logic;

import com.cook_ebook.logic.exceptions.InvalidRecipeException;
import com.cook_ebook.logic.exceptions.InvalidRecipeTitle;
import com.cook_ebook.logic.exceptions.NonPositiveCookingTimeException;
import com.cook_ebook.logic.exceptions.NotATimeException;
import com.cook_ebook.objects.Recipe;

public class RecipeValidator {

    /* validateRecipe()
     *
     * Return false if title is invalid
     */
    public static boolean validateRecipe(Recipe recipe) throws InvalidRecipeException {
        return recipe !=null;
    }

    /* validateTitle()
     *
     * Return false if title is invalid
     */
    public static boolean validateTitle(String title) throws InvalidRecipeTitle {
        return title.equals("") || title == null;
    }

    /* validateCookingTimeNumeric()
     *
     * Return false if cooking time is not a number
     */
    public static boolean validateCookingTimeNumeric(String cookingTime) throws NotATimeException {

        try {
            int num = Integer.parseInt(cookingTime);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
    }
}
