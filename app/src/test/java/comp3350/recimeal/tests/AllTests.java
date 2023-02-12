package comp3350.recimeal.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.tests.business.AccessRecipeTest;
import comp3350.recimeal.tests.objects.IngredientTest;
import comp3350.recimeal.tests.objects.RecipeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                AccessRecipeTest.class,
                IngredientTest.class,
                RecipeTest.class
        }
)



public class AllTests {


}
