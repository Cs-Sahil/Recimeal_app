package comp3350.recimeal.business;


import java.util.List;

import comp3350.recimeal.application.Services;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;
import comp3350.recimeal.persistence.RecipePersistence;

public class CreateRecipes
{
    private final RecipePersistence recipePersistence;
    private final IngredientPersistence ingredientPersistence;

    public CreateRecipes(){
        recipePersistence = Services.getRecipePersistence();
        ingredientPersistence = Services.getIngredientPersistence();
    }

    public int createRecipe(Recipe recipe, List<Ingredient> ingredientList){
        //add the recipe and get the id
        int newID = recipePersistence.insertRecipe(recipe);
        //add the ingredients
        for(int i = 0; i < ingredientList.size(); i++){
            ingredientPersistence.insertIngredient(ingredientList.get(i), newID);
        }
        return newID;
    }

    public Recipe updateRecipe(Recipe currRecipe){
        return recipePersistence.updateRecipe(currRecipe);
    }

}
