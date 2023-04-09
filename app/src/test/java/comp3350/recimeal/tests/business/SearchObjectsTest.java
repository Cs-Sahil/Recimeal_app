package comp3350.recimeal.tests.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.business.AccessIngredients;
import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.business.SearchObjects;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.PermanentRecipe;
import comp3350.recimeal.objects.Recipe;

public class SearchObjectsTest {

    private SearchObjects searchObjects;
    private AccessRecipes accessRecipes;
    private AccessIngredients accessIngredients;
    @Before
    public void setup()
    {
        List<Recipe> testList = new ArrayList<Recipe>();
        testList.add(new PermanentRecipe(1,"mock duck", "tasty", "make it"));
        testList.add(new PermanentRecipe(2,"mock duck", "tasty", "make it",null,null,true,false, null));
        testList.add(new PermanentRecipe(3,"mock duck", "tasty", "make it",null,null,false,true, null));
        testList.add(new PermanentRecipe(4,"mock duck", "tasty", "make it",null,null,true,true, null));
        testList.add(new PermanentRecipe(5,"dog", "tasty", "make it",null,null,true,true, null));
        testList.add(new PermanentRecipe(6,"soup", "tasty", "make it",null,null,true,true, null));


        this.accessRecipes = mock(AccessRecipes.class);
        when(accessRecipes.getRecipes()).thenReturn(testList);
        this.accessIngredients = mock(AccessIngredients.class);
        List<Ingredient> testIng = new ArrayList<Ingredient>();
        testIng.add(new Ingredient(6,"duck",100,"tons"));
        when(accessIngredients.getRecipeIngredients( 1)).thenReturn(null);
        when(accessIngredients.getRecipeIngredients( 2)).thenReturn(null);
        when(accessIngredients.getRecipeIngredients( 3)).thenReturn(null);
        when(accessIngredients.getRecipeIngredients( 4)).thenReturn(null);
        when(accessIngredients.getRecipeIngredients( 5)).thenReturn(null);
        when(accessIngredients.getRecipeIngredients( 6)).thenReturn(testIng);
        searchObjects = new SearchObjects(accessRecipes,accessIngredients);

    }
    @Test
    public void testSearch()
    {
        this.setup();
        List<Recipe> testResults = searchObjects.getSearchedRecipes("test", false,false,false);
        assertTrue("No results should be found", testResults.size() ==0);
        testResults = searchObjects.getSearchedRecipes("mock", false,false,false);
        assertTrue("Results should be found", testResults.size() ==4);
        for(int i =0; i<testResults.size();i++)
        {
            assertTrue(testResults.get(i).getRecipeName().equals("mock duck"));
        }
    }

    @Test
    public void testEmptySearch()
    {
        List<Recipe> testResults = searchObjects.getSearchedRecipes(null, false,false,false);
        assertTrue("All Should be Returned", testResults.size() == 6 );

    }
    @Test
    public void testIngredient()
    {
        List<Recipe> testResults = searchObjects.getSearchedRecipes("duck", false,false,true);
        assertTrue("Results should be found", testResults.size() ==5);
        assertTrue( testResults.get(4).getRecipeId() ==6);
    }


    @Test
    public void testUserCreatedSearch()
    {
        List<Recipe> testResults = searchObjects.getSearchedRecipes("mock", true,false,false);
        assertTrue("One result should be found", testResults.size() ==2);
        assertTrue(testResults.get(0).getRecipeName().equals("mock duck"));
        assertTrue(testResults.get(0).getRecipeId() == 2);
        assertTrue(testResults.get(1).getRecipeName().equals("mock duck"));
        assertTrue("Should be 4 is: " + testResults.get(1).getRecipeId(),testResults.get(1).getRecipeId() == 4);
    }

    @Test
    public void testFavSearch()
    {
        List<Recipe> testResults = searchObjects.getSearchedRecipes("duck", false,true,false);
        assertTrue("One result should be found", testResults.size() ==2);
        assertTrue(testResults.get(0).getRecipeName().equals("mock duck"));
        assertTrue("Should be 3 is: " + testResults.get(0).getRecipeId(),testResults.get(0).getRecipeId() == 3);
        assertTrue(testResults.get(1).getRecipeName().equals("mock duck"));
        assertTrue("Should be 4 is: " + testResults.get(1).getRecipeId(),testResults.get(1).getRecipeId() == 4);

    }

    @Test
    public void testBoth()
    {
        List<Recipe> testResults = searchObjects.getSearchedRecipes("duck", true,true,false);
        assertTrue("no result should be found", testResults.size() ==1);
        assertTrue(testResults.get(0).getRecipeName().equals("mock duck"));
        assertTrue(testResults.get(0).getRecipeId() == 4);
    }
}

