package com.cook_ebook.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import com.cook_ebook.R;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

public class SingleRecipe extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton favourite_btn;
    private FloatingActionButton speech_button;
    private boolean favourite = false;
    private boolean playSpeech = true;
    private boolean update = false;
    private static final String TAG = "SingleActivity";
    public static final int ADD_ACTIVITY = 1;
    private TextToSpeech text_to_speech;

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

        final CollapsingToolbarLayout layout = findViewById(R.id.toolbar_layout);
        layout.setTitle(extras.getString("recipeTitle"));

        final TextView description = findViewById(R.id.description);
        description.setText(extras.getString("recipeDescription"));

        final TextView ingredients = findViewById(R.id.ingredients);
        ingredients.setText(extras.getString("recipeIngredients"));

        final TextView time = findViewById(R.id.time);
        String cookTime = extras.getInt("recipeTime") + " minutes";
        time.setText(cookTime);

        TextView tags = findViewById(R.id.tags);
        tags.setText(extras.getString("recipeTags"));

        TextView date = findViewById(R.id.recipe_date);
        String recipeDate = "Last Modified: " + extras.getString("recipeDate");
        date.setText(recipeDate);

        favourite = extras.getBoolean("recipeFavourite");
        setFavouriteImage();

        speech_button = findViewById(R.id.play_button);

        text_to_speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    text_to_speech.setLanguage(Locale.UK);
                }
            }
        });
//
        speech_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playSpeech) {
                    // recipe title
                    String toSpeak = getRecipeTitle() + " Recipe";
                    speakPhrase(toSpeak);

                    // cooking time
                    toSpeak = "This Recipe has " + getRecipeTime() + " cooking time. ";
                    speakPhrase(toSpeak);

                    // recipe ingredients
                    toSpeak = "Ingredients " + getRecipeIngredients();
                    speakPhrase(toSpeak);

                    // recipe description
                    toSpeak = "Description " + getRecipeDescription();
                    speakPhrase(toSpeak);
                }else
                {
                    text_to_speech.stop();
                }

                setPlayImage();
            }
        });
    }

    private String getRecipeDescription() {
        return extras.getString("recipeDescription");
    }

    private String getRecipeIngredients() {
        return extras.getString("recipeIngredients");
    }

    private String getRecipeTitle() {
        return extras.getString("recipeTitle");
    }

    private String getRecipeTime() {
        return extras.getInt("recipeTime") + " minutes";
    }

    private void speakPhrase(String text) {
        text_to_speech.speak(text, TextToSpeech.QUEUE_ADD, null, null);
        text_to_speech.playSilentUtterance(1000, TextToSpeech.QUEUE_ADD, null);
    }

    private void setPlayImage() {
        if (playSpeech) {
            speech_button.setImageResource(R.drawable.stop_icon);
        } else {
            speech_button.setImageResource(R.drawable.play_icon);
        }
        playSpeech = !playSpeech;
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

    private void updateFavourite() {
        if(favourite_btn != null) {
            favourite = !favourite;
            update = true;
            setFavouriteImage();
        }
    }
    private void setFavouriteImage() {
        if(favourite_btn != null) {
            if (favourite) {
                favourite_btn.setImageResource(R.drawable.heart_filled);
            } else {
                favourite_btn.setImageResource(R.drawable.heart_empty);
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
        Intent intent = deleteIntent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private Intent deleteIntent() {
        Intent myIntent = new Intent();
        myIntent.putExtra("doDelete", extras.getInt("recipeID"));

        return myIntent;
    }

    private Intent favouriteIntent() {
        Intent myIntent = new Intent();
        myIntent.putExtra("toggleFavourite", extras.getInt("recipeID"));
        myIntent.putExtra("favourite", favourite);

        return myIntent;
    }

    private void editRecipe(){
        Intent intent = new Intent(getApplicationContext(),AddEditView.class);
        intent.putExtra("recipe_key",extras);
        intent.putExtra("createRecipe",false);
        startActivityForResult(intent, ADD_ACTIVITY);
    }

    private void shareRecipe() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, getRecipeTitle());
        intent.putExtra(Intent.EXTRA_TEXT, formatEmailText());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String formatEmailText() {
        String text = "Title: \n" + getRecipeTitle();
        text += "\n\nCooking Time: \n" + getRecipeTime();
        text += "\n\nIngredients: \n" + getRecipeIngredients();
        text += "\n\nDescription: \n" + getRecipeDescription();
        text += "\n\nSent using Cook-ebook\n\n";
        return text;
    }

    private void duplicateRecipe(){
        Intent intent = new Intent(getApplicationContext(),AddEditView.class);
        intent.putExtra("recipe_key",extras);
        intent.putExtra("duplicateRecipe", true);
        startActivityForResult(intent, ADD_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            Log.d(TAG, "Result code was NOT okay. " + requestCode);
            return;
        }

        if (requestCode == ADD_ACTIVITY) {
            data.putExtra("favourite", favourite);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        text_to_speech.stop();
        if(update) {
            Intent intent = favouriteIntent();
            setResult(RESULT_OK, intent);
            finish();
        }
        super.onBackPressed();
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
        } else if (id == R.id.edit_recipe){
            editRecipe();
            return true;
        } else if (id == R.id.duplicate_recipe){
            duplicateRecipe();
            return true;
        } else if (id == R.id.share_recipe) {
            shareRecipe();
            return true;
        } else {
            if(update) {
                Intent intent = favouriteIntent();
                setResult(RESULT_OK, intent);
            }
            text_to_speech.stop();
            finish();
            return true;
        }
    }
}
