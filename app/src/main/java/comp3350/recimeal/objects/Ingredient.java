package comp3350.recimeal.objects;

import java.util.Objects;

public class Ingredient{

    private final int id;
    private final String name;
    private final String unit;

    //constructor for ingredients with unknown measurement
    public Ingredient(final String name, int id){
        this.name = name;
        this.id = id;
        this.unit = "Unknown";
    }

    public Ingredient(final String name, int id, final String unit){
        this.name = name;
        this.id = id;
        this.unit = unit;
    }


    //could be used when displaying recipe information, concatenate with a string of amount
    public String toString(){
        return String.format("%s  Measurement Unit:%s ", this.name, this.unit);
    }


    public boolean equals(Object other){
        //only consider equal when both objects are Ingredients
        if(other instanceof Ingredient){
            final Ingredient otherIngredient = (Ingredient) other;
            return (this.id == otherIngredient.id);
        }

        return false;
    }

    public int getId(){return this.id;}

    public String getName() {
        return this.name;
    }

    public String getUnit(){return this.unit;}
}
