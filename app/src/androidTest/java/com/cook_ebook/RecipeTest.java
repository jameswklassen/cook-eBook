package com.cook_ebook;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cook_ebook.objects.Recipe;
import com.cook_ebook.presentation.MainActivity;
import com.cook_ebook.utils.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeTest {
    private final int sleepTime = 500;
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    private TestUtils testUtils;

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
    }

    @Test
    public void viewRecipeTest() {
        Recipe recipe = testUtils.getRecipeByPosition(1);

        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        SystemClock.sleep(sleepTime);

        // verify if the information has been showed as expect
        onView(withId(R.id.toolbar_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(withText(recipe.getRecipeDescription())));
        onView(withId(R.id.ingredients)).check(matches(withText(recipe.getRecipeIngredients())));
        onView(withId(R.id.time)).check(matches(withText(containsString(recipe.getRecipeCookingTime() + ""))));
        onView(withId(R.id.recipe_date)).check(matches(withText(containsString(recipe.getRecipeDate().toString()))));

        pressBack();
    }

    @Test
    public void editRecipeTest() {
        Recipe recipe = testUtils.getRecipeByPosition(1);
        String originalTitle = recipe.getRecipeTitle();
        String originalDescription = recipe.getRecipeDescription();
        String originalIngredients = recipe.getRecipeIngredients();
        String originalCookingTime = recipe.getRecipeCookingTime() + "";

        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        SystemClock.sleep(sleepTime);
        onView(withText("Edit Recipe")).perform(click());

        onView(withId(R.id.addTitle)).perform(clearText());
        onView(withId(R.id.addTitle)).perform(typeText("Test title"));
        onView(withId(R.id.addDescription)).perform(clearText());
        onView(withId(R.id.addDescription)).perform(typeText("Test description"));
        onView(withId(R.id.addIngredients)).perform(clearText());
        onView(withId(R.id.addIngredients)).perform(typeText("Test ingredients"));
        onView(withId(R.id.addTime)).perform(clearText());
        onView(withId(R.id.addTime)).perform(typeText("99999"));

        onView(withId(R.id.save_recipe)).perform(click());
        SystemClock.sleep(sleepTime);
        pressBack();

        // verify if the information has been showed as expect
        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.toolbar_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(withText("Test description")));
        onView(withId(R.id.ingredients)).check(matches(withText("Test ingredients")));
        onView(withId(R.id.time)).check(matches(withText(containsString(99999 + ""))));

        pressBack();

        // reset the edited recipe
        recipe.setRecipeTitle(originalTitle);
        recipe.setRecipeDescription(originalDescription);
        recipe.setRecipeIngredients(originalIngredients);
        recipe.setRecipeCookingTime(Integer.parseInt(originalCookingTime));

        testUtils.updateRecipe(recipe);
    }

    @Test
    public void addRecipeTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        SystemClock.sleep(sleepTime);
        onView(withText("Add Recipe")).perform(click());

        // add a recipe
        onView(withId(R.id.addTitle)).perform(typeText("Grilled Lemon Chicken"));
        onView(withId(R.id.addDescription)).perform(typeText("So simple, so sweet!"));
        onView(withId(R.id.addIngredients)).perform(typeText("3/4 cup thawed lemonade concentrate..."));
        onView(withId(R.id.addTime)).perform(typeText("60"));

        onView(withId(R.id.save_recipe)).perform(click());

        // verify the recipe has been added
        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.description)).check(matches(withText("So simple, so sweet!")));
        onView(withId(R.id.ingredients)).check(matches(withText("3/4 cup thawed lemonade concentrate...")));
        onView(withId(R.id.time)).check(matches(withText(containsString(60 + ""))));
    }

    @Test
    public void deleteRecipeTest() {
        Recipe recipe = testUtils.getRecipeByPosition(testUtils.getTotalNumberRecipes() - 1);
        int originalRecipeNumber = testUtils.getTotalNumberRecipes();

        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(testUtils.getTotalNumberRecipes() - 1, click()));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        SystemClock.sleep(sleepTime);
        onView(withText("Delete Recipe")).perform(click());
        SystemClock.sleep(sleepTime);
        onView(withText("Yes")).perform(click());
        SystemClock.sleep(sleepTime);

        // verify
        testUtils = new TestUtils();
        assertEquals(originalRecipeNumber - 1, testUtils.getTotalNumberRecipes());
        SystemClock.sleep(sleepTime);
        // add the deleted recipe back
        testUtils.insertRecipe(recipe);
    }

    @Test
    public void sortByDateOldestRecipeTest() {
    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    SystemClock.sleep(sleepTime);
    onView(withText("Sort List")).perform(click());

    onView(withText("Date-Oldest")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

    onView(withText("OK")).perform(click());

    // verify recipes' dates
    Recipe olderRecipe = testUtils.getRecipeByPosition(1);
    Recipe newerRecipe = testUtils.getRecipeByPosition(2);

    olderRecipe.getRecipeDate().before(newerRecipe.getRecipeDate());
    }

    @Test
    public void duplicateRecipeTest() {
        Recipe recipe = testUtils.getRecipeByPosition(0);
        int originalRecipeNumber = testUtils.getTotalNumberRecipes();

        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        SystemClock.sleep(sleepTime);
        onView(withText("Duplicate Recipe")).perform(click());
        onView(withId(R.id.save_recipe)).perform(click());
        SystemClock.sleep(sleepTime);

        // verify if the information has been showed as expect
        testUtils = new TestUtils();
        assertEquals(originalRecipeNumber + 1, testUtils.getTotalNumberRecipes());
        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.toolbar_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(withText(recipe.getRecipeDescription())));
        onView(withId(R.id.ingredients)).check(matches(withText(recipe.getRecipeIngredients())));
        onView(withId(R.id.time)).check(matches(withText(containsString(recipe.getRecipeCookingTime() + ""))));

        pressBack();
        SystemClock.sleep(sleepTime);
    }

    @Test
    public void filterRecipeTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        SystemClock.sleep(sleepTime);
        onView(withText("Filter List")).perform(click());

        onView(withText("dessert")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        onView(withText("Apply")).perform(click());

        // verify
        onView(allOf(withId(R.id.recipe_name), isDisplayed())).check(matches((withText("Chocolate Chip Cookies"))));
    }

    @Test
    public void readRecipeTest() {
        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.play_button)).perform(click());
        SystemClock.sleep(5000);

        onView(withId(R.id.play_button)).perform(click());
        SystemClock.sleep(5000);

        onView(withId(R.id.play_button)).perform(click());
        SystemClock.sleep(10500);

        pressBack();
    }

    @Test
    public void shareRecipeTest() {
        onView(allOf(withId(R.id.recycleView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
    }
}
