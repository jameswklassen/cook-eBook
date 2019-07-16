package com.cook_ebook.persistence;

import com.cook_ebook.objects.RecipeTag;

import java.util.*;

public interface RecipeTagPersistence {
    List<RecipeTag> getAllTags();

    RecipeTag insertOneTag(RecipeTag targetTag);

    void deleteOneTag(RecipeTag targetTag);
}
