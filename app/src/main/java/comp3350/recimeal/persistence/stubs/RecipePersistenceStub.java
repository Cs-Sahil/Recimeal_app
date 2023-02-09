package comp3350.recimeal.persistence.stubs;

import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

import java.util.List;
import java.util.ArrayList;

public class RecipePersistenceStub implements RecipePersistence{
    private List<Recipe> recipes;
 
    private String friedRiceInstructions = "Add the cooked rice to the pan and stir until well combined with the vegetables      and eggs.\n" + "Cook for an additional 2-3 minutes, or until the rice is heated through.\n";
   
   private String omeleteIntructions = "Whisk 2 eggs, add fillings, cook both sides in pan. Serve hot.";
   private String BrownieInstructions = "Mix melted chocolate and butter. Add sugar, eggs, and flour. Bake and serve.";
    

    public RecipePersistenceStub() {
        this.recipes = new ArrayList<>();
        
        recipes.add(new Recipe("Fried Rice",friedRiceInstructions))
        recipes.add(new Recipe("omelette",omeleteIntructions ))
        recipes.add(new Recipe("Brownies", BrownieInstructions))
        
    }

    @Override
    //getRecipeSequential() return the all the recipes store in the list
    public List<Recipe> getRecipeSequential() {
        return new ArrayList<>(recipes);
    }

    @Override
    // insertRecipe() adds a new recipe in list
    public Recipe insertRecipe(Recipe newRecipe) {
        recipes.add(newRecipe);
        return newRecipe;
    }

    @Override
    // updateRecipe(Recipe currRecipe) -> updates a recipe using an ID of that recipe
    public Recipe updateRecipe(Recipe currRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
                 if (recipe.equals(currRecipe)) {
                recipes.set(i, currRecipe);
                return currRecipe;
            }
        }
        return null;
    }

    @Override
    //deleteRecipe(Recipe discardRecipe) -> delete recipe using ID of that recipe
    public void deleteRecipe(Recipe discardRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (recipe.equals(discardRecipe)) {
                recipes.remove(i);
                break;
            }
        }
    }
}
