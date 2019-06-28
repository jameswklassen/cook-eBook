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
    public List<RecipeTag> getAllTags(){
        List<RecipeTag> tagList = new ArrayList<>();

        Iterator<RecipeTag> iterator = recipeTagSet.iterator();

        while(iterator.hasNext()) {
            tagList.add(iterator.next());
        }

        return tagList;
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
        List<RecipeTag> tagList = new ArrayList<>();

        Iterator<RecipeTag> iterator = recipeTagSet.iterator();

        while(iterator.hasNext()) {
            tagList.add(iterator.next());
        }

        if(!tagList.contains(targetTag)) {
            recipeTagSet.add(targetTag);
        }

        return targetTag;
    }

    @Override
    public void deleteOneTag(RecipeTag targetTag){
        List<RecipeTag> tagList = new ArrayList<>();

        Iterator<RecipeTag> iterator1 = recipeTagSet.iterator();

        while(iterator1.hasNext()) {
            tagList.add(iterator1.next());
        }

        if (tagList.contains(targetTag)) {

            Iterator<RecipeTag> iterator2 = recipeTagSet.iterator();

            while (iterator2.hasNext()) {
                RecipeTag temp = iterator2.next();
                if(temp.getTagName().equals(targetTag.getTagName())) {
                    recipeTagSet.remove(temp);
                }
            }
        }
    }

    @Override
    public boolean doesTagExist(RecipeTag targetTag){
        List<RecipeTag> tagList = new ArrayList<>();

        Iterator<RecipeTag> iterator = recipeTagSet.iterator();

        while(iterator.hasNext()) {
            tagList.add(iterator.next());
        }

        return tagList.contains(targetTag);
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
