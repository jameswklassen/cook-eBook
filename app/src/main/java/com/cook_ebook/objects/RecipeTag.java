package com.cook_ebook.objects;

public class RecipeTag {
    private static int counter = -1;

    private int tagId;
    private String tagName;

    public RecipeTag(final int tagId) {
        this.tagId = tagId;
        this.tagName = "";
    }

    public RecipeTag(String tagName) {
        this.tagId = setTagID();
        this.tagName = tagName;
    }

    private int setTagID() {
        counter ++;
        return counter;
    }

    public int getTagID() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "RecipeTag: "  + tagId + ". " + tagName;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof RecipeTag) {
            RecipeTag newRecipeTag = (RecipeTag) o;
            if(this.getTagID() == newRecipeTag.getTagID()) {
                return true;
            }
        }

        return false;
    }
}
