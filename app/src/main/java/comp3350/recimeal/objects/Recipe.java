package comp3350.recimeal.objects;

import java.util.Map;
import java.util.TreeMap;

public class Recipe {

    private final String name;
    private final String instruction;
    //stores the ingredients name and their amounts, implemented with Treemap
    private Map<String, Integer> ingredients;

    //a complete constructor
    public Recipe(final String name, final String instruction, final Map<String, Integer> ingredients){
        this.name = name;
        this.instruction = instruction;
        this.ingredients = new TreeMap<>(ingredients);
    }

    //constructor without the ingredients, can add ingredients with addIngred() after Recipe is created
    public Recipe(final String name, final String instruction){
        this.name = name;
        this.instruction = instruction;
        ingredients = new TreeMap<>();
    }

    /*methods to add ingredient into the recipe, there are two versions:
    1. The ingredient is already in the app data
        We simply add the name and the amount to the ingredients map
    2. It is a new ingredient
         We create a new Ingredient Object, add it to the app data, then add the name and the amount into the map

    Both return true if successfully added
     Caller is responsible for deciding which one should be used
     */

    //version 1
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
}
