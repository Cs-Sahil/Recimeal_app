package comp3350.recimeal.application;

import comp3350.recimeal.persistence.IngredientPersistence;
import comp3350.recimeal.persistence.RecipePersistence;
import comp3350.recimeal.persistence.hsqldb.IngredientDBPersistence;
import comp3350.recimeal.persistence.stubs.IngredientPersistenceStub;
import comp3350.recimeal.persistence.stubs.RecipePersistenceStub;
import comp3350.recimeal.persistence.hsqldb.RecipeDBPersistence;
public class Services
{
    private static RecipePersistence recipePersistence = null;
    private static IngredientPersistence ingredientPersistence = null;

    public static synchronized RecipePersistence getRecipePersistence()
    {
        if (recipePersistence == null)
        {
            recipePersistence = new RecipeDBPersistence(Main.getDBPathName());
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
