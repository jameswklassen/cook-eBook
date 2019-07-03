package com.cook_ebook.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;

import com.cook_ebook.logic.RecipeTagHandler;
import com.cook_ebook.logic.RecipeValidator;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.R;
import com.cook_ebook.persistence.utils.DBHelper;

import org.hsqldb.lib.tar.DbBackup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final int ADD_ACTIVITY = 1;
    public static final int SINGLE_ACTIVITY = 2;

    private boolean viewingFavourites = false;

    //Temporary variables until we have database placeholders merged in
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeHandler handler;
    private RecipeTagHandler tagHandler = new RecipeTagHandler(true);
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper.copyDatabaseToDevice(this);

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

        handler = new RecipeHandler(true);

        getRecipes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.filter_number).setVisible(false);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem filterNumber = menu.findItem(R.id.filter_number),
                 filterOption = menu.findItem(R.id.filter_list);

        if (handler.getFilter().size() > 0) {
            String text = "" + handler.getFilter().size();
            text += handler.getFilter().size() == 1 ? " Filter" : " Filters";

            filterNumber.setVisible(true);
            filterNumber.setTitle(text);
        } else {
            filterNumber.setVisible(false);
        }

        if (viewingFavourites) {
            menu.findItem(R.id.favourites_icon).setIcon(R.drawable.filled_favorite);
            filterOption.setEnabled(false);
        } else {
            menu.findItem(R.id.favourites_icon).setIcon(R.drawable.outline_favorite);
            filterOption.setEnabled(true);
        }

        return true;
    }

    public void addRecipe() {
        startActivityForResult(new Intent(getApplicationContext(), AddEditView.class), ADD_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            Log.d(TAG, "Result code was NOT okay. " + requestCode);
            return;
        }

        Bundle extras = data.getExtras();

        if (requestCode == ADD_ACTIVITY || (requestCode == SINGLE_ACTIVITY && extras.containsKey("update"))) {
            int time = Integer.parseInt(extras.getString("recipeTime"));
            String title = extras.getString("recipeTitle");
            String tags = extras.getString("recipeTags");
            String ingredients = extras.getString("recipeIngredients");
            String description = extras.getString("recipeDescription");


            if (extras.containsKey("update")) {
                // change this once code is pulled
                int recipeId = extras.getInt("recipeID");
                Date date = new Date();
                Recipe newRecipe = buildRecipe(recipeId, time, title, tags, ingredients, description, date);
                updateRecipe(newRecipe);
            }else{
                Recipe newRecipe = buildRecipe(time, title, tags, ingredients, description);
                handler.insertRecipe(newRecipe);
            }

            recipes = handler.getAllRecipes();
            adapter.setNewList(recipes);
            ((RecyclerView) findViewById(R.id.recycleView)).setAdapter(adapter); //Force a redraw.
        } else if(requestCode == SINGLE_ACTIVITY && data != null) {
            if(extras.containsKey("doDelete")) {
                deleteRecipe(extras.getInt("doDelete"));
            } else if (extras.containsKey("toggleFavourite")) {
                int index = extras.getInt("toggleFavourite");
                if (index < recipes.size()) {
                    Recipe recipe = recipes.get(index);
                    recipe.setRecipeIsFavourite(!recipe.getRecipeIsFavourite());
                    updateRecipe(recipe);
                }
            }

        }
    }

    public void updateRecipe(Recipe recipe) {
        handler.updateRecipe(recipe);
    }

    private Recipe buildRecipe(int time, String title, String tags, String ingredients, String description) {
        RecipeTag newSet = new RecipeTag(tags);

        Recipe newRecipe = new Recipe(
                title,
                description,
                ingredients,
                time,
                null,
                false);

        newRecipe.addRecipeTag(newSet);

        return newRecipe;
    }

    private Recipe buildRecipe(int id, int time, String title, String tags, String ingredients, String description, Date date) {
        RecipeTag newSet = new RecipeTag(tags);

        Recipe newRecipe = new Recipe(
                id,
                title,
                description,
                ingredients,
                time,
                null,
                false,
                date);

        newRecipe.addRecipeTag(newSet);

        return newRecipe;
    }

    public void deleteRecipe(int id) {
        int index;

        handler.deleteRecipeById(id);
        for (index = 0; index < recipes.size(); index++) {
            if (recipes.get(index).getRecipeID() == id) {
                recipes.remove(index);
                break;
            }
        }

        adapter.notifyItemRemoved(index);
        ((RecyclerView) findViewById(R.id.recycleView)).setAdapter(adapter); //Force a redraw.
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
        } else if (id == R.id.delete_recipe) {
            deleteRecipe(0);
            return true;
        } else if (id == R.id.sort_list) {
            showSortListDialog();
        } else if (id == R.id.filter_list) {
            showFilterListDialog();
        } else if (id == R.id.filter_number) {
            clearAllFilters();
        } else if (id == R.id.favourites_icon) {
            viewingFavourites = !viewingFavourites;

            updateFavouritesView();
            invalidateOptionsMenu();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortListDialog() {
        final String[] sorts = new String[] {
            "Date-Ascending",
            "Date-Descending",
            "Title-Ascending",
            "Title-Descending"
        };

        //For some reason I can't assign to a non-array from within the onClick listener.
        final int[] checkedOption = new int[1];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        builder.setSingleChoiceItems(sorts, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedOption[0] = which;
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.setSort(sorts[checkedOption[0]]);
                doFullRecyclerViewReset(handler.getAllRecipes());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing.
            }
        });

        builder.show();
    }

    private void showFilterListDialog() {
        List<RecipeTag> tags = tagHandler.getAllRecipeTags();
        final String[] tagList = new String[tags.size()];

        for (int i = 0; i < tagList.length; i++)
            tagList[i] = tags.get(i).getTagName();

        final boolean[] checkedArray = new boolean[tagList.length];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter By");

        builder.setMultiChoiceItems(tagList, checkedArray, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedArray[which] = isChecked;
            }
        });

        builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                applyFilters(tagList, checkedArray);
            }
        });

        builder.setNegativeButton("Clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAllFilters();
            }
        });

        builder.show();
    }

    private void applyFilters(String[] tagList, boolean[] checkedArray) {
        handler.resetFilter();
        List<Recipe> allRecipes = handler.filter(tagList, checkedArray);

        //Set the menu text appropriately
        invalidateOptionsMenu();

        //Do a full reset of the recycler view
        doFullRecyclerViewReset(allRecipes);
    }

    private void updateFavouritesView() {
        if (viewingFavourites)
            handler.resetFilter();

        handler.setFavourite(viewingFavourites);
        doFullRecyclerViewReset(handler.getAllRecipes());
    }

    private void clearAllFilters() {
        handler.resetFilter();
        doFullRecyclerViewReset(handler.getAllRecipes());
        invalidateOptionsMenu();
    }

    private void doFullRecyclerViewReset(List<Recipe> newRecipeList) {
        recipes = newRecipeList;
        adapter.setNewList(newRecipeList);
        adapter.notifyDataSetChanged();
        ((RecyclerView) findViewById(R.id.recycleView)).setAdapter(adapter); //Force a redraw.
        invalidateOptionsMenu();
    }

    private void getRecipes() {
        recipes = handler.getAllRecipes();
        initRecyclerView();
    }

    private void showConfirmationDialog(final Recipe recipe) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_delete)
                .setTitle("Confirmation Dialog")
                .setMessage("Are you sure you'd like to delete this recipe? This action cannot be undone.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String message = recipe.getRecipeTitle() + " recipe deleted";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        deleteRecipe(recipe.getRecipeID());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doFullRecyclerViewReset(handler.getAllRecipes());
                    }
                 })
                .show();
    }

    private void initRecyclerView() {
        Log.d(TAG, "InitRecyclerView: init recycler view.");

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        adapter = new RecyclerViewAdapter(recipes, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Item swiped.
                int pos = viewHolder.getAdapterPosition();
                Recipe recipe = recipes.get(pos);
                showConfirmationDialog(recipe);
            }
        };

        // Attach it to recyclerview
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }
}
