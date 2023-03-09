package comp3350.recimeal.persistence.hsqldb;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import comp3350.recimeal.application.Services;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;
import comp3350.recimeal.persistence.RecipePersistence;

public class RecipeDBPersistence extends DBPersistence implements RecipePersistence {

    public RecipeDBPersistence(final String newDbPath) {
        super(newDbPath);

    }

    private Recipe fromResultSet(final ResultSet rset) throws SQLException
    {
        final int recipeID = rset.getInt("RecipeID");
        final String recipeName = rset.getString("Title");
        final String recipeDescription = rset.getString("Description");
        final String recipeInstruction = rset.getString("Instructions");
        final String recipeStyle = rset.getString("Style");
        final String recipeType = rset.getString("Type");
        final boolean userCreated = rset.getBoolean("UserCreated");
        final boolean favorited = rset.getBoolean("Favorited");
        final String notes = rset.getString("Notes");


        return new Recipe(recipeID,recipeName, recipeDescription,recipeInstruction,recipeStyle,recipeType,userCreated,favorited,notes);
    }

    @Override
    public List<Recipe> getRecipeSequential()
    {
        final List<Recipe> recipes = new ArrayList<>();
        try(final Connection dbConnect = connectDB();)
        {
            final Statement state = dbConnect.createStatement();
            final ResultSet rset = state.executeQuery("SELECT * FROM Recipes");
            while (rset.next()) {
                final Recipe recipeToAdd = fromResultSet(rset);
                recipes.add(recipeToAdd);
            }
            rset.close();
            state.close();

            return recipes;

        }
        catch (final SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }




    @Override
    public Recipe getRecipeById(int id) {
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            String query = String.format("SELECT * FROM Recipes WHERE id = %d", id);
            final ResultSet rset = state.executeQuery(query);
            return fromResultSet(rset);
        }catch (final SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int insertRecipe(Recipe newRecipe) {
        //get the fields from the object
        String name = newRecipe.getRecipeName();
        String description = newRecipe.getRecipeDescription();
        String instruction = newRecipe.getRecipeInstruction();
        String type = newRecipe.getRecipeType();
        String style = newRecipe.getRecipeStyle();
        boolean userCreated = newRecipe.getUserCreated();
        boolean favorited = newRecipe.getFavorited();
        String notes = newRecipe.getRecipeNotes();

        int newId = -1;
        //check if there's a recipe with the same name, if there is, just return this recipe
        try{
            newId = contains(newRecipe);
            if(newId != -1)
                return newId;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        };

        try(final Connection dbConnect = connectDB();){

            //if the ingredient is not in the database, insert it
            final PreparedStatement insertRecipe = dbConnect.prepareStatement("INSERT INTO Recipes(Title,Description,Instructions,Notes,Style,Type,UserCreated,Favorited) VALUES(?, ?, ?, ?, ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            insertRecipe.setString(1, name);
            insertRecipe.setString(2, description);
            insertRecipe.setString(3, instruction);
            insertRecipe.setString(4, notes);
            insertRecipe.setString(5, style);
            insertRecipe.setString(6, type);
            insertRecipe.setBoolean(7, userCreated);
            insertRecipe.setBoolean(8, favorited);
            insertRecipe.executeQuery();
            //get the key
            try(ResultSet rset = insertRecipe.getGeneratedKeys()){
                if(rset.next())
                    newId = rset.getInt("RecipeID");
                else{
                    throw new SQLException("Insert recipe fail!");
                }
            }
        }catch (final SQLException e){
            System.out.println(e.getMessage());
        }
        return newId;
    }

    @Override
    public Ingredient insertIngredient(Ingredient newIngredient) {
        return null;
    }



    @Override
    public Recipe updateRecipe(Recipe currRecipe) {
        return null;
    }

    @Override
    public void deleteRecipe(Recipe discardRecipe)
    {

    }

    @Override
    public void deleteIngredient(Ingredient discardIngredient)
    {

    }

    @Override
    public int getNewestId() {
        int newId = 0;
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            final ResultSet maxId = state.executeQuery("SELECT MAX(id) FROM Recipes");
            newId = maxId.getInt("id") + 1;
        }catch (final SQLException e){
            System.out.println("Database reading error!");
        }
        return newId;
    }

    private int contains(Recipe recipe) throws SQLException{
        String name = recipe.getRecipeName();

        try(final Connection dbConnect = connectDB()){
            final PreparedStatement state = dbConnect.prepareStatement("SELECT * FROM Recipes r WHERE r.Title = ?");
            state.setString(1,name);
            final ResultSet rset = state.executeQuery();
            //if no result, ingredient does not exist in the database
            if(!rset.next())
                return -1;
            else
                return rset.getInt("RecipeID");

        }catch (final SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

}
