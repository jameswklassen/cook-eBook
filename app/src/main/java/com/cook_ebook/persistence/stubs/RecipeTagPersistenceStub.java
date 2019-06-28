package com.cook_ebook.persistence.stubs;

import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.*;

public class RecipeTagPersistenceStub implements RecipeTagPersistence {
    private Set<RecipeTag> recipeTagSet;

    public RecipeTagPersistenceStub() {
        this.recipeTagSet = new HashSet<>();

        recipeTagSet.add(new RecipeTag("dessert"));
        recipeTagSet.add(new RecipeTag("cake"));
        recipeTagSet.add(new RecipeTag("pasta"));
        recipeTagSet.add(new RecipeTag("salad"));
    }

    @Override
    public Set<RecipeTag> getAllTags(){
        return recipeTagSet;
    }

    @Override
    public int getTagIdByName(String tagName) {
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();
        while(iterator.hasNext()) {
            RecipeTag temp = iterator.next();
            if(temp.getTagName().equals(tagName)) {
                return temp.getTagID();
            }
        }

        return -1;
    }

    @Override
    public String getTagNameById(int tagId) {
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();
        while(iterator.hasNext()) {
            RecipeTag temp = iterator.next();
            if(temp.getTagID() == tagId) {
                return temp.getTagName();
            }
        }

        return "Tag Id does not exist!";
    }

    @Override
    public RecipeTag getTagById(int tagId){
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();
        while(iterator.hasNext()) {
            RecipeTag temp = iterator.next();
            if(temp.getTagID() == tagId) {
                return temp;
            }
        }

        return null;
    }

    @Override
    public RecipeTag getTagByName(String tagName){
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();
        while(iterator.hasNext()) {
            RecipeTag temp = iterator.next();
            if(temp.getTagName().equals(tagName)) {
                return temp;
            }
        }

        return null;
    }

    @Override
    public RecipeTag insertOneTag(RecipeTag targetTag){
        if(!recipeTagSet.contains(targetTag)) {
            recipeTagSet.add(targetTag);
        }

        return targetTag;
    }

    @Override
    public void deleteOneTag(RecipeTag targetTag){
        if (recipeTagSet.contains(targetTag)) {
            recipeTagSet.remove(targetTag);
        }
    }

    @Override
    public boolean doesTagExist(RecipeTag targetTag){
        return recipeTagSet.contains(targetTag);
    }

    @Override
    public boolean doesTagNameExist(String targetTagName) {
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();

        while (iterator.hasNext()) {
            RecipeTag temp = iterator.next();
            if(temp.getTagName().equals(targetTagName)) {
                return true;
            }
        }

        return false;
    }
}
