package comp3350.recimeal.business;


import java.util.List;

import comp3350.recimeal.application.Services;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

public class CreateRecipes {
    private final RecipePersistence recipePersistence;

    public CreateRecipes(){
        recipePersistence = Services.getRecipePersistence();
    }
    public void createRecipe(final String name, final String instruction, final String description, String style, String type, List<Ingredient> ingredientList){
        //get the correct constructor
        int newId = recipePersistence.getNewestId();
        Recipe newRecipe = new Recipe(newId, name, instruction, description, style, type, true, false, "");
        //now insert the ingredients
        for(int i = 0; i < ingredientList.size(); i++){
            newRecipe.addIngred(ingredientList.get(i));
        }

        recipePersistence.insertRecipe(newRecipe);
    }

}
