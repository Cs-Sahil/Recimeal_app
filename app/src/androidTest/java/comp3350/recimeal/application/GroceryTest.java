package comp3350.recimeal.application;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.matchers.JUnitMatchers.containsString;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.recimeal.R;
import comp3350.recimeal.presentation.GroceryActivity;


/*
This class runs an automated acceptance tests for the Grocery List feature
 */
@RunWith(AndroidJUnit4.class)
public class GroceryTest {
    @Rule
    public ActivityTestRule<GroceryActivity> activityRule = new ActivityTestRule<>(GroceryActivity.class);

    @Test
    public void testTitle() {
        Espresso.onView(withId(R.id.textRecipeTitle)).check(matches(withText(containsString("Grocery List"))));
    }

}
