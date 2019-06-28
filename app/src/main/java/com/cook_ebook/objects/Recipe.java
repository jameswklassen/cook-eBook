package com.cook_ebook.objects;

import java.util.*;

public class Recipe {
    private static int counter = -1;

    private String recipeTitle;
    private String recipeDescription;
    private String recipeIngredients;
    private int recipeCookingTime;
    private String recipeImages;
    private Set<RecipeTag> recipeTagSet;
    private Boolean recipeIsFavourite;
    private Date date;
    private int recipeID;

    public Recipe(final int recipeID) {
        this.recipeID = recipeID;
        this.recipeTitle = null;
        this.recipeDescription = null;
        this.recipeIngredients = null;
        this.recipeCookingTime = -1;
        this.recipeImages = null;
        this.recipeTagSet = new HashSet<>();
        this.recipeIsFavourite = null;
        this.date = new Date(); // Current date
    }

    public Recipe(
        String recipeTitle,
        String recipeDescription,
        String recipeIngredients,
        int recipeCookingTime,
        String recipeImages,
        Boolean recipeIsFavourite) {
        recipeID = setRecipeID();
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeCookingTime = recipeCookingTime;
        this.recipeImages = recipeImages;
        this.recipeTagSet = new HashSet<>();
        this.recipeIsFavourite = recipeIsFavourite;
        this.date = new Date(); // Current date
    }

    private int setRecipeID() {
        counter ++;
        return counter;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public int getRecipeCookingTime() {
        return recipeCookingTime;
    }

    public String getRecipeImages() {
        return recipeImages;
    }

    public List<RecipeTag> getRecipeTagSet() {
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();
        List<RecipeTag> tagList = new ArrayList<>();

        while(iterator.hasNext()) {
            tagList.add(iterator.next());
        }

        return tagList;
    }

    public Boolean getRecipeIsFavourite() {
        return recipeIsFavourite;
    }

    public Date getRecipeDate() {
        return date;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public void setRecipeCookingTime(int recipeCookingTime) {
        this.recipeCookingTime = recipeCookingTime;
    }

    public void setRecipeImages(String recipeImages) {
        this.recipeImages = recipeImages;
    }

    public RecipeTag addRecipeTag(RecipeTag newTag) {
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();
        List<RecipeTag> tagList = new ArrayList<>();

        while(iterator.hasNext()) {
            tagList.add(iterator.next());
        }

        if(!tagList.contains(newTag)) {
            recipeTagSet.add(newTag);
        }

        return newTag;
    }

    public void deleteRecipeTag(RecipeTag targetTag) {
        Iterator<RecipeTag> iterator = recipeTagSet.iterator();
        List<RecipeTag> tagList = new ArrayList<>();

        while(iterator.hasNext()) {
            tagList.add(iterator.next());
        }

        if(tagList.contains(targetTag)) {
            recipeTagSet.remove(targetTag);
        }
    }

    public void setRecipeIsFavourite(Boolean recipeIsFavourite) {
        this.recipeIsFavourite = recipeIsFavourite;
    }

    public void updateRecipeDate() {
        this.date = new Date(); //update date to current date
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeID=" + recipeID +
                ", recipeTitle='" + recipeTitle + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                ", recipeIngredients='" + recipeIngredients + '\'' +
                ", recipeCookingTime=" + recipeCookingTime +
                ", recipeImages='" + recipeImages + '\'' +
                ", recipeTagSet='" + recipeTagSet + '\'' +
                ", recipeIsFavourite=" + recipeIsFavourite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Recipe) {
            Recipe newRecipe = (Recipe)o;
            if(this.getRecipeID() == newRecipe.getRecipeID()) {
                return true;
            }
        }

        return false;
    }
}
