package comp3350.recimeal.persistence.stubs;

import java.util.List;

import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

public class RecipePersistenceStub implements RecipePersistence {

    @Override
    public List<Recipe> getRecipeSequential() {
        return null;
    }

    @Override
    public Recipe insertRecipe(Recipe newRecipe) {
        return null;
    }

    @Override
    public Recipe updateRecipe(Recipe currRecipe) {
        return null;
    }

    @Override
    public void deleteRecipe(Recipe discardRecipe) {

    }
}
