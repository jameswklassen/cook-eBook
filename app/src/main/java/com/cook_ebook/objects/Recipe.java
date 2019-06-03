package com.cook_ebook.objects;

import java.util.*;

public class Recipe {

    private final int recipeID;
    private String recipeTitle;
    private String recipeDescription;
    private String recipeIngredients;
    private int recipeCookingTime;
    private String recipeImages;
    private Set<String> recipeTags;
    private Boolean recipeIsFavourite;

    public Recipe(final int recipeID) {
        this.recipeID = recipeID;
        this.recipeTitle = null;
        this.recipeDescription = null;
        this.recipeIngredients = null;
        this.recipeCookingTime = -1;
        this.recipeImages = null;
        this.recipeTags = new HashSet<>();
        this.recipeIsFavourite = null;

    }

    public Recipe(
        final int recipeID,
        String recipeTitle,
        String recipeDescription,
        String recipeIngredients,
        int recipeCookingTime,
        String recipeImages,
        Set<String> recipeTags,
        Boolean recipeIsFavourite
    ) {
        this.recipeID = recipeID;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeCookingTime = recipeCookingTime;
        this.recipeImages = recipeImages;
        this.recipeTags = recipeTags;
        this.recipeIsFavourite = recipeIsFavourite;
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

    public Set<String> getRecipeTags() {
        return recipeTags;
    }

    public Boolean getRecipeIsFavourite() {
        return recipeIsFavourite;
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

    public void setRecipeTags(Set<String> recipeTags) {
        this.recipeTags = recipeTags;
    }

    public void addRecipeTag(String tag) {
        this.recipeTags.add(tag);
    }

    public void removeRecipeTag(String tag) {
        this.recipeTags.remove(tag);
    }

    public void setRecipeIsFavourite(Boolean recipeIsFavourite) {
        this.recipeIsFavourite = recipeIsFavourite;
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
                ", recipeTags='" + recipeTags + '\'' +
                ", recipeIsFavourite=" + recipeIsFavourite +
                '}';
    }
}
