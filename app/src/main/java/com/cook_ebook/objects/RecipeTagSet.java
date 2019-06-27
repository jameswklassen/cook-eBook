package com.cook_ebook.objects;

import java.util.*;
import java.util.Iterator;

public class RecipeTagSet {
    private static int counter = -1;

    private Set<String> recipeTagSet;
    private int id;

    public RecipeTagSet() {
        this.recipeTagSet = new HashSet<>();
    }

    public RecipeTagSet(String tag) {
        this.id = setID();
        this.recipeTagSet = new HashSet<>();
        if(tag != null) {
            recipeTagSet.add(tag);
        }
    }

    private int setID() {
        counter ++;
        return counter;
    }

    public int getID() {
        return id;
    }

    public Set<String> getAllTags() {
        return recipeTagSet;
    }

    public void addTag(String tag) {
        recipeTagSet.add(tag);
    }

    public void deleteTag(String tag) {
        recipeTagSet.remove(tag);
    }

    public void setTag (String newTag, String oldTag) {
        recipeTagSet.remove(oldTag);
        recipeTagSet.add(newTag);
    }

    public List<String> getTagsList() {
        List<String> tagsList = new ArrayList<>();

        Iterator iter = recipeTagSet.iterator();

        while(iter.hasNext()) {
            tagsList.add(iter.next().toString());
        }

        return tagsList;
    }

    @Override
    public String toString() {
        String recipeTagsString = "";
        Iterator iter = recipeTagSet.iterator();
        while(iter.hasNext()) {
            recipeTagsString += iter.next();
        }

        return recipeTagsString;
    }
}
