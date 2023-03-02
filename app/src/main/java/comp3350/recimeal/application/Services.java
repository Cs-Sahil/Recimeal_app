package comp3350.recimeal.application;

import comp3350.recimeal.persistence.IngredientPersistence;
import comp3350.recimeal.persistence.RecipePersistence;
import comp3350.recimeal.persistence.stubs.IngredientPersistenceStub;
import comp3350.recimeal.persistence.stubs.RecipePersistenceStub;

public class Services
{
    private static RecipePersistence recipePersistence = null;
    private static IngredientPersistence ingredientPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence()
    {
        if (recipePersistence == null)
        {
            recipePersistence = new RecipePersistenceStub();
        }


        return recipePersistence;
    }

    public static synchronized IngredientPersistence getIngredientPersistence()
    {
        if(ingredientPersistence == null)
        {
            ingredientPersistence = new IngredientPersistenceStub();
        }

        return ingredientPersistence;
    }
}
