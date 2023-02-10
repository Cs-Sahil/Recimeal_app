package comp3350.recimeal.objects;

import java.util.Map;
import java.util.TreeMap;

public class Recipe {

    private final static String DEFAULT_DESCRIPTION = "No Description Given";
    private final String name;
    private String instruction;
    //a short description of the recipe, shouldn't be more than 50 characters
    private String description;
    //stores the ingredients name and their amounts, implemented with Treemap
    private Map<String, Integer> ingredients;

    //a complete constructor
    public Recipe(final String name, final String instruction, final String description,  Map<String, Integer> ingredients){
        this.name = name;
        this.instruction = instruction;
        this.description = description;
        this.ingredients = new TreeMap<>(ingredients);
        this.description = description;
    }

    //constructor without the ingredients, can add ingredients with addIngred() after Recipe is created
    public Recipe(final String name, final String instruction){
        this.name = name;
        this.instruction = instruction;
        ingredients = new TreeMap<>();
        this.description = DEFAULT_DESCRIPTION;
    }

    public Recipe(final String name, final String instruction,final String description){
        this.name = name;
        this.instruction = instruction;
        ingredients = new TreeMap<>();
        this.description = description;
    }

    public Map<String, Integer> getIngredients(){return this.ingredients;}


    /*methods to add ingredient into the recipe, there are two versions:
    1. The ingredient is already in the app data
        We simply add the name and the amount to the ingredients map
    2. It is a new ingredient
         We create a new Ingredient Object, add it to the app data, then add the name and the amount into the map

    Both return true if successfully added
     Caller is responsible for deciding which one should be used
     */

    //version 1
    public String getRecipeName()
    {
        return (this.name);
    }
    public String getRecipeDescription()
    {
        return (this.description);
    }
    public String getRecipeInstruction() { return (this.instruction);}

    public boolean addIngred(String name, int amount){
        //if the ingredient is already in the map, don't add it again
        if(ingredients.containsKey(name))
            return false;
        else{
            ingredients.put(name, amount);
            return true;
        }
    }

    //version 2 (not implemented yet!)
    public boolean addIngred(String name, int amount, String unit){
        return false;
    }

    @Override
    public String toString() {

        return this.name;
    }
}

