package com.cook_ebook.logic.comparators;
import com.cook_ebook.objects.Recipe;
import java.util.Comparator;
public class DescendingDateComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        return first.getRecipeDate().compareTo(second.getRecipeDate());
    }
}