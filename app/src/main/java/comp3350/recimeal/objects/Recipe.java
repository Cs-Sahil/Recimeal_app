package comp3350.recimeal.objects;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Recipe {

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


    //a complete constructor
    public Recipe(int id, final String name, final String description , final String instruction, String style, String type, boolean userCreated, boolean favorited, String notes  ){
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.description = description;
        this.userCreated = userCreated;
        this.favorited = favorited;
        this.style = style;
        this.type = type;
        this.notes = notes;
    }
    public Recipe( int id, final String name, final String description, final String instruction )
    {
        this.id = id;
        this.name = name;
        this.instruction = instruction;
        this.description = description;
        this.userCreated = false;
        this.favorited = false;
        this.style = null;
        this.type = null;
        this.notes = null;
    }
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

    @Override
    public String toString() {
        return this.name;
    }


}

