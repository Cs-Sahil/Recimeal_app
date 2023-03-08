package comp3350.recimeal.persistence.hsqldb;

import java.util.List;

import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.persistence.IngredientPersistence;

public class IngredientDBPersistence implements IngredientPersistence {
    @Override
    public List<Ingredient> getIngredientSequential() {
        return null;
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return null;
    }

    @Override
    public Ingredient insertIngredient(Ingredient newIngredient) {
        return null;
    }

    @Override
    public Ingredient updateIngredient(Ingredient currIngredient) {
        return null;
    }

    @Override
    public void deleteIngredient(Ingredient discardIngredient) {

    }
}
