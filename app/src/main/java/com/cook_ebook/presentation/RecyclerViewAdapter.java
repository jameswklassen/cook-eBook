package com.cook_ebook.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cook_ebook.R;
import com.cook_ebook.objects.Recipe;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<Recipe> recipes;
    private Context _context;

    public RecyclerViewAdapter(List<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this._context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.recipeName.setText(recipes.get(i).getRecipeTitle());
        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(_context, SingleRecipe.class);
                myIntent.putExtra("recipeTitle", recipes.get(i).getRecipeTitle());
                myIntent.putExtra("recipeDescription", recipes.get(i).getRecipeDescription());
                myIntent.putExtra("recipeIngredients", recipes.get(i).getRecipeIngredients());
                myIntent.putExtra("recipeTime", recipes.get(i).getRecipeCookingTime());
                myIntent.putExtra("recipeFavourite", recipes.get(i).getRecipeIsFavourite());
                String tags = tagsToString(recipes.get(i).getRecipeTagSet());
                myIntent.putExtra("recipeTags", tags);
                myIntent.putExtra("recipeID", recipes.get(i).getRecipeID());

                ((Activity)_context).startActivityForResult(myIntent, MainActivity.SINGLE_ACTIVITY);
            }
        });
    }

    private String tagsToString(List<String> tags)
    {
        if(tags.size() == 0)
            return "No Tags";

        String tagString = "";

        for(int i = 0; i< tags.size(); i++)
            tagString += tags.get(i) + "\n";

        return tagString;
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.recipe_name);
            parent = itemView.findViewById(R.id.parent_relative_layout);
        }
    }
}
