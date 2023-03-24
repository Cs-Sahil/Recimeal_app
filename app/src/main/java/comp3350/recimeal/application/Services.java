package comp3350.recimeal.application;

import comp3350.recimeal.persistence.IngredientPersistence;
import comp3350.recimeal.persistence.RecipePersistence;
import comp3350.recimeal.persistence.hsqldb.IngredientDBPersistence;
import comp3350.recimeal.persistence.stubs.IngredientPersistenceStub;
import comp3350.recimeal.persistence.stubs.RecipePersistenceStub;
import comp3350.recimeal.persistence.hsqldb.RecipeDBPersistence;
public class Services
{
    private static RecipePersistence recipePersistence = new RecipeDBPersistence(Main.getDBPathName());
    private static IngredientPersistence ingredientPersistence = new IngredientDBPersistence(Main.getDBPathName());

    public static synchronized RecipePersistence getRecipePersistence()
    {
        return recipePersistence;
    }

    public static synchronized IngredientPersistence getIngredientPersistence()
    {
        return ingredientPersistence;
    }
}
