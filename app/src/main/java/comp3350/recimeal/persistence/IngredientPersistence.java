package comp3350.recimeal.persistence;

import java.util.List;

import comp3350.recimeal.objects.Ingredient;

public interface IngredientPersistence {

    List<Ingredient> getIngredientSequential();

    Ingredient getIngredientById(int id);

    Ingredient insertIngredient(Ingredient newIngredient);

    Ingredient updateIngredient(Ingredient currIngredient);

    void deleteIngredient(Ingredient discardIngredient);
}
