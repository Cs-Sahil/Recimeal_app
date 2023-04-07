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
    private final List<Recipe> recipes;

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
        return Collections.unmodifiableList(recipes);
    }
    public Recipe getRecipeById(int recipeId)
    {
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


    //returns a subset of recipes from the provided list that contain the search term
    //for now it only looks for the term in the recipe name. Case insensitive.
    //Does not modify fullList, but returns it if no search term is provided.
    //booleans further filter the results
    public List<Recipe> getSearchedRecipes(List<Recipe> fullList, String searchTerm, boolean userOnly, boolean favOnly, boolean checkIng)
    {
        ArrayList<Recipe> searchList = new ArrayList<Recipe>();

        //craft the list with only entries with the search term
        if(searchTerm!=null && !searchTerm.equals(""))
        {
            searchList = searchStrings(fullList, checkIng, searchTerm);

            if(userOnly)
                searchList = searchUserOnly(searchList);
            if(favOnly)
                searchList = searchUserOnly(searchList);
        }
        else //no search term, work with entire list
        {
            if(userOnly)
            {
                searchList = searchUserOnly(fullList);
                if(favOnly)
                    searchList = searchFavOnly(searchList);
            }
            else if (favOnly)
                searchList = searchFavOnly(fullList);
            else
                return fullList;
        }
        return searchList;
    }

    private ArrayList<Recipe> searchUserOnly(List<Recipe> fullList)
    {
        final ArrayList<Recipe> searchList = new ArrayList<Recipe>();
        for (int i = 0; i < fullList.size(); i++)
        {
            if (fullList.get(i).getUserCreated())
                searchList.add(fullList.get(i));
        }
        return searchList;
    }

    private ArrayList<Recipe> searchFavOnly(List<Recipe> fullList)
    {
        final ArrayList<Recipe> searchList = new ArrayList<Recipe>();
        for (int i = 0; i < fullList.size(); i++)
        {
            if (fullList.get(i).getFavorited())
                searchList.add(fullList.get(i));
        }
        return searchList;
    }

    private ArrayList<Recipe> searchStrings(List<Recipe> fullList, boolean checkIng, String searchTerm)
    {
        ArrayList<Recipe> searchList = new ArrayList<Recipe>();
        AccessIngredients accessIngredients = new AccessIngredients();
        for (int i = 0; i < fullList.size(); i++)
        {
            if (fullList.get(i).getRecipeName().toLowerCase(Locale.ROOT).contains(searchTerm))
                searchList.add(fullList.get(i));
            else if(checkIng)
            {//search for the term in ingredient names
                int id = fullList.get(i).getRecipeId();
                List<Ingredient> ingredients = accessIngredients.getRecipeIngredients(id);
                for(int j=0;j<ingredients.size();j++)
                {
                    if(ingredients.get(j).getName().toLowerCase(Locale.ROOT).contains(searchTerm))
                    {
                        searchList.add(fullList.get(i));
                        break;
                    }
                }
            }
        }
        return searchList;
    }

    public void deleteRecipe(Recipe recipe)
    {
        recipePersistence.deleteRecipe(recipe);
    }
}
