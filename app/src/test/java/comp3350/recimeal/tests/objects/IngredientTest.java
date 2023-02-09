package comp3350.recimeal.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import comp3350.recimeal.objects.Ingredient;

public class IngredientTest {

    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @Before
    public void setup(){
        ingredient1 = new Ingredient("Ingredient1");
        ingredient2 = new Ingredient("Ingredient2", "kg");
    }

    @Test
    public void testIngredientCreation(){

        assertNotNull("Should be able to create Ingredient with only the name", ingredient1);
        assertNotNull("Should be able to create Ingredient with the name and the unit", ingredient2);

    }

    @Test
    public void testGetters(){

        assertEquals("Correct name of \"Ingredient1 should be printed\"", ingredient1.getName(), "Ingredient1");
        assertEquals("Correct name of \"Ingredient2 should be printed\"", ingredient2.getName(), "Ingredient2");
        assertEquals("Correct unit of \"kg\" should be printed", ingredient2.getUnit(), "kg");
        assertEquals("Default unit \"Unknown\" should be printed", ingredient1.getUnit(), "Unknown");
    }

    @Test
    public void testEqual(){

        Ingredient ingredient3 = new Ingredient("ingredient1", "g");

        assertTrue("Same ingredient should be equal", ingredient1.equals(ingredient1));
        assertTrue("Ingredients with the same name should be equal", ingredient1.equals(ingredient3));

    }

    @Test
    public void testToString(){
        assertEquals("Ingredient1  Measurement Unit:Unknown ", ingredient1.toString());
    }

}
