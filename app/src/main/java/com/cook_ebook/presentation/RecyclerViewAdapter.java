package com.cook_ebook.presentation;

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

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<String> recipeNames;
    private Context _context;

    public RecyclerViewAdapter(List<String> recipeNames, Context context) {
        this.recipeNames = recipeNames;
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

        viewHolder.recipeName.setText(recipeNames.get(i));
        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(_context, SingleRecipe.class);
                myIntent.putExtra("recipeTitle", recipeNames.get(i));

                _context.startActivity(myIntent, myIntent.getExtras());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeNames.size();
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
