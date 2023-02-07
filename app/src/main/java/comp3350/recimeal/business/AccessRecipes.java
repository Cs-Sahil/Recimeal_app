package comp3350.recimeal.business;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.RecipePersistence;

public class AccessRecipes {

    private RecipePersistence recipePersistence;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    public AccessRecipes()
    {
        //recipePersistence = Services.getRecipePersistence();
        recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Hamburger","make Hamburger","not bad"));
        recipes.add(new Recipe("Hot dog","make Hotdog", "it's ok"));
        recipes.add(new Recipe("Taco","make Taco", "Yum"));
        recipes.add(new Recipe("Cheese Burger","make Hamburger, add Cheese"));
        recipe = null;
        currentRecipe = 0;
    }

    public List<Recipe> getRecipes()
    {
        //recipes = recipePersistence.getCourseSequential();



        return Collections.unmodifiableList(recipes);
    }

}
