package comp3350.recimeal.tests.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;

public class RecipeTest {

    private List<Ingredient> ingredients;
    private Map<Integer, Integer> recipeIngred;
    private final int listLength = 5;

    @Before
    public void setup(){
        ingredients = new ArrayList<>();
        recipeIngred = new TreeMap<>();

        ingredients.add(new Ingredient("ingred1", 1, "unit1"));
        ingredients.add(new Ingredient("ingred2", 2, "unit2"));
        ingredients.add(new Ingredient("ingred3", 3, "unit3"));
        ingredients.add(new Ingredient("ingred4", 4, "unit4"));
        ingredients.add(new Ingredient("ingred5", 5, "unit5"));

        recipeIngred.put((ingredients.get(0)).getId(), 10);
        recipeIngred.put((ingredients.get(1)).getId(), 10);
        recipeIngred.put((ingredients.get(2)).getId(), 10);

    }

    @Test
    public void testRecipeCreation(){

        Recipe recipe1 = new Recipe("Recipe1", "Instructions here", "Description here", recipeIngred);
        Recipe recipe2 = new Recipe("Recipe2", "Instructions here", "Description here");
        Recipe recipe3 = new Recipe("Recipe3", "Instructions here");

        assertNotNull("Recipe should be created with full information", recipe1);
        assertNotNull("Recipe should be created without ingredient tree", recipe2);
        assertNotNull("Recipe should be created without ingredient tree and description", recipe3);

    }

    @Test
    public void testGetters(){
        Recipe recipe1 = new Recipe("Recipe1", "Instructions here", "Description here", recipeIngred);

        assertEquals("Should get the name of \"Recipe1\"!", "Recipe1", recipe1.getRecipeName());
        assertEquals("Should get the instruction of \"Instructions here\"!", "Instructions here", recipe1.getRecipeInstruction());
        assertEquals("Should get the description of \"Description here\"!", "Description here", recipe1.getRecipeDescription());
        assertTrue("Should have the first ingredient amount!", (recipe1.getIngredients().get(1) == 10));
    }

    @Test
    public void testAddIngred(){
        Recipe recipe1 = new Recipe("Recipe1", "Instructions here", "Description here", recipeIngred);

        assertTrue("New ingredient should be added", recipe1.addIngred(6, 10));
        assertFalse("Existing ingredient should be ignored", recipe1.addIngred(1, 10));

    }
}
