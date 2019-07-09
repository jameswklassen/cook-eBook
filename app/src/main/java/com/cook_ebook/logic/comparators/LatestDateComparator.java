package com.cook_ebook.logic.comparators;
import com.cook_ebook.objects.Recipe;
import java.util.Comparator;
public class LatestDateComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        return second.getRecipeDate().compareTo(first.getRecipeDate());
    }
}