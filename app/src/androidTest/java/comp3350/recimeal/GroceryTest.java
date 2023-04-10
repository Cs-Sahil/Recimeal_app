package comp3350.recimeal;

import static android.os.SystemClock.sleep;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.recimeal.presentation.GroceryActivity;

import static org.hamcrest.Matchers.containsString;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import comp3350.recimeal.R;
import comp3350.recimeal.presentation.GroceryActivity;
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
    public void testSomething() {
        sleep(4500);
        Espresso.onView(withId(R.id.appTitle)).check(matches(withText(containsString("Recimeal"))));
    }

}
