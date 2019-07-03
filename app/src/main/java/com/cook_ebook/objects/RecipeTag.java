package com.cook_ebook.objects;

public class RecipeTag {
    private static int counter = -1;

    private int tagId;
    private String tagName;

    public RecipeTag(final int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
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

    @Override
    public String toString() {
        return tagName;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof RecipeTag) {
            RecipeTag newRecipeTag = (RecipeTag) o;
            return this.getTagName().equals(newRecipeTag.getTagName());
        }

        return false;
    }
}
