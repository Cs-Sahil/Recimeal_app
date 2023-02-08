package comp3350.recimeal.persistence.stubs;

import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

import java.util.List;
import java.util.ArrayList;

public class RecipePersistenceStub implements RecipePersistence{
    private List<Recipe> recipes;

    public RecipePersistenceStub() {
        this.recipes = new ArrayList<>();
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
            if (recipe.getId().equals(currRecipe.getId())) {
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
            if (recipe.getId().equals(discardRecipe.getId())) {
                recipes.remove(i);
                break;
            }
        }
    }
}
