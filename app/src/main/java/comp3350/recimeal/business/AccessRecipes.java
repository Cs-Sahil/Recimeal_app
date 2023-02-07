package comp3350.recimeal.business;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


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

    //returns a subset of recipes from the provided list that contain the search term
    //for now it only looks for the term in the recipe name. Case insensitive.
    //Does not modify fullList, but returns it if no search term is provided.
    public List<Recipe> getSearchedRecipes(List<Recipe> fullList, String searchTerm)
    {
        final List<Recipe> searchList = new ArrayList<Recipe>();

        //craft the list with only entries with the search term
        if(searchTerm!=null && !searchTerm.equals(""))
        {
            for (int i = 0; i < fullList.size(); i++)
            {
                if (fullList.get(i).getRecipeName().toLowerCase(Locale.ROOT).contains(searchTerm))
                    searchList.add(fullList.get(i));
            }
            return searchList;
        }
        else //if search term was empty, give back the full list
            return fullList;
    }
}
