package com.cook_ebook.logic.comparators;
import com.cook_ebook.objects.Recipe;
import java.util.Comparator;

public class OldestDateComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        if (first.getRecipeDate().before(second.getRecipeDate())) {
            return -1;
        } else if (first.getRecipeDate().after(second.getRecipeDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}
