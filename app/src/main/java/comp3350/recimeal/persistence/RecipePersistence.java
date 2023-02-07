package comp3350.recimeal.persistence;

import comp3350.recimeal.objects.Recipe;
import java.util.List;

public interface RecipePersistence {

    List<Recipe> getRecipeSequential();

    Recipe insertRecipe(Recipe newRecipe);

    Recipe updateRecipe(Recipe currRecipe);

    void deleteRecipe(Recipe discardRecipe);
}
