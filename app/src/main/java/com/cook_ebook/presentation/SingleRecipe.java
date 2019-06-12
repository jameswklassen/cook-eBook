package com.cook_ebook.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.cook_ebook.R;

public class SingleRecipe extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton favourite_btn;
    private boolean favourite = false;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        favourite_btn = findViewById(R.id.fav_button);
        favourite_btn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extras = getIntent().getExtras();

        CollapsingToolbarLayout layout = findViewById(R.id.toolbar_layout);
        layout.setTitle(extras.getString("recipeTitle"));

        TextView description = findViewById(R.id.description);
        description.setText(extras.getString("recipeDescription"));

        TextView ingredients = findViewById(R.id.ingredients);
        ingredients.setText(extras.getString("recipeIngredients"));

        TextView time = findViewById(R.id.time);
        String cookTime = extras.getInt("recipeTime") + " minutes";
        time.setText(cookTime);

        TextView tags = findViewById(R.id.tags);
        tags.setText(extras.getString("recipeTags"));

        favourite = extras.getBoolean("recipeFavourite");
        updateFavourite();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_recipe, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        updateFavourite();
    }

    // TODO: have this update the value in memory
    private void updateFavourite() {
        if(favourite_btn != null) {
            if (favourite) {
                favourite_btn.setImageResource(android.R.drawable.star_big_off);
                favourite = false;
            } else {
                favourite_btn.setImageResource(android.R.drawable.star_big_on);
                favourite = true;
            }
        }
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Confirmation Dialog")
                .setMessage("Are you sure you'd like to delete this recipe? This action cannot be undone.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteRecipe();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteRecipe() {
        Intent intent = generateIntent(true);
        setResult(RESULT_OK, intent);
        finish();
    }

    private Intent generateIntent(boolean doDelete) {
        Intent myIntent = new Intent();
        myIntent.putExtra("doDelete", extras.getInt("recipeID"));

        return myIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_recipe) {
            showConfirmationDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
