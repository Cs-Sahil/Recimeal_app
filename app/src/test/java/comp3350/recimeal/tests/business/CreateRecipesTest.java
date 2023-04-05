package comp3350.recimeal.tests.business;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.business.AccessIngredients;
import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.business.CreateRecipes;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.PermanentRecipe;
import comp3350.recimeal.objects.Recipe;

public class CreateRecipesTest {
    private CreateRecipes createRecipes;
    private AccessRecipes accessRecipes;
    private AccessIngredients accessIngredients;

    @Before
    public void setUp()
    {
        this.accessRecipes = mock(AccessRecipes.class);
        when(accessRecipes.addRecipe(any(Recipe.class))).thenReturn(1);
        this.accessIngredients = mock(AccessIngredients.class);
        this.createRecipes = new CreateRecipes(this.accessRecipes,this.accessIngredients);

    }

    @Test
    public void testCreate()
    {
        List<Ingredient> testIng= new ArrayList<Ingredient>();
        testIng.add(new Ingredient(1,"cheese", 3, "lots"));
        Recipe newRecipe = new PermanentRecipe(1,"","","");

        int testInt = createRecipes.createRecipe(newRecipe, testIng);
        assertTrue( testInt == 1);

    }
}
