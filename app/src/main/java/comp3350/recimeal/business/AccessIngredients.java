package comp3350.recimeal.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import comp3350.recimeal.application.Services;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;

public class AccessIngredients {
    //all the ingredients in the app
    private IngredientPersistence ingredientPersistence;
    private List<Ingredient> ingredients;
    private Ingredient ingredient;
    private int currIngredient;

    public AccessIngredients()
    {
        ingredientPersistence = Services.getIngredientPersistence();
        ingredients = ingredientPersistence.getIngredientSequential();
        ingredient = null;
        currIngredient = 0;
    }

    public List<Ingredient> getRecipes()
    {
        return Collections.unmodifiableList(ingredients);
    }

    //returns a subset of ingredients from the provided list that contain the search term
    //for now it only looks for the term in the ingredient name. Case insensitive.
    //Does not modify fullList, but returns it if no search term is provided.
    public List<Ingredient> getSearchedRecipes(List<Ingredient> fullList, String searchTerm)
    {
        final List<Ingredient> searchList = new ArrayList<>();

        //craft the list with only entries with the search term
        if(searchTerm!=null && !searchTerm.equals(""))
        {
            for (int i = 0; i < fullList.size(); i++)
            {
                if (fullList.get(i).getName().toLowerCase(Locale.ROOT).contains(searchTerm))
                    searchList.add(fullList.get(i));
            }
            return searchList;
        }
        else //if search term was empty, give back the full list
            return fullList;
    }
}
