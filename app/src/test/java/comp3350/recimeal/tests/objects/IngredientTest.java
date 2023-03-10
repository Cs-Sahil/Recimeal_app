package comp3350.recimeal.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import comp3350.recimeal.objects.Ingredient;

public class IngredientTest {

    private Ingredient ingredient1;

    @Before
    public void setup(){
        ingredient1 = new Ingredient(1, "Ingredient1", 2, "kg");
    }

    @Test
    public void testIngredientCreation(){

        assertNotNull("Should be able to create Ingredient with only the name", ingredient1);

    }

    @Test
    public void testGetters(){
        assertEquals("Correct id of \"Ingredient1 should be printed\"", ingredient1.getId(), 1);
        assertEquals("Correct name of \"Ingredient1 should be printed\"", ingredient1.getName(), "Ingredient1");
        assertEquals("Correct unit of \"Ingredient1 should be printed\"", ingredient1.getUnit(), "kg");
    }

    @Test
    public void testEqual(){

        Ingredient ingredient2 = new Ingredient(2, "Ingredient1", 2, "kg");

        assertTrue("Same ingredient should be equal", ingredient1.equals(ingredient1));
        assertTrue("Ingredients with the same name should be equal", ingredient1.equals(ingredient2));

    }

    @Test
    public void testToString(){
        assertEquals("Ingredient1", ingredient1.toString());
    }

}
