package comp3350.recimeal.tests.business;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.business.AccessIngredients;

import comp3350.recimeal.objects.Ingredient;

import comp3350.recimeal.persistence.IngredientPersistence;


public class AccessIngredientsTest {
    private AccessIngredients accessIngredients;
    private IngredientPersistence ingredientPersistence;
    private List<Ingredient> testIng;
    private List<Ingredient> testIngRec;

    @Before
    public void setUp()
    {
        testIng = new ArrayList<>();
        testIngRec = new ArrayList<>();
        testIng.add( new Ingredient(1,"cheese", 5, " scoops"));
        testIngRec.add( new Ingredient(2,"bun", 12, " units"));

        this.ingredientPersistence = mock(IngredientPersistence.class);
        when(ingredientPersistence.getIngredientSequential()).thenReturn(testIng);
        when(ingredientPersistence.getRecipesIngredients(2)).thenReturn(testIngRec);

        this.accessIngredients = new AccessIngredients(this.ingredientPersistence);
    }
    @Test
    public void testSequential()
    {
        List<Ingredient> testList = accessIngredients.getIngredients();
        assertNotNull(testList);
        assertTrue(testList.size() ==1);
        assertTrue(testList.get(0).getName().equals("cheese"));

    }
    @Test
    public void testRecipe()
    {
        List<Ingredient> testList = accessIngredients.getRecipeIngredients(2);
        assertNotNull(testList);
        assertTrue(testList.size() ==1);
        assertTrue(testList.get(0).getName().equals("bun"));
    }
}
