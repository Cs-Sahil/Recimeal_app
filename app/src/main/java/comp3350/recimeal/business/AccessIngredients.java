package comp3350.recimeal.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import comp3350.recimeal.application.Services;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;

public class AccessIngredients {
    //all the ingredients in the app
    private IngredientPersistence ingredientPersistence;
    private List<Ingredient> ingredients;

    public AccessIngredients()
    {
        ingredientPersistence = Services.getIngredientPersistence();
        ingredients = ingredientPersistence.getIngredientSequential();
    }

    public AccessIngredients(final IngredientPersistence ingredientPersistence){
        this.ingredientPersistence = ingredientPersistence;
        ingredients = this.ingredientPersistence.getIngredientSequential();
    }
    public List<Ingredient> getRecipeIngredients( int recipeId )
    {
        return ingredientPersistence.getRecipesIngredients(recipeId);
    }
    public List<Ingredient> getIngredients()
    {
        ingredients = this.ingredientPersistence.getIngredientSequential();
        return Collections.unmodifiableList(ingredients);
    }

    //returns a subset of ingredients from the provided list that contain the search term
    //for now it only looks for the term in the ingredient name. Case insensitive.
    //Does not modify fullList, but returns it if no search term is provided.

    public void addIngredient(Ingredient ingredient, int newID) {
        ingredientPersistence.insertIngredient(ingredient,newID);
    }
}

