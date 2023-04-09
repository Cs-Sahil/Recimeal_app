package comp3350.recimeal.tests.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.PermanentRecipe;
import comp3350.recimeal.objects.Recipe;

public class RecipeTest {

    private List<Ingredient> ingredients;
    private Map<Integer, Float> recipeIngred;
    private final int listLength = 5;

    @Before
    public void setup(){

    }

    @Test
    public void testRecipeCreation(){

        Recipe recipe1 = new PermanentRecipe(-1, "name", "instruction", "description", "style", "type", true, false, "");
        Recipe recipe2 = new PermanentRecipe(2, "Recipe2", "Instructions here", "Description here");

        assertNotNull("Recipe should be created with full information", recipe1);
        assertNotNull("Recipe should be created with only id, name, instruction and description", recipe2);

    }

    @Test
    public void testGetters(){
        Recipe recipe1 = new PermanentRecipe(1, "name", "description", "instruction", "style", "type", true, false, "");

        assertEquals("Should get the id of \"1\"!", 1, recipe1.getRecipeId());
        assertEquals("Should get the name of \"Recipe1\"!", "name", recipe1.getRecipeName());
        assertEquals("Should get the instruction of \"instruction\"!", "instruction", recipe1.getRecipeInstruction());
        assertEquals("Should get the description of \"description\"!", "description", recipe1.getRecipeDescription());
        assertEquals("Should get the style of \"style\"!", "style", recipe1.getRecipeStyle());
        assertEquals("Should get the type of \"type\"!", "type", recipe1.getRecipeType());
        assertTrue("User created this!", recipe1.getUserCreated());
        assertFalse("Not favorite!", recipe1.getFavorited());

    }

}
