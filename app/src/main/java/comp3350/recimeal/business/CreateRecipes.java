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

    public int createRecipe(final String name, final String instruction, final String description, String style, String type, List<Ingredient> ingredientList){
        //construct a temp Recipe Object, its id will be corrected by the database layer
        Recipe newRecipe = new Recipe(-1, name, instruction, description, style, type, true, false, "");
        //insert this recipe
        int newID = recipePersistence.insertRecipe(newRecipe);

        //insert the ingredients
        for(int i = 0; i < ingredientList.size(); i++){
            ingredientPersistence.insertIngredient(ingredientList.get(i), newID);
        }

        return newID;
    }

}
