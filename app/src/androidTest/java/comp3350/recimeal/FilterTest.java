package comp3350.recimeal;
/*
This class runs an automated acceptance tests for the Filter the Recipes feature
 */

import static android.os.SystemClock.sleep;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.recimeal.R;
import comp3350.recimeal.presentation.MainActivity;
import comp3350.recimeal.presentation.RecipesActivity;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FilterTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void filterTest(){
        //create a recipe
        onView(withId(R.id.newRecipe)).perform(click());
        onView(withId(R.id.createTitleText)).perform(typeText("My Test Recipe"));
        onView(withId(R.id.createDescriptionText)).perform(typeText("Test Description"));
        onView(withId(R.id.createIngredientAmount)).perform(typeText("2"));
        onView(withId(R.id.createIngredientName)).perform(typeText("Eggs"));
        onView(withId(R.id.addIngredientButton)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.createPrepText)).perform(typeText("Make them how you like."));
        closeSoftKeyboard();

        //search for user created
        onView(withId(R.id.button)).perform(click());
        pressBack();
        onView(withId(R.id.home)).perform(click());

        //click the My Recipes button to turn on the filter
        onView(withId(R.id.switchUser)).perform(click());

        //input nothing
        onView(withId(R.id.searchRecipeText)).perform(typeText(""));
        //click the search button to apply the filter with empty search field to search all favorite recipes
        onView(withId(R.id.searchRecipeButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listRecipes)).atPosition(0).perform(click());
        onView(withId(R.id.textRecipeTitle)).check(matches(withText("My Test Recipe")));

        // Delete test recipe
        onView(withId(R.id.deleteButton)).perform(click());
    }


}
