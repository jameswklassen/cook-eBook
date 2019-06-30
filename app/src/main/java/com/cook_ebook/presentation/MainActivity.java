package com.cook_ebook.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.cook_ebook.logic.RecipeTagHandler;
import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.logic.RecipeHandler;
import com.cook_ebook.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final int ADD_ACTIVITY = 1;
    public static final int SINGLE_ACTIVITY = 2;

    //Temporary variables until we have database placeholders merged in
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeHandler handler = new RecipeHandler();
    private RecipeTagHandler tagHandler = new RecipeTagHandler();
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
    public boolean onPrepareOptionsMenu (Menu menu) {
        if(handler.getFilter().size() > 0) {
            String text = "" + handler.getFilter().size();
            text += handler.getFilter().size() == 1 ? " Filter" : " Filters";

            menu.findItem(R.id.filter_number).setVisible(true);
            menu.findItem(R.id.filter_number).setTitle(text);
        }
        else {
            menu.findItem(R.id.filter_number).setVisible(false);
        }

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
            recipes = handler.getAllRecipes();
            adapter.setNewList(recipes);
            ((RecyclerView)findViewById(R.id.recycleView)).setAdapter(adapter); //Force a redraw.
        } else if(requestCode == SINGLE_ACTIVITY && data != null) {
            deleteRecipe(extras.getInt("doDelete"));
        }
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
        } else if(id == R.id.sort_list) {
            showSortListDialog();
        } else if(id == R.id.filter_list) {
            showFilterListDialog();
        } else if(id == R.id.filter_number) {
            clearAllFilters();
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

        for(int i = 0; i < tagList.length; i++)
            tagList[i] = tags.get(i).getTagName();

        final boolean[] checkedArray = new boolean[tagList.length];

        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
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

        for(int i = 0; i < checkedArray.length; i++) {
            if(checkedArray[i]) {
                handler.setFilter(tagList[i]);
            }
        }

        //Apply the actual filtering operation
        List<Recipe> allRecipes = handler.getAllRecipes();
        List<String> filters = handler.getFilter();

        if(filters.size() > 0) {
            for (Iterator<Recipe> iter = allRecipes.iterator(); iter.hasNext(); ) {
                Recipe next = iter.next();

                boolean hasTagFromFilter = false;

                for (String tag : filters)
                    if (next.getRecipeTagList().contains(new RecipeTag(tag)))
                        hasTagFromFilter = true;

                if(!hasTagFromFilter)
                    iter.remove();
            }
        }

        //Set the menu text appropriately
        invalidateOptionsMenu();

        //Do a full reset of the recycler view
        doFullRecyclerViewReset(allRecipes);
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
        ((RecyclerView)findViewById(R.id.recycleView)).setAdapter(adapter); //Force a redraw.
        invalidateOptionsMenu();

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
