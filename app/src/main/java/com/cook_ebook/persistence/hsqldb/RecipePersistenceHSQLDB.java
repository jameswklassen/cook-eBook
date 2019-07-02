package com.cook_ebook.persistence.hsqldb;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipePersistence;
import com.cook_ebook.persistence.utils.DBHelper;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipePersistenceHSQLDB implements RecipePersistence {

    private final String dbPath;
    private List<Recipe> recipes;

    public RecipePersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
         this.recipes = new ArrayList<>();
         loadRecipes();
    }

    private Connection connect() throws SQLException {
        System.out.println("[LOG] Connecting Recipe Persistence " + dbPath);
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Recipe fromResultSet(final ResultSet rs) throws SQLException {
        int recipeID = rs.getInt("id");
        String recipeTitle = rs.getString("title");
        String recipeDescription = rs.getString("description");
        String recipeIngredients = rs.getString("ingredients");
        int recipeCookingTime = rs.getInt("cooking_time");
        String recipeImages = rs.getString("image");
        Boolean recipeIsFavourite = rs.getBoolean("favourite");
        Date date = rs.getDate("creation_date");

        return new Recipe(recipeID, recipeTitle, recipeDescription, recipeIngredients, recipeCookingTime, recipeImages, recipeIsFavourite, date);
    }

    private void loadRecipes() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM RECIPES");

            while (resultSet.next()) {
                final Recipe recipe = fromResultSet(resultSet);
                this.recipes.add(recipe);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public List<Recipe> getRecipeList() {
        return recipes;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM RECIPES WHERE RECIPES.id = ?");
            statement.setString(1, Integer.toString(recipeId));
            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return fromResultSet(resultSet);
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByCookingTime(int cookingTime) {
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByTagName(String tagName) {
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByTagId(int tagId) {
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByTag(RecipeTag tag) {
        return null;
    }

    @Override
    public List<Recipe> getRecipeListByFavourite(boolean isFavourite) {
        List<Recipe> favourites = new ArrayList<>();

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM RECIPES WHERE favourite = ?");
            statement.setBoolean(1, isFavourite);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                final Recipe recipe = fromResultSet(resultSet);
                favourites.add(recipe);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return favourites;
    }

    @Override
    public Recipe insertRecipe(Recipe recipe) {
        System.out.println("[LOG] INSERTING RECIPE");
        String dateString = DBHelper.getSQLDateString(recipe.getRecipeDate());

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO RECIPES VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, recipe.getRecipeTitle());
            statement.setString(2, recipe.getRecipeDescription());
            statement.setString(3, recipe.getRecipeIngredients());
            statement.setInt(4, recipe.getRecipeCookingTime());
            statement.setString(5, recipe.getRecipeImages());
            statement.setBoolean(6, recipe.getRecipeIsFavourite());
            statement.setString(7, dateString);

            statement.executeUpdate();
            recipes.add(recipe);

            return recipe;

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    public Recipe updateRecipe(Recipe newRecipe) {
        String dateString = DBHelper.getSQLDateString(newRecipe.getRecipeDate());

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE RECIPES set title = ?, description = ?, ingredients = ?, cooking_time = ?, image = ?, favourite = ?, creation_date = ? WHERE id = ?");
            statement.setString(1, newRecipe.getRecipeTitle());
            statement.setString(2, newRecipe.getRecipeDescription());
            statement.setString(3, newRecipe.getRecipeIngredients());
            statement.setInt(4, newRecipe.getRecipeCookingTime());
            statement.setString(5, newRecipe.getRecipeImages());
            statement.setBoolean(6, newRecipe.getRecipeIsFavourite());
            statement.setString(7, dateString);
            statement.setInt(8, newRecipe.getRecipeID());

            statement.executeUpdate();

            int index = recipes.indexOf(newRecipe);
            recipes.set(index, newRecipe);

            return newRecipe;

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    public void deleteRecipe(Recipe recipe) {
        System.out.println("[LOG] Deleting recipe");
        deleteRecipeById(recipe.getRecipeID());
    }

    public void deleteRecipeById(int recipeId) {
        System.out.println("[LOG] Deleting recipe by ID " + recipeId);

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM RECIPES WHERE id = ?");
            statement.setString(1, Integer.toString(recipeId));
            statement.executeUpdate();

            for (Recipe recipe : recipes) {
                if (recipe.getRecipeID() == recipeId) {
                    recipes.remove(recipe);
                    break;
                }
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }
}
