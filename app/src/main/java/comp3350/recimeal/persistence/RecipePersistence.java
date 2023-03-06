package comp3350.recimeal.persistence;

import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.objects.Ingredient;
import java.util.List;

public interface RecipePersistence {

    List<Recipe> getRecipeSequential();

    Recipe getRecipeById(int id);

    Recipe insertRecipe(Recipe newRecipe);

    Ingredient insertIngredient( Ingredient newIngredient);

    Recipe updateRecipe(Recipe currRecipe);

    void deleteRecipe(Recipe discardRecipe);

    void deleteIngredient( Ingredient discardIngredient);
}
