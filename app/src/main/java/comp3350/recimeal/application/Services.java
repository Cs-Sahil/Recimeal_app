package comp3350.recimeal.application;

import comp3350.recimeal.persistence.RecipePersistence;
import comp3350.recimeal.persistence.stubs.RecipePersistenceStub;

public class Services
{
    private static RecipePersistence recipePersistence = null;

    public static synchronized RecipePersistence getRecipePersistence()
    {
        if (recipePersistence == null)
        {
            recipePersistence = new RecipePersistenceStub();
        }

        return recipePersistence;
    }
}
