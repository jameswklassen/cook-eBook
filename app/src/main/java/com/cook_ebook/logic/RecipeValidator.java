package com.cook_ebook.logic;

import com.cook_ebook.objects.Recipe;

public class RecipeValidator {

    /* validateRecipe()
     *
     * Return false if title is invalid
     */
    public static boolean validateRecipe(Recipe recipe) {
        if(recipe !=null) {
            return true;
        }

        return false;
    }

    /* validateTitle()
     *
     * Return false if title is invalid
     */
    public static boolean validateTitle(String title) {
        if(title.equals("") || title == null) {
            return false;
        }

        return true;
    }

    /* validateCookingTimeNumeric()
     *
     * Return false if cooking time is not a number
     */
    public static boolean validateCookingTimeNumeric(String cookingTime) {
        if(isStringNumeric(cookingTime)) {
                return true;
        }

        return false;
    }

    /* validateCookingTimePositive()
     *
     * Return false if cooking time is non-positive
     */
    public static boolean validateCookingTimePositive(String cookingTime) {
        if(Integer.parseInt(cookingTime) > 0) {
            return true;
        }

        return false;
    }

    /* isStringNumeric()
     *
     * Return false if String is not a number
     */
    public static boolean isStringNumeric(String targetInput) {
        try {
            int num = Integer.parseInt(targetInput);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
    }
}
