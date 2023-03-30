package comp3350.recimeal.objects;

public class PermanentRecipe extends Recipe{

    private int id;

    public PermanentRecipe(int id, String name, String description, String instruction, String style, String type, boolean userCreated, boolean favorited, String notes) {
        super(name, description, instruction, style, type, userCreated, favorited, notes);
        this.id = id;
    }

    public PermanentRecipe(int id, final String name, final String description, final String instruction ){
        super(name, description, instruction);
        this.id = id;
    }

    public int getRecipeId(){return this.id;}
}
