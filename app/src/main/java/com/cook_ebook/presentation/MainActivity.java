package com.cook_ebook.presentation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTagSet;
import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.R;
import com.cook_ebook.persistence.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final int ADD_ACTIVITY = 1;
    public static final int SINGLE_ACTIVITY = 2;

    //Temporary variables until we have database placeholders merged in
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeHandler handler = new RecipeHandler();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DBHelper.copyDatabaseToDevice(this);
        getRecipes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addRecipe() {
        startActivityForResult(new Intent(getApplicationContext(), AddEditView.class), ADD_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK) {
            Log.d(TAG, "Result code was NOT okay. " + requestCode);
            return;
        }

        Bundle extras = data.getExtras();

        if(requestCode == ADD_ACTIVITY) {
            int time;

            try {
                time = Integer.parseInt(extras.getString("recipeTime"));
            } catch (NumberFormatException | NullPointerException nfe) {
                time = -1; //something is wrong
            }

            String title = extras.getString("recipeTitle");
            String tags = extras.getString("recipeTags");
            String ingredients = extras.getString("recipeIngredients");
            String description = extras.getString("recipeDescription");

            Recipe newRecipe = buildRecipe(time, title, tags, ingredients, description);

            handler.insertRecipe(newRecipe);
            recipes.add(recipes.size(), newRecipe);
            adapter.notifyItemInserted(recipes.size());
        } else if(requestCode == SINGLE_ACTIVITY && data != null) {
            deleteRecipe(extras.getInt("doDelete"));
        }
    }

    private Recipe buildRecipe(int time, String title, String tags, String ingredients, String description) {
        RecipeTagSet newSet = new RecipeTagSet(tags);

        Recipe newRecipe = new Recipe(
                title,
                description,
                ingredients,
                time,
                null,
                newSet,
                false);
        return newRecipe;
    }

    public void deleteRecipe(int id) {
        int index;

        handler.deleteRecipeById(id);
        for(index = 0; index < recipes.size(); index++) {
            if(recipes.get(index).getRecipeID() == id) {
                recipes.remove(index);
                break;
            }
        }

        adapter.notifyItemRemoved(index);
        ((RecyclerView)findViewById(R.id.recycleView)).setAdapter(adapter); //Force a redraw.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_recipe) {
            addRecipe();
            return true;
        } else if(id == R.id.delete_recipe) {
            deleteRecipe(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getRecipes() {
        recipes = handler.getAllRecipes();
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "InitRecyclerView: init recycler view.");

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        adapter = new RecyclerViewAdapter(recipes, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
