package comp3350.recimeal.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.recimeal.business.AccessIngredients;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.persistence.IngredientPersistence;

import comp3350.recimeal.persistence.hsqldb.IngredientDBPersistence;

import comp3350.recimeal.tests.utils.TestUtils;

public class AccessIngredientsIT {

    private AccessIngredients accessIngredients;
    private File tempDB;

    @Before
    public void setUp() throws IOException
    {
        this.tempDB = TestUtils.copyDB();
        System.out.println(this.tempDB);
        final IngredientPersistence persistence= new IngredientDBPersistence(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessIngredients = new AccessIngredients(persistence);
    }

    @Test
    public void testGetIngredients() {
        final List<Ingredient> ingredients;
        ingredients = accessIngredients.getIngredients();

        System.out.println("Starting GetIngredients Testing");
        assertNotNull("Ingredients should not be null", ingredients);
        assertTrue( " should be ", ingredients.size() == 6 );
        System.out.println("Ending GetIngredients Testing ");
    }
    @Test
    public void testAddIngredient()
    {
        final Ingredient i = new Ingredient(1,"Ing", 10, "tons");
        accessIngredients.addIngredient(i, 1);
        assertEquals(7, accessIngredients.getIngredients().size());
    }


    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
