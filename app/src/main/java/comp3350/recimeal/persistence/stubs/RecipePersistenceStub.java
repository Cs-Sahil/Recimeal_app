package comp3350.recimeal.persistence.stubs;

import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class RecipePersistenceStub implements RecipePersistence{
    private List<Recipe> recipes;
 
    private String friedRiceInstructions = "Add the cooked rice to the pan and stir until well combined with the vegetables and eggs.\n"
            + "Cook for an additional 2-3 minutes, or until the rice is heated through.\n";
    private String omeleteIntructions = "Whisk 2 eggs, add fillings, cook both sides in pan. Serve hot.";
    private String brownieInstructions = "Mix melted chocolate and butter. Add sugar, eggs, and flour. Bake and serve.";
    private String muffinInstructions =
                    "- Heat oven to 400 degrees fahrenheit.\n"+
                    "- Whisk flour, sugar, baking powder, and salt in a large bowl.\n"+
                    "- Add oil to a measuring jug that holds at least 1 cup.\n"+
                    "- Add the egg then fill the jug to the 1-cup line with milk (1/3 to 1/2 cup milk).\n"+
                    "- Add vanilla and whisk to combine.\n"+
                    "- Add milk mixture to the bowl with dry ingredients then use a fork to combine.\n"+
                    "- Fold in the blueberries.\n"+
                    "- Divide batter between muffin cups.\n"+
                    "- Bake 15-20 minutes";

    public RecipePersistenceStub() {
        this.recipes = new ArrayList<>();
        
        recipes.add(new Recipe("Fried Rice",friedRiceInstructions));
        recipes.add(new Recipe("Omelette",omeleteIntructions));
        recipes.add(new Recipe("Brownies", brownieInstructions));
        recipes.add(new Recipe("Blueberry Muffins",muffinInstructions,"Simple, fluffy and tasty!"));
        
    }

    @Override
    //getRecipeSequential() return the all the recipes store in the list
    public List<Recipe> getRecipeSequential() {
        return Collections.unmodifiableList(recipes);
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
