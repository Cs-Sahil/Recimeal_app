package comp3350.recimeal.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.TreeMap;

public class Recipe implements Parcelable {

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
    protected Recipe(Parcel in){
        name = in.readString();
        instruction = in.readString();
        description = in.readString();
        int mapSize = in.readInt();
        ingredients =  new TreeMap<String, Integer>();
        for(int i =0; i< mapSize; i++)
        {
            this.addIngred( in.readString(), in.readInt());
        }
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Map<String, Integer> getIngredients(){return this.ingredients;}
    public String[] getIngredientList(){ return this.ingredients.keySet().toArray(new String[0]);}
    public int getIngredientAmount(String ingredient){return this.ingredients.get(ingredient);}

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(instruction);
        parcel.writeString(description);
        parcel.writeInt(ingredients.size());
        for(Map.Entry<String,Integer> mover : ingredients.entrySet()){
            parcel.writeString(mover.getKey());
            parcel.writeInt(mover.getValue());
        }
    }
}

