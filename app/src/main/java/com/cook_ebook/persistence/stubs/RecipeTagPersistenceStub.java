package com.cook_ebook.persistence.stubs;

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
    public int getTagIdByName(String tagName) {
        if(recipeTagList.contains(new RecipeTag(tagName))) {
            for(int i = 0; i < recipeTagList.size(); i ++) {
                if(recipeTagList.get(i).getTagName().equals(tagName)) {
                    return recipeTagList.get(i).getTagID();
                }
            }
        }

        return -1;
    }

    @Override
    public String getTagNameById(int tagId) {
        for(int i = 0; i < recipeTagList.size(); i ++) {
            if(recipeTagList.get(i).getTagID() == tagId) {
                return recipeTagList.get(i).getTagName();
            }
        }

        return "Tag Id does not exist!";
    }

    @Override
    public RecipeTag getTagById(int tagId){
        for(int i = 0; i < recipeTagList.size(); i ++) {
            if(recipeTagList.get(i).getTagID() == tagId) {
                return recipeTagList.get(i);
            }
        }

        return null;
    }

    @Override
    public RecipeTag getTagByName(String tagName){
        if(recipeTagList.contains(new RecipeTag(tagName))) {
            for(int i = 0; i < recipeTagList.size(); i ++) {
                if(recipeTagList.get(i).getTagName().equals(tagName)) {
                    return recipeTagList.get(i);
                }
            }
        }

        return null;
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

    @Override
    public boolean doesTagExist(RecipeTag targetTag){
        return recipeTagList.contains(targetTag);
    }

    @Override
    public boolean doesTagNameExist(String targetTagName) {
        return recipeTagList.contains(new RecipeTag(targetTagName));
    }
}
