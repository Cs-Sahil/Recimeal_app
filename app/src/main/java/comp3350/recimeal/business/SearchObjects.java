package comp3350.recimeal.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;

public class SearchObjects {

    private List<Recipe> fullList;
    private AccessIngredients accessIngredients;

    public SearchObjects(){
        fullList = null;
    }

    public SearchObjects(AccessRecipes accessRecipes)
    {
        fullList = accessRecipes.getRecipes();
        accessIngredients = new AccessIngredients();
    }
    public SearchObjects(AccessRecipes accessRecipes, AccessIngredients newAccessIngredients)
    {
        fullList = accessRecipes.getRecipes();
        this.accessIngredients = newAccessIngredients;
    }

    //returns a subset of recipes from the provided list that contain the search term
    //for now it only looks for the term in the recipe name. Case insensitive.
    //Does not modify fullList, but returns it if no search term is provided.
    //booleans further filter the results
    public List<Recipe> getSearchedRecipes( String searchTerm, boolean userOnly, boolean favOnly, boolean checkIng)
    {
        ArrayList<Recipe> searchList = new ArrayList<Recipe>();

        //craft the list with only entries with the search term
        if(searchTerm!=null && !searchTerm.equals(""))
        {
            searchList = this.searchStrings(checkIng, searchTerm);

            if(userOnly)
            {
                searchList = searchUserOnly(searchList);
            }
            if(favOnly)
                searchList = searchFavOnly(searchList);
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

    private ArrayList<Recipe> searchUserOnly(List<Recipe> toSearch)
    {
        final ArrayList<Recipe> searchList = new ArrayList<Recipe>();
        for (int i = 0; i < toSearch.size(); i++)
        {
            if (toSearch.get(i).getUserCreated())
                searchList.add(toSearch.get(i));
        }
        return searchList;
    }

    private ArrayList<Recipe> searchFavOnly(List<Recipe> toSearch)
    {
        final ArrayList<Recipe> searchList = new ArrayList<Recipe>();
        for (int i = 0; i < toSearch.size(); i++)
        {
            if (toSearch.get(i).getFavorited())
                searchList.add(toSearch.get(i));
        }
        return searchList;
    }

    private ArrayList<Recipe> searchStrings( boolean checkIng, String searchTerm)
    {
        ArrayList<Recipe> searchList = new ArrayList<Recipe>();

        if(fullList != null)
        {
            for (int i = 0; i < fullList.size(); i++)
            {
                if (fullList.get(i).getRecipeName().toLowerCase(Locale.ROOT).contains(searchTerm))
                    searchList.add(fullList.get(i));
                else if(checkIng)
                {//search for the term in ingredient names
                    int id = fullList.get(i).getRecipeId();
                    List<Ingredient> ingredients = accessIngredients.getRecipeIngredients(id);
                    if(ingredients != null)
                    {
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
            }
        }

        return searchList;
    }

    /*
    public List<Ingredient> getSearchedIngredients(List<Ingredient> fullList, String searchTerm)
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

     */
}
