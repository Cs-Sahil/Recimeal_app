package comp3350.recimeal.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.objects.Ingredient;

public interface IngredientPersistence {

    List<Ingredient> getIngredientSequential();

    // returns ingredient without respect to a contains relationship with a recipe therefore making amount and unit default values
    Ingredient getIngredientById(int id);

    int insertIngredient(Ingredient newIngredient, int recipeID);

    Ingredient updateIngredient(Ingredient currIngredient);

    void deleteIngredient(Ingredient discardIngredient);

     List<Ingredient> getRecipesIngredients(int recipeId);
}
