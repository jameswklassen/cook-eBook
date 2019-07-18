package com.cook_ebook.objects;

import java.util.*;

public class Recipe {
    private static int counter = -1;

    private String recipeTitle;
    private String recipeDescription;
    private String recipeIngredients;
    private int recipeCookingTime;
    private String recipeImage;
    private List<RecipeTag> recipeTagList;
    private Boolean recipeIsFavourite;
    private Date date;
    private int recipeID;

    public Recipe(final int recipeID) {
        this.recipeID = recipeID;
        this.recipeTitle = null;
        this.recipeDescription = null;
        this.recipeIngredients = null;
        this.recipeCookingTime = -1;
        this.recipeImage = null;
        this.recipeTagList = new ArrayList<>();
        this.recipeIsFavourite = null;
        this.date = new Date(); // Current date
    }

    /*
     *  Recipe constructor
     *      - used when DB creates Recipes with data straight from the database.
     *      - Allows for full control over recipe creation (set the id and the date)
     */
    public Recipe(
            int recipeID,
            String recipeTitle,
            String recipeDescription,
            String recipeIngredients,
            int recipeCookingTime,
            String recipeImage,
            Boolean recipeIsFavourite,
            Date date) {
        this(recipeTitle, recipeDescription, recipeIngredients, recipeCookingTime, recipeImage, recipeIsFavourite);
        this.recipeID = recipeID;
        this.date = date; // Current date
    }

    /*
     * Recipe constructor
     *      - used by the app when a new recipe is being created
     *      - Doesn't set an ID, and sets the date to current date
     */
    public Recipe(
        String recipeTitle,
        String recipeDescription,
        String recipeIngredients,
        int recipeCookingTime,
        String recipeImage,
        Boolean recipeIsFavourite) {
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeIngredients = recipeIngredients;
        this.recipeCookingTime = recipeCookingTime;
        this.recipeImage = recipeImage;
        this.recipeTagList = new ArrayList<>();
        this.recipeIsFavourite = recipeIsFavourite;
        this.date = new Date(); // Current date
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

    public String getRecipeImage() {
        return recipeImage;
    }

    public List<RecipeTag> getRecipeTagList() {
        return recipeTagList;
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


    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public RecipeTag addRecipeTag(RecipeTag newTag) {
        if(!recipeTagList.contains(newTag)) {
            recipeTagList.add(newTag);
            return newTag;
        } else {
            return recipeTagList.get(recipeTagList.indexOf(newTag));
        }
    }

    public void deleteRecipeTag(RecipeTag targetTag) {
        if(recipeTagList.contains(targetTag)) {
            recipeTagList.remove(targetTag);
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
                ", recipeImage='" + recipeImage + '\'' +
                ", recipeTagList='" + recipeTagList.toString() + '\'' +
                ", recipeIsFavourite=" + recipeIsFavourite + '\'' +
                ", recipeDate=" + getRecipeDate().toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Recipe) {
            Recipe newRecipe = (Recipe)o;
            return this.getRecipeID() == newRecipe.getRecipeID();
        }

        return false;
    }
}
