package com.cook_ebook.persistence;

import com.cook_ebook.objects.RecipeTagSet;
import java.util.*;

public interface RecipeTagPersistence {
    Set<String> getAllTags();

    boolean insertOneTag(String targetTag);

    boolean deleteOneTag(String targetTag);

    boolean doesTagExist(String targetTag);
}
