package comp3350.recimeal.business;

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

     public String validateNumIngredients(int ingStart, int ingEnd)
     {
         String result = "";
         if(ingEnd<0 || ingStart<0)
             result += "Error while indexing ingredients.\n";
         else if(ingEnd<=ingStart)
             result += "Recipe needs at least one ingredient.\n";
         return result;
     }

     public String validateName(String name)
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
