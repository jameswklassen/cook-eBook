package com.cook_ebook.persistence.stubs;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipePersistence;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Comparator;

public class RecipePersistenceStub implements RecipePersistence {
    private List<Recipe> recipeList;

    public RecipePersistenceStub() {
        this.recipeList = new ArrayList<>();

        Date date = new Date();

        recipeList.add(new Recipe(
                1,
                "Cheese Cake",
                "Heat oven to 325ºF.\n" +
                        "Mix crumbs, 3 Tbsp. sugar and butter; press onto bottom of 9-inch springform pan. Bake 10 min.\n" +
                        "Beat cream cheese, 1 cup sugar, flour and vanilla in large bowl with mixer until blended. Add sour cream; mix well. Add eggs, 1 at a time, mixing on low speed after each just until blended. Pour over crust.\n" +
                        "Bake 1 hour 10 min. or until centre is almost set. Run knife around rim of pan to loosen cake; cool before removing rim. Refrigerate 4 hours.",
                "1 cup graham crumbs\n" +
                        "3 Tbsp. sugar\n" +
                        "3 Tbsp. butter, melted\n" +
                        "5 pkg. (250 g each) Philadelphia Brick Cream Cheese, softened\n" +
                        "1 cup sugar\n" +
                        "3 Tbsp. flour\n" +
                        "1 Tbsp. vanilla\n" +
                        "1 cup sour cream\n" +
                        "4 eggs",
                60,
                "cheese cake images",
                false,
                date
        ));

        recipeList.add(new Recipe(
                2,
                "Brownies",
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour an 8-inch square pan.\n" +
                        "In a large saucepan, melt 1/2 cup butter. Remove from heat, and stir in sugar, eggs, and 1 teaspoon vanilla. Beat in 1/3 cup cocoa, 1/2 cup flour, salt, and baking powder. Spread batter into prepared pan.\n" +
                        "Bake in preheated oven for 25 to 30 minutes. Do not overcook.\n" +
                        "To Make Frosting: Combine 3 tablespoons softened butter, 3 tablespoons cocoa, honey, 1 teaspoon vanilla extract, and 1 cup confectioners' sugar. Stir until smooth. Frost brownies while they are still warm.",
                "1/2 cup butter\n" +
                        "1 cup white sugar\n" +
                        "2 eggs\n" +
                        "1 teaspoon vanilla extract\n" +
                        "1/3 cup unsweetened cocoa powder\n" +
                        "1/2 cup all-purpose flour\n" +
                        "1/4 teaspoon salt\n" +
                        "1/4 teaspoon baking powder",
                45,
                "brownies images",
                false,
                date
        ));

        recipeList.add(new Recipe(
                3,
                "Chicken Pasta",
                "Bring a large pot of lightly salted water to a boil. Add linguini pasta, and cook for 8 to 10 minutes, or until al dente; drain.\n" +
                        "Meanwhile, place chicken and Cajun seasoning in a bowl, and toss to coat.\n" +
                        "In a large skillet over medium heat, saute chicken in butter until no longer pink and juices run clear, about 5 to 7 minutes. Add green and red bell peppers, sliced mushrooms and green onions; cook for 2 to 3 minutes. Reduce heat, and stir in heavy cream. Season the sauce with basil, lemon pepper, salt, garlic powder and ground black pepper, and heat through.\n" +
                        "In a large bowl, toss linguini with sauce. Sprinkle with grated Parmesan cheese.",
                "4 ounces linguine pasta\n" +
                        "2 boneless, skinless chicken breast halves, sliced into thin strips\n" +
                        "2 teaspoons Cajun seasoning\n" +
                        "2 tablespoons butter1 green bell pepper, chopped\n" +
                        "1/2 red bell pepper, chopped\n" +
                        "4 fresh mushrooms, sliced1 green onion, minced\n" +
                        "1 1/2 cups heavy cream\n" +
                        "1/4 teaspoon dried basil\n" +
                        "1/4 teaspoon lemon pepper\n" +
                        "1/4 teaspoon salt\n" +
                        "1/8 teaspoon garlic powder\n" +
                        "1/8 teaspoon ground black pepper\n" +
                        "2 tablespoons grated Parmesan cheese",
                30,
                "pasta images",
                true,
                date
        ));

        recipeList.add(new Recipe(
                4,
                "Greek Salad",
                "Whisk olive oil, vinegar, dill, salt, and black pepper together in a bowl.\n" +
                        "Mix cucumber, broccoli, cauliflower, plum tomatoes, red cabbage, red onion, red bell pepper, green bell pepper, olives, and feta cheese together in a large bowl. Drizzle dressing over vegetable mixture; toss to coat. Refrigerate at least 1 hour to allow flavors to marinate.",
                "3/4 cup olive oil\n" +
                        "1/4 cup red wine vinegar\n" +
                        "1/4 cup chopped fresh dill\n" +
                        "salt and ground black pepper to taste\n" +
                        "1 cucumber, peeled and diced1 cup chopped broccoli\n" +
                        "1 cup chopped cauliflower2 plum tomatoes, diced\n" +
                        "1/4 head red cabbage, shredded\n" +
                        "1/4 large red onion, diced\n" +
                        "1/2 red bell pepper, chopped\n" +
                        "1/2 green bell pepper, chopped\n" +
                        "1 (5 ounce) jar pimento-stuffed green olives, sliced\n" +
                        "1 (4 ounce) package feta cheese, crumbled",
                30,
                "salad images",
                false,
                date
        ));

        List<RecipeTag> TagList1 = recipeList.get(0).getRecipeTagList();
        TagList1.add(new RecipeTag("dessert"));
        TagList1.add(new RecipeTag("cake"));

        List<RecipeTag> TagList2 = recipeList.get(1).getRecipeTagList();
        TagList2.add(new RecipeTag(("dessert")));

        List<RecipeTag> TagList3 = recipeList.get(2).getRecipeTagList();
        TagList3.add(new RecipeTag(("pasta")));

        List<RecipeTag> TagList4 = recipeList.get(3).getRecipeTagList();
        TagList4.add(new RecipeTag(("salad")));
    }

    @Override
    public List<Recipe> getRecipeList() {
        //default sorted by date in ascending order
        List<Recipe> recipeListSortByDateAscending = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            recipeListSortByDateAscending.add(recipeList.get(i));
        }

        Collections.sort(recipeListSortByDateAscending, new Comparator<Recipe>() {
            public int compare(Recipe recipe1, Recipe recipe2) {
                return Integer.valueOf(recipe1.getRecipeID()).compareTo(recipe2.getRecipeID());
            }
        });

        return recipeListSortByDateAscending;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeID() == recipeId) {
                return recipeList.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByFavourite(boolean isFavourite) {
        List<Recipe> recipeListByFavourite = new ArrayList<>();

        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeIsFavourite() == isFavourite) {
                recipeListByFavourite.add(recipeList.get(i));
            }
        }

        //sort the result by recipeId in ascending order
        Collections.sort(recipeListByFavourite, new Comparator<Recipe>() {
            public int compare(Recipe recipe1, Recipe recipe2) {
                return Integer.valueOf(recipe1.getRecipeID()).compareTo(recipe2.getRecipeID());
            }
        });

        return recipeListByFavourite;
    }

    @Override
    public Recipe insertRecipe(Recipe currentRecipe) {
        int index;
        index = recipeList.indexOf(currentRecipe);

        if(index >= 0) {
            // check for duplicates
            return null;
        }

        recipeList.add(currentRecipe);
        return currentRecipe;
    }

    @Override
    public Recipe updateRecipe(Recipe currentRecipe) {
        int index;
        index = recipeList.indexOf(currentRecipe);

        if (index < 0) {
            //if currentRecipe does not exist in the list
            return null;
        }

        recipeList.set(index, currentRecipe);
        return currentRecipe;
    }

    @Override
    public void deleteRecipe(Recipe currentRecipe) {
        int index;
        index = recipeList.indexOf(currentRecipe);

        if(index < 0) {
            //if currentRecipe does not exist in the list
            return;
        }

        recipeList.remove(index);
    }

    @Override
    public void deleteRecipeById(int recipeId) {
        for(int i = 0; i < recipeList.size(); i ++) {
            if(recipeList.get(i).getRecipeID() == recipeId) {
                recipeList.remove(i);
                return;
            }
        }
    }
}
