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
        //this.ingredients = new ArrayList<>();
        /*
        ingredients.add(new Ingredient("Fries", 1));
        ingredients.add(new Ingredient("Rice", 2));
        ingredients.add(new Ingredient("Eggs", 3));
        ingredients.add(new Ingredient("Cheese", 4));
        ingredients.add(new Ingredient("Pepper", 5));
        ingredients.add(new Ingredient("Flour", 6));
        ingredients.add(new Ingredient("Muffins", 7));
        ingredients.add(new Ingredient("Blueberries", 8));

         */
    }

    @Override
    public List<Ingredient> getIngredientSequential() {
        return Collections.unmodifiableList(ingredients);
    }

    @Override
    // returns ingredient without respect to a contains relationship with a recipe therefore making amount and unit default values
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

    public ArrayList<Ingredient> getRecipesIngredients( int recipeId)
    {
        ArrayList ingredients = new ArrayList<Ingredient>();
        return ingredients;
    };

    @Override
    public void deleteIngredient(Ingredient discardIngredient) {

    }
}
