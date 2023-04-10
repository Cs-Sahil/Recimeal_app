package comp3350.recimeal;

import static android.os.SystemClock.sleep;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.anything;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import comp3350.recimeal.R;
import comp3350.recimeal.presentation.MainActivity;


/*
This class runs an automated acceptance tests for the Grocery List feature
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class GroceryTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAddRecipeToGrocery() {
        sleep(4500);
        onData(anything()).inAdapterView(withId(R.id.listRecipes)).atPosition(0).perform(click());
        onView(withId(R.id.addToGroceryButton)).perform(click());
        pressBack();
        // Go to grocery tab
        onView(withId(R.id.groceryList)).perform(click());

        // Check ingredients are there
        onView(withId(R.id.listIngredients))
                .check(matches(hasDescendant(withText("yellow onion"))));

        onView(withId(R.id.listIngredients))
                .check(matches(hasDescendant(withText("garlic cloves"))));

        onView(withId(R.id.listIngredients))
                .check(matches(hasDescendant(withText("paprika"))));

        onView(withId(R.id.listIngredients))
                .check(matches(hasDescendant(withText("kosher salt"))));

        onView(withId(R.id.listIngredients))
                .check(matches(hasDescendant(withText("black pepper"))));


    }

}
