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
    private String name;
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

    public String getRecipeStyle(){return (this.style);}

    public String getRecipeType(){return (this.type);}
    public String getRecipeNotes(){return (this.notes);}
    public boolean getUserCreated(){return (this.userCreated);}
    public boolean getFavorited(){return (this.favorited);}


    //setters
    public void setName(String newName){this.name = newName;}
    public void setInstruction(String newIns){this.instruction = newIns;}
    public void setDescription(String newDes){this.description = newDes;}
    public void setNotes(String newNotes){this.notes = newNotes;}
    public void setFavorited(){this.favorited = true;}
    public void unsetFavorited(){this.favorited = false;}
    public void setStyle(String newStyle){this.style = newStyle;}
    public void setType(String newType){this.type = newType;}


    @Override
    public String toString() {
        return this.name;
    }


}

