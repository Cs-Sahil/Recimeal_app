package comp3350.recimeal.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.objects.Recipe;

public class AccessRecipeTest {

    private AccessRecipes accessRecipes;
    private List<Recipe> fullList;

    @Before
    public void setup(){
        accessRecipes = new AccessRecipes();
        fullList = new ArrayList<>();
      //  fullList.add(new Recipe("recipe1", "instruction1"));
        //fullList.add(new Recipe("recipe1", "instruction2"));
       // fullList.add(new Recipe("recipe2", "instruction2"));
    }

    @Test
    public void testCreation(){
        assertNotNull("Default constructor should work!", accessRecipes);
    }

    @Test
    public void testGetRecipes(){
        List<Recipe> recipes = accessRecipes.getRecipes();
        assertNotNull("Should return a list!", recipes);
        Recipe element = recipes.get(0);
        assertTrue("Should return a list of recipes!", (element instanceof Recipe));

    }

    @Test
    public void testSearchByName(){
        List<Recipe> result = accessRecipes.getSearchedRecipes(fullList, "recipe1");
        assertTrue("Should find all the recipes with the searched name!", result.size() == 2);
        for(int i = 0; i < result.size(); i++) {
            assertEquals("Should find the correct recipe with name \"recipe1\"", result.get(i).getRecipeName(), "recipe1");
        }
    }
}
