package comp3350.recimeal.application;
/*
This class runs an automated acceptance tests for the Search for Recipes feature
 */
import static android.os.SystemClock.sleep;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
public class SearchTest {

    //onView(withId(R.id.searchRecipeText)).perform(typeText("Spanish"));
    //onView(withId(R.id.searchRecipeButton)).perform(click());
}
