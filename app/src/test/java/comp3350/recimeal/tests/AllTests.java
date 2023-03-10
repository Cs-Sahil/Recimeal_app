package comp3350.recimeal.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.recimeal.tests.business.VerifyRecipeTest;
import comp3350.recimeal.tests.objects.IngredientTest;
import comp3350.recimeal.tests.objects.RecipeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                IngredientTest.class,
                RecipeTest.class,
                VerifyRecipeTest.class
        }
)



public class AllTests {


}
