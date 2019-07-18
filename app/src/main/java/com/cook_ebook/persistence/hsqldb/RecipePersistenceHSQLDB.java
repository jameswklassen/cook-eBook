package com.cook_ebook.persistence.hsqldb;

import com.cook_ebook.logic.exceptions.RecipeNotFoundException;
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

import com.cook_ebook.persistence.RecipeTagPersistence;

public class RecipePersistenceHSQLDB implements RecipePersistence {

    private final String dbPath;
    private RecipeTagPersistence recipeTagPersistence;
    private List<Recipe> recipes;

    public RecipePersistenceHSQLDB(RecipeTagPersistence recipeTagPersistence, String dbPath) {
        this.dbPath = dbPath;
        this.recipes = new ArrayList<>();
        this.recipeTagPersistence = recipeTagPersistence;
        loadRecipes();
    }

    private Connection connect() throws SQLException {
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
        Date date = rs.getTimestamp("creation_date");

        return new Recipe(recipeID, recipeTitle, recipeDescription, recipeIngredients, recipeCookingTime, recipeImages, recipeIsFavourite, date);
    }

    private void loadRecipes() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM RECIPES");

            while (resultSet.next()) {
                final Recipe recipe = fromResultSet(resultSet);
                connectRecipeWithTags(recipe);
                this.recipes.add(recipe);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    private void connectRecipeWithTags(Recipe recipe) {

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT tag_id FROM RECIPES_TAGS WHERE RECIPES_TAGS.recipe_id = ?");
            statement.setInt(1, recipe.getRecipeID());

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int tagId = resultSet.getInt("tag_id");
                for (RecipeTag tag: recipeTagPersistence.getAllTags()) {
                    if (tag.getTagID() == tagId) {
                        recipe.addRecipeTag(tag);
                        break;
                    }
                }
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    private void addRecipeTagRelation(Connection connection, List<RecipeTag> tags, int recipeId) throws SQLException {
        for (RecipeTag tag : tags) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO RECIPES_TAGS VALUES(?, ?)");
            statement.setInt(1, recipeId);
            statement.setInt(2, tag.getTagID());
            statement.executeUpdate();
            statement.close();
        }
    }

    private void removeRecipeTagRelations(Connection connection, int tagId) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("DELETE FROM RECIPES_TAGS where tag_id = ?");
        statement.setInt(1, tagId);
        statement.executeUpdate();
        statement.close();
    }

    private void removeTag(Connection connection, int tagId) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("DELETE FROM TAGS WHERE id = ?");
        statement.setInt(1, tagId);
        statement.executeUpdate();
        statement.close();
    }

    private void removeAssociatedTags(Connection connection, int recipeId) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement("SELECT tag_id FROM RECIPES_TAGS where recipe_id = ?");
        statement.setInt(1, recipeId);
        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int tagId = resultSet.getInt("tag_id");
            removeRecipeTagRelations(connection, tagId);
            removeTag(connection, tagId);
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
        System.out.println("[LOG] Inserting Recipe " + recipe.getRecipeTitle());
        String dateString = DBHelper.getSQLDateString(recipe.getRecipeDate());

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO RECIPES VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, recipe.getRecipeTitle());
            statement.setString(2, recipe.getRecipeDescription());
            statement.setString(3, recipe.getRecipeIngredients());
            statement.setInt(4, recipe.getRecipeCookingTime());
            statement.setString(5, recipe.getRecipeImage());
            statement.setBoolean(6, recipe.getRecipeIsFavourite());
            statement.setString(7, dateString);

            statement.executeUpdate();
            statement.close();

            // Since the recipe was automatically assigned an ID by the database
            // we need to retrieve the recipe using title + creation date and get its ID
            final PreparedStatement newStatement = connection.prepareStatement("SELECT * FROM RECIPES WHERE title = ? AND creation_date = ?");
            newStatement.setString(1, recipe.getRecipeTitle());
            newStatement.setString(2, dateString);
            final ResultSet resultSet = newStatement.executeQuery();

            if (resultSet.next()) {
                final Recipe recipeWithId = fromResultSet(resultSet);
                addRecipeTagRelation(connection, recipe.getRecipeTagList(), recipeWithId.getRecipeID());
                this.recipes = new ArrayList<>();
                loadRecipes();
            }

            return recipe;

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    public Recipe updateRecipe(Recipe newRecipe) throws RecipeNotFoundException {
        System.out.println("[LOG] UPDATE RECIPE");
        String dateString = DBHelper.getSQLDateString(newRecipe.getRecipeDate());

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE RECIPES set title = ?, description = ?, ingredients = ?, cooking_time = ?, image = ?, favourite = ?, creation_date = ? WHERE id = ?");
            statement.setString(1, newRecipe.getRecipeTitle());
            statement.setString(2, newRecipe.getRecipeDescription());
            statement.setString(3, newRecipe.getRecipeIngredients());
            statement.setInt(4, newRecipe.getRecipeCookingTime());
            statement.setString(5, newRecipe.getRecipeImage());
            statement.setBoolean(6, newRecipe.getRecipeIsFavourite());
            statement.setString(7, dateString);
            statement.setInt(8, newRecipe.getRecipeID());

            statement.executeUpdate();
            statement.close();

            removeRecipeTagRelations(connection, newRecipe.getRecipeID());
            addRecipeTagRelation(connection, newRecipe.getRecipeTagList(), newRecipe.getRecipeID());

            this.recipes = new ArrayList<>();
            loadRecipes();

            return newRecipe;

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        throw new RecipeNotFoundException("Recipe not found");
    }

    public void deleteRecipe(Recipe recipe) throws RecipeNotFoundException {
        System.out.println("[LOG] Deleting recipe: " + recipe.getRecipeTitle());
        deleteRecipeById(recipe.getRecipeID());
    }

    public void deleteRecipeById(int recipeId) throws RecipeNotFoundException {
        System.out.println("[LOG] Deleting recipe by ID " + recipeId);

        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM RECIPES WHERE id = ?");
            statement.setString(1, Integer.toString(recipeId));
            statement.executeUpdate();
            statement.close();

            removeAssociatedTags(connection, recipeId);
            removeRecipeTagRelations(connection, recipeId);

            for (Recipe recipe : recipes) {
                if (recipe.getRecipeID() == recipeId) {
                    recipes.remove(recipe);
                    break;
                }
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
            throw new RecipeNotFoundException("Recipe could not be deleted in the database");
        }
    }
}
