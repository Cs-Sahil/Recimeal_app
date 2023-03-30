package comp3350.recimeal.objects;

public class TempRecipe extends Recipe{

    public TempRecipe(String name, String description, String instruction, String style, String type, boolean userCreated, boolean favorited, String notes) {
        super(name, description, instruction, style, type, userCreated, favorited, notes);
    }

    //temp recipe does not have id
    @Override
    public int getRecipeId() {
        return -1;
    }
}
