package comp3350.recimeal.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.recimeal.tests.business.AccessIngredientsIT;
import comp3350.recimeal.tests.business.AccessRecipesIT;


@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                AccessRecipesIT.class,
                AccessIngredientsIT.class
        }
)

public class IntegrationTests{

}
