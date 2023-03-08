package comp3350.recimeal.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;

public class IngredientPersistenceStub implements IngredientPersistence {

    private List<Ingredient> ingredients;

    public IngredientPersistenceStub() {

        this.ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1, "Fries", 1, "unit"));
        ingredients.add(new Ingredient(2, "Rice", 2, "unit"));
        ingredients.add(new Ingredient(3, "Eggs", 3, "unit"));
        ingredients.add(new Ingredient(4, "Cheese", 4, "unit"));
        ingredients.add(new Ingredient(5, "Pepper", 5, "unit"));
        ingredients.add(new Ingredient(6, "Flour", 6, "unit"));
        ingredients.add(new Ingredient(7, "Muffins", 7, "unit"));
        ingredients.add(new Ingredient(8, "Blueberries", 8, "unit"));
    }

    @Override
    public List<Ingredient> getIngredientSequential() {
        return Collections.unmodifiableList(ingredients);
    }

    @Override
    public Ingredient getIngredientById(int id) {
        for(Ingredient ingredient: ingredients){
            if(ingredient.getId() == id)
                return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient insertIngredient(Ingredient newIngredient) {
        ingredients.add(newIngredient);
        return newIngredient;
    }

    @Override
    public Ingredient updateIngredient(Ingredient currIngredient) {
        return null;
    }

    @Override
    public void deleteIngredient(Ingredient discardIngredient) {

    }


}
