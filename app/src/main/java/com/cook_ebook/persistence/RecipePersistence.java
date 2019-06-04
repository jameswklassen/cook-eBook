package com.cook_ebook.persistence;

import com.cook_ebook.objects.Recipe;

public interface RecipePersistence {

    Recipe insertRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);
}
