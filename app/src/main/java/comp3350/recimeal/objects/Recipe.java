package comp3350.recimeal.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.TreeMap;

public class Recipe implements Parcelable {

    private final static String DEFAULT_DESCRIPTION = "No Description Given";
    private final int id;
    private final String name;
    private String instruction;
    //a short description of the recipe, shouldn't be more than 50 characters
    private String description;
    //stores the ingredients id and their amounts, implemented with Treemap
    private Map<Integer, Integer> ingredients;

    //a complete constructor
    public Recipe(int id, final String name, final String instruction, final String description,  Map<Integer, Integer> ingredients){
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.description = description;
        this.ingredients = new TreeMap<>(ingredients);
        this.description = description;
    }

    //constructor without the ingredients, can add ingredients with addIngred() after Recipe is created
    public Recipe(int id, final String name, final String instruction){
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        ingredients = new TreeMap<>();
        this.description = DEFAULT_DESCRIPTION;
    }

    public Recipe(int id, final String name, final String instruction,final String description){
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        ingredients = new TreeMap<>();
        this.description = description;
    }
    protected Recipe(Parcel in){
        id = in.readInt();
        name = in.readString();
        instruction = in.readString();
        description = in.readString();
        int mapSize = in.readInt();
        ingredients =  new TreeMap<Integer, Integer>();
        for(int i =0; i< mapSize; i++)
        {
            this.addIngred(Integer.valueOf(in.readString()), in.readInt());
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

    public Map<Integer, Integer> getIngredients(){return this.ingredients;}
    public Integer[] getIngredientList(){ return this.ingredients.keySet().toArray(new Integer[0]);}
    public int getIngredientAmount(int ingredient){return this.ingredients.get(ingredient);}

    public int getRecipeId(){return this.id;}
    public String getRecipeName()
    {
        return (this.name);
    }
    public String getRecipeDescription()
    {
        return (this.description);
    }
    public String getRecipeInstruction() { return (this.instruction);}

    //add recipe to the recipe
    public boolean addIngred(Integer id, int amount){
        //if the ingredient is already in the map, don't add it again
        if(ingredients.containsKey(id))
            return false;
        else{
            ingredients.put(id, amount);
            return true;
        }
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
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(instruction);
        parcel.writeString(description);
        parcel.writeInt(ingredients.size());
        for(Map.Entry<Integer, Integer> mover : ingredients.entrySet()){
            parcel.writeString(String.valueOf(mover.getKey()));
            parcel.writeInt(mover.getValue());
        }
    }
}

