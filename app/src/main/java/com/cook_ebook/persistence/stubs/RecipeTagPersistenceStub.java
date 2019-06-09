package com.cook_ebook.persistence.stubs;

import com.cook_ebook.objects.RecipeTagSet;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.*;
import java.util.Iterator;

public class RecipeTagPersistenceStub implements RecipeTagPersistence {
    private RecipeTagSet recipeTagSet;

    public RecipeTagPersistenceStub() {
        this.recipeTagSet = new RecipeTagSet();

        recipeTagSet.addTag("dessert");
        recipeTagSet.addTag("pasta");
        recipeTagSet.addTag("cake");
        recipeTagSet.addTag("salad");
    }

    @Override
    public Set<String> getAllTags(){
        return recipeTagSet.getAllTags();
    }


    // we may not need this method,
    // but I will keep it here until we finish true database
    @Override
    public RecipeTagSet insertMultipleTags(RecipeTagSet currentTagSet){
        Iterator<String> iter = currentTagSet.getAllTags().iterator();
        while (iter.hasNext()) {
            recipeTagSet.addTag(iter.next());
        }
        return currentTagSet;
    }

    @Override
    public RecipeTagSet insertOneTag(String targetTag){
        recipeTagSet.addTag(targetTag);
        return recipeTagSet;
    }

    @Override
    public void deleteMultipleTags(RecipeTagSet currentTagSet){
        Iterator<String> iter = currentTagSet.getAllTags().iterator();
        while (iter.hasNext()) {
            recipeTagSet.deleteTag(iter.next());
        }
    }

    @Override
    public void deleteOneTag(String targetTag){
        if (recipeTagSet.getAllTags().contains(targetTag)) {
            recipeTagSet.deleteTag(targetTag);
        }
    }

    @Override
    public boolean doesTagExist(String targetTag){
        return recipeTagSet.getAllTags().contains(targetTag);
    }
}
