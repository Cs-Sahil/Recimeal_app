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
public class SearchTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void SearchRecipe() {
        //tests default search
        onView(withId(R.id.searchRecipeText)).perform(typeText("Spanish"));
        onView(withId(R.id.searchRecipeButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listRecipes)).atPosition(0).perform(click());
        sleep(1000);
        onView(withId(R.id.textRecipeTitle)).check(matches(withText("Spanish Rice and Beans")));
        sleep(1000);
        pressBack();


    }
    @Test
    public void SearchIngredient() {
        // tests searching by ingredient filter
        onView(withId(R.id.switchIng)).perform(click());
        onView(withId(R.id.searchRecipeText)).perform(typeText("parmesan"));
        onView(withId(R.id.searchRecipeButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listRecipes)).atPosition(0).perform(click());
        sleep(1000);
        onView(withId(R.id.textRecipeTitle)).check(matches(withText("Pasta Puttanesca")));
        sleep(1000);
    }


}
