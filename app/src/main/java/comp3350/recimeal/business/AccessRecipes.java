package comp3350.recimeal.business;

import android.os.Bundle;
import android.speech.RecognizerIntent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;
import comp3350.recimeal.application.Services;

public class AccessRecipes
{

    private final RecipePersistence recipePersistence;
    private List<Recipe> recipes;

    public AccessRecipes()
    {
        recipePersistence = Services.getRecipePersistence();
        recipes = recipePersistence.getRecipeSequential();
    }

    public AccessRecipes(final RecipePersistence recipePersistence){
        this.recipePersistence = recipePersistence;
        recipes = this.recipePersistence.getRecipeSequential();
    }

    public List<Recipe> getRecipes()
    {
        recipes = this.recipePersistence.getRecipeSequential();
        return Collections.unmodifiableList(recipes);
    }
    public Recipe getRecipeById(int recipeId)
    {
        recipes = this.recipePersistence.getRecipeSequential();
        Recipe toSender = null;
        boolean recipeFound = false;
        for(int i =0; i< recipes.size() && !(recipeFound);i++) {

            if( recipes.get(i).getRecipeId() == recipeId)
            {
                recipeFound = true;
                toSender = recipes.get(i);
            }
        }
        return toSender;
    }
    public List<Integer> getGroceryRecipes()
    {
        return recipePersistence.groceryRecipes();
    }

    public void deleteRecipe(Recipe discardRecipe){
        recipePersistence.deleteRecipe(discardRecipe);
    }

    public int addRecipe(Recipe addRecipe)
    {
        return recipePersistence.insertRecipe(addRecipe);
    }
    public Recipe updateRecipe(Recipe currRecipe){
        return recipePersistence.updateRecipe(currRecipe);
    }

}
