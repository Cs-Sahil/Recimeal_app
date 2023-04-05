package comp3350.recimeal.business;


import java.util.List;

import comp3350.recimeal.application.Services;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;
import comp3350.recimeal.persistence.RecipePersistence;

public class CreateRecipes
{
    private final AccessRecipes accessRecipes;
    private final AccessIngredients accessIngredients;

    public CreateRecipes(AccessRecipes newRecipes, AccessIngredients newIngredients){
        accessRecipes = newRecipes;
        accessIngredients = newIngredients;
    }

    public int createRecipe(Recipe recipe, List<Ingredient> ingredientList){
        //add the recipe and get the id
        int newID = accessRecipes.addRecipe(recipe);
        //add the ingredients
        if(ingredientList!=null)
        {
            for(int i = 0; i < ingredientList.size(); i++){
                accessIngredients.addIngredient(ingredientList.get(i), newID);
            }

        }
        return newID;
    }

}

