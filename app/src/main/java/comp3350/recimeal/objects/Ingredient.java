package comp3350.recimeal.objects;

import java.util.Objects;

public class Ingredient{

    private final int id;
    private final String name;
    private final float amount;
    private final String unit;

    //constructor for ingredients
    public Ingredient(int id,final String name, float amount, String unit){
        this.name = name;
        this.id = id;
        this.amount = amount;
        this.unit = unit;
    }

    //could be used when displaying recipe information, concatenate with a string of amount
    public String toString(){
        return String.format("%s", this.name);
    }


    public boolean equals(Object other){
        //only consider equal when both objects are Ingredients
        if(other instanceof Ingredient){
            final Ingredient otherIngredient = (Ingredient) other;
            return (this.id == otherIngredient.id) || (this.name).equals(((Ingredient) other).name);
        }

        return false;
    }

    public int getId(){return this.id;}

    public String getName() {
        return this.name;
    }

    public float getAmount(){ return this.amount;}

    public String getUnit(){return this.unit;}

}
