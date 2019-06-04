package com.cook_ebook.persistence;

import com.cook_ebook.objects.RecipeTagSet;
import java.util.*;

public interface RecipeTagPersistence {
    Set<String> getAllTags();

    RecipeTagSet insertMultipleTags(RecipeTagSet currentTagSet);

    RecipeTagSet insertOneTag(String targetTag);

    void deleteMultipleTags(RecipeTagSet currentTagSet);

    void deleteOneTag(String targetTag);

    boolean doesTagExist(String targetTag);
}
