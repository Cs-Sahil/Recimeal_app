package comp3350.recimeal.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;
import comp3350.recimeal.persistence.hsqldb.RecipeDBPersistence;
import comp3350.recimeal.tests.utils.TestUtils;


public class AccessRecipesIT {

    private AccessRecipes accessRecipes;
    private File tempDB;

    @Before
    public void setUp() throws IOException
    {
        this.tempDB = TestUtils.copyDB();
        System.out.println(this.tempDB);
        final RecipePersistence persistence = new RecipeDBPersistence(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessRecipes = new AccessRecipes(persistence);
    }

    @Test
    public void testGetRecipes() {
        final List<Recipe> recipes;
        recipes = accessRecipes.getRecipes();

        System.out.println("Starting GetRecipes Testing");
        assertNotNull("Recipes should not be null", recipes);
        assertTrue( " should be ", recipes.size() == 6 );
        System.out.println("Ending GetRecipes Testing ");
    }
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }


}

