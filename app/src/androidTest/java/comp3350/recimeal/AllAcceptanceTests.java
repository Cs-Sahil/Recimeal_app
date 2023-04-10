package comp3350.recimeal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.recimeal.application.FilterTest;
import comp3350.recimeal.application.GroceryTest;
import comp3350.recimeal.application.InputDeleteTest;
import comp3350.recimeal.application.RateTest;
import comp3350.recimeal.application.SearchTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                FilterTest.class,
                GroceryTest.class,
                InputDeleteTest.class,
                RateTest.class,
                SearchTest.class
        }
)


public class AllAcceptanceTests {
}
