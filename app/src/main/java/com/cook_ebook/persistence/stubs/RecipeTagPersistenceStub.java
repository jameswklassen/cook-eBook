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

    @Override
    public boolean insertOneTag(String targetTag){
        if(recipeTagSet.getAllTags().contains(targetTag)) {
            return false;
        }

        recipeTagSet.addTag(targetTag);
        return true;
    }

    @Override
    public boolean deleteOneTag(String targetTag){
        if (recipeTagSet.getAllTags().contains(targetTag)) {
            recipeTagSet.deleteTag(targetTag);
            return true;
        }

        return false;
    }

    @Override
    public boolean doesTagExist(String targetTag){
        return recipeTagSet.getAllTags().contains(targetTag);
    }
}
