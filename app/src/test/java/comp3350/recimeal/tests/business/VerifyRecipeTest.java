package comp3350.recimeal.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.business.VerifyRecipes;
import comp3350.recimeal.objects.Recipe;

public class VerifyRecipeTest {

    private VerifyRecipes verifyRecipes;

    @Before
    public void setup(){
        verifyRecipes = new VerifyRecipes();
    }

    @Test
    public void testCreation(){
        assertNotNull("Default constructor should work!", verifyRecipes);
    }

    @Test
    public void testValName() {
        assertEquals("",verifyRecipes.validateName("Normal Food Name"));
        assertEquals("Recipe requires a name.\n",verifyRecipes.validateName(""));
        String longStr = "12345678910111213141516171819202122232425262728293031323334353637383940";
        assertEquals("Recipe name is too long.\n",verifyRecipes.validateName(longStr));
    }

    @Test
    public void testValDesc(){
        assertEquals("",verifyRecipes.validateDescription("Test description."));
        assertEquals("",verifyRecipes.validateDescription(""));
    }

    @Test
    public void testValPrep(){
        assertEquals("",verifyRecipes.validateInstruction("Bake at 200 degrees for 10 minutes."));
        assertEquals("Recipe requires instructions.\n",verifyRecipes.validateInstruction(""));
    }

    @Test
    public void testNumIng(){
        assertEquals("",verifyRecipes.validateNumIngredients(0,1));
        assertEquals("Recipe needs at least one ingredient.\n",verifyRecipes.validateNumIngredients(3,3));
        assertEquals("Recipe needs at least one ingredient.\n",verifyRecipes.validateNumIngredients(4,3));
        assertEquals("Error while indexing ingredients.\n",verifyRecipes.validateNumIngredients(-1,0));
    }
    @Test
    public void testValAll() {
        //good check - no problems
        assertEquals("",verifyRecipes.validateAll(0,4,"good name","description","put in microwave"));
        //missing name
        assertEquals("Recipe requires a name.\n",verifyRecipes.validateAll(0,4,"","description","put in microwave"));
        //missing prep
        assertEquals("Recipe requires instructions.\n",verifyRecipes.validateAll(0,4,"good name","description",""));
        //bad number of ingredients
        assertEquals("Recipe needs at least one ingredient.\n",verifyRecipes.validateAll(5,2,"good name","description","put in microwave"));
        assertEquals("Error while indexing ingredients.\n",verifyRecipes.validateAll(-1,2,"good name","description","put in microwave"));
        //multiple invalid fields
        String expected = "Recipe requires a name.\n";
        expected+="Recipe requires instructions.\n";
        expected+="Recipe needs at least one ingredient.\n";
        assertEquals(expected,verifyRecipes.validateAll(2,2,"","",""));
    }
}
