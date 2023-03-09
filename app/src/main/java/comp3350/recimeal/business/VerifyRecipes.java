package comp3350.recimeal.business;


import java.util.List;

import comp3350.recimeal.application.Services;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;
import comp3350.recimeal.persistence.RecipePersistence;

public class VerifyRecipes
{
     //returns with an error message, returns EMPTY STRING ON SUCCESS
     public String validateAll(int ingStart, int ingEnd, String name, String description, String instructions)
     {
         String result = "";
         result += validateName(name);
         result += validateDescription(description);
         result += validateInstruction(instructions);
         result += validateNumIngredients(ingStart,ingEnd);
         return result;
     }

     private String validateNumIngredients(int ingStart, int ingEnd)
     {
         String result = "";
         if(ingEnd<=ingStart)
             result = "Recipe needs at least one ingredient.\n";
         return result;
     }

     private String validateName(String name)
     {
         String result="";
         if(name.length()<1)
             result += "Recipe requires a name.\n";
         else if(name.length()>50)
             result += "Recipe name is too long.\n";
         return result;
     }

     public String validateInstruction(String instructions)
     {
         String result = "";
         if(instructions.length()<1)
             result+="Recipe requires instructions.\n";
         return result;
     }

     //it is okay to not have a description, so length of 0 allowed
     public String validateDescription(String description)
     {
         String result="";
         if(description.length()<0)
             result+="Problem with description.\n";
         return result;
     }
}
