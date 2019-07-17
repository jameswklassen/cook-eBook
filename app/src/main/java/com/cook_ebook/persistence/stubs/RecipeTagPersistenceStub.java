package com.cook_ebook.persistence.stubs;

import android.widget.TabHost;

import com.cook_ebook.logic.exceptions.TagNotFoundException;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.*;

public class RecipeTagPersistenceStub implements RecipeTagPersistence {
    private List<RecipeTag> recipeTagList;

    public RecipeTagPersistenceStub() {
        this.recipeTagList = new ArrayList<>();

        recipeTagList.add(new RecipeTag("dessert"));
        recipeTagList.add(new RecipeTag("cake"));
        recipeTagList.add(new RecipeTag("pasta"));
        recipeTagList.add(new RecipeTag("salad"));
    }

    @Override
    public List<RecipeTag> getAllTags(){
        return Collections.unmodifiableList(recipeTagList);
    }

    @Override
    public RecipeTag insertOneTag(RecipeTag targetTag){
        if(!recipeTagList.contains(targetTag)) {
            recipeTagList.add(targetTag);
        }

        return targetTag;
    }

    @Override
    public void deleteOneTag(RecipeTag targetTag){
        if (recipeTagList.contains(targetTag)) {
            recipeTagList.remove(targetTag);
        }
    }
}
