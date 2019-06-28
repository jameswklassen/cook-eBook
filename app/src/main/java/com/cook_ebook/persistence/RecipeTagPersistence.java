package com.cook_ebook.persistence;

import com.cook_ebook.objects.RecipeTag;

import java.util.*;

public interface RecipeTagPersistence {
    List<RecipeTag> getAllTags();

    int getTagIdByName(String tagName);

    String getTagNameById(int tagId);

    RecipeTag getTagById(int tagId);

    RecipeTag getTagByName(String tagName);

    RecipeTag insertOneTag(RecipeTag targetTag);

    void deleteOneTag(RecipeTag targetTag);

    boolean doesTagExist(RecipeTag targetTag);

    // we may not need this method, I am not not sure, so I keep it for temp
    // if we do not need it, we can delete this method before merge to master
    boolean doesTagNameExist(String targetTagName);
}
