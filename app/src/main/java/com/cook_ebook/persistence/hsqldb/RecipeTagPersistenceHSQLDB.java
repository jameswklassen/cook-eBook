package com.cook_ebook.persistence.hsqldb;

import android.util.Log;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.objects.RecipeTag;
import com.cook_ebook.persistence.RecipeTagPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecipeTagPersistenceHSQLDB implements RecipeTagPersistence {

    private final String dbPath;
    private List<RecipeTag> tags;

    public RecipeTagPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        this.tags = new ArrayList<>();
        loadTags();
    }

    private Connection connect() throws SQLException {
        System.out.println("[LOG] Connecting Recipe TAG Persistence " + dbPath);
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private RecipeTag fromResultSet(final ResultSet rs) throws SQLException {
        int tagId = rs.getInt("id");
        String tagName = rs.getString("title");

        return new RecipeTag(tagId, tagName);
    }

    private void loadTags() {
        try (Connection connection = connect()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM TAGS");

            while (resultSet.next()) {
                final RecipeTag tag = fromResultSet(resultSet);
                this.tags.add(tag);
            }
        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public List<RecipeTag> getAllTags() {
        return tags;
    }

    @Override
    public int getTagIdByName(String tagName) {
        return -1;
    }

    @Override
    public String getTagNameById(int tagId) {
        return null;
    }

    @Override
    public RecipeTag getTagById(int tagId) {
        return null;
    }

    @Override
    public RecipeTag getTagByName(String tagName) {
        return null;
    }

    @Override
    public RecipeTag insertOneTag(RecipeTag targetTag) {

        if (tags.contains(targetTag)) {
            return null;
        }

        System.out.println("[LOG] INSERTING ONE TAG");
        try (Connection connection = connect()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO TAGS VALUES(DEFAULT, ?)");
            statement.setString(1, targetTag.getTagName());
            statement.executeUpdate();
            statement.close();

            this.tags = new ArrayList<>();
            loadTags();

            return targetTag;

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteOneTag(RecipeTag targetTag) {
    }

    @Override
    public boolean doesTagExist(RecipeTag targetTag) {
        return false;
    }

    // we may not need this method, I am not not sure, so I keep it for temp
    // if we do not need it, we can delete this method before merge to master
    @Override
    public boolean doesTagNameExist(String targetTagName) {
        return false;
    }
}
