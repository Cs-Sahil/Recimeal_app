package comp3350.recimeal.tests.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.objects.PermanentRecipe;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

public class AccessRecipesTest {
    private AccessRecipes accessRecipes;
    private RecipePersistence recipePersistence;
    private List<Recipe> testRecipes;

    @Before
    public void setup() {
        this.setTestRecipes();
        accessRecipes = new AccessRecipes(recipePersistence);
    }


    public void setTestRecipes()
    {
        testRecipes = new ArrayList<Recipe>();
        testRecipes.add(new PermanentRecipe(1,"mock duck", "tasty", "make it"));
        recipePersistence = mock(RecipePersistence.class);
        when(recipePersistence.getRecipeSequential()).thenReturn(testRecipes);
    }

    @Test
    public void testGet()
    {
        //this.setup();
        final List<Recipe> tRecipes;

        System.out.println("\nStarting Get Test");

        tRecipes = accessRecipes.getRecipes();
        for(int i =0; i<tRecipes.size();i++)
        {
            assertNotNull("the recipes list items should not be null", tRecipes.get(i));
            assertTrue(testRecipes.get(i).getRecipeName().equals(tRecipes.get(i).getRecipeName()));
        }
        verify(recipePersistence).getRecipeSequential();
        System.out.println("Finished testing Get");

    }

    @Test
    public void testGetId()
    {
        //this.setup();
        Recipe testRecipe ;
        System.out.println("\nStarting Get by ID Test");

        testRecipe = accessRecipes.getRecipeById( -1);
        assertTrue( testRecipe == null);

        testRecipe = accessRecipes.getRecipeById(1);
        assertNotNull("This recipe should be found",testRecipe);
        assertTrue(testRecipe.getRecipeId() ==1);

        verify(recipePersistence).getRecipeSequential();

    }

}
