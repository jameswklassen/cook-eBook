package com.cook_ebook.logic.comparators;
import com.cook_ebook.objects.Recipe;
import java.util.Comparator;
public class LatestDateComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        if (second.getRecipeDate().before(first.getRecipeDate())) {
            return -1;
        } else if (second.getRecipeDate().after(first.getRecipeDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}