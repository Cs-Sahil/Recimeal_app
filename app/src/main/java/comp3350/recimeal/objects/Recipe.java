package comp3350.recimeal.objects;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;
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
    private String notes;
    private boolean userCreated;
    private boolean favorited;
    private String style;
    private String type;

    private Map<Integer, Ingredient> ingredients;

    //a complete constructor
    public Recipe(int id, final String name, final String instruction, final String description, String style, String type, boolean userCreated, boolean favorited, String notes  ){
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.description = description;
        this.ingredients = new TreeMap<>();
        this.description = description;
        this.userCreated = userCreated;
        this.favorited = favorited;
        this.style = style;
        this.type = type;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            userCreated = in.readBoolean();
            favorited = in.readBoolean();
        }
        style = in.readString();
        type = in.readString();
        int mapSize = in.readInt();
        ingredients =  new TreeMap<Integer, Ingredient>();
        for(int i =0; i< mapSize; i++)
        {
            int ingredID = in.readInt();
            String ingredName = in.readString();
            float ingredAmount = in.readFloat();
            String ingredUnit = in.readString();
            this.addIngredient(new Ingredient(ingredID, ingredName,ingredAmount,ingredUnit));
        }
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(instruction);
        parcel.writeString(description);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(userCreated);
            parcel.writeBoolean(favorited);
        }
        parcel.writeString(style);
        parcel.writeString(type);
        parcel.writeInt(ingredients.size());
        for(Map.Entry<Integer, Ingredient> mover : ingredients.entrySet()){
            parcel.writeInt(mover.getKey());
            parcel.writeString(mover.getValue().getName());
            parcel.writeFloat(mover.getValue().getAmount());
            parcel.writeString(mover.getValue().getUnit());
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

    public Map<Integer, Ingredient> getIngredients(){return this.ingredients;}
    public Ingredient[] getIngredientList(){ return this.ingredients.values().toArray(new Ingredient[0]);}
    public float getIngredientAmount(int ingredient){return this.ingredients.get(ingredient).getAmount();}

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
    public Integer[] getIngredientIds() { return this.ingredients.keySet().toArray(new Integer[0]);}

    //add recipe to the recipe
    public boolean addIngredient( Ingredient newIngredient){
        //if the ingredient is already in the map, don't add it again
        if(ingredients.containsKey(newIngredient.getId()))
            return false;
        else{
            ingredients.put(newIngredient.getId(), newIngredient);
            return true;
        }
    }
    public boolean addIngredients(List<Ingredient> toAdd)
    {
        boolean allSuccessful = true;
        for(int i =0; i< toAdd.size();i++)
        {
            if(!this.addIngredient(toAdd.get(i)))
            {
                allSuccessful = false;
            }
        }
        return allSuccessful;
    }
    public Ingredient getIngredientById(int ingredientId)
    {
        return this.ingredients.get(ingredientId);
    }

    public boolean containsIngredient(int ingredientId)
    {
        return ingredients.containsKey(ingredientId);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}

