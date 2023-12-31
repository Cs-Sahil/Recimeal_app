package comp3350.recimeal.persistence;

import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.objects.Ingredient;
import java.util.List;

public interface RecipePersistence {

    List<Recipe> getRecipeSequential();

    Recipe getRecipeById(int id);

    int insertRecipe(Recipe newRecipe);

    Recipe updateRecipe(Recipe currRecipe);

    void deleteRecipe(Recipe discardRecipe);

    List<Integer> groceryRecipes();

    void addToGrocery(int RecipeID);

}
