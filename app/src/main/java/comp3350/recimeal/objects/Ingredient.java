package comp3350.recimeal.objects;

import java.util.Objects;

public class Ingredient {

    private final String name;
    private final String unit;

    //constructor for ingredients with unknown measurement
    public Ingredient(final String name){
        this.name = name;
        this.unit = "Unknown";
    }

    public Ingredient(final String name, final String unit){
        this.name = name;
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
            return this.name.equals(otherIngredient.name);
        }

        return false;
    }

}
