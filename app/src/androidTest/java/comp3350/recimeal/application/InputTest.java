package comp3350.recimeal.application;
/*
This class runs an automated acceptance tests for the Input Recipe feature
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

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.recimeal.R;
import comp3350.recimeal.presentation.MainActivity;


import static org.hamcrest.CoreMatchers.anything;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InputTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void inputRecipe()
    {
        onView(withId(R.id.newRecipe)).perform(click());
        onView(withId(R.id.createTitleText)).perform(typeText("Test Recipe"));
        onView(withId(R.id.createDescriptionText)).perform(typeText("Test Description"));
        onView(withId(R.id.createIngredientAmount)).perform(typeText("1 test"));
        onView(withId(R.id.createIngredientName)).perform(typeText("testatos"));
        onView(withId(R.id.addIngredientButton)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.createPrepText)).perform(typeText("Make a Test"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        pressBack();
        sleep(500);
        pressBack();
        sleep(500);
        onData(anything()).inAdapterView(withId(R.id.listRecipes)).atPosition(6).perform(click());
        sleep(1000);
        onView(withId(R.id.textRecipeTitle)).check(matches(withText("Test Recipe")));
        sleep(1000);

    }
}
