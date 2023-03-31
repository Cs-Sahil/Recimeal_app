package comp3350.recimeal.persistence.hsqldb;

import android.util.Log;

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
import comp3350.recimeal.objects.PermanentRecipe;
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


        return new PermanentRecipe(recipeID,recipeName, recipeDescription,recipeInstruction,recipeStyle,recipeType,userCreated,favorited,notes);
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
            throw new PersistenceException("Fail to connect to database, please contact the developer.", e);
        }
    }




    @Override
    public Recipe getRecipeById(int id) {
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            String query = String.format("SELECT * FROM Recipes WHERE id = %d", id);
            final ResultSet rset = state.executeQuery(query);
            return fromResultSet(rset);
        }catch (final SQLException e){
            Log.d("RecipeDBPersistence", "getRecipeById failed DB connect: "+e.getMessage());
            throw new PersistenceException("Fail to connect to database, please contact the developer.", e);
        }
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
        newId = contains(newRecipe);
        if(newId != -1)
            return newId;

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
            insertRecipe.executeUpdate();
            //get the key
            try(ResultSet rset = insertRecipe.getGeneratedKeys()){
                if(rset.next())
                    newId = rset.getInt("RecipeID");
                else{
                    throw new SQLException("Insert recipe fail!");
                }
            }
        }catch (final SQLException e){
            Log.d("RecipeDBPersistence", "insertRecipe failed DB connect: "+e.getMessage());
            throw new PersistenceException("Fail to connect to database, please contact the developer.", e);
        }
        return newId;
    }

    @Override
    public Recipe updateRecipe(Recipe currRecipe) {
        //get the fields from the object
        String name = currRecipe.getRecipeName();
        String description = currRecipe.getRecipeDescription();
        String instruction = currRecipe.getRecipeInstruction();
        String type = currRecipe.getRecipeType();
        String style = currRecipe.getRecipeStyle();
        boolean userCreated = currRecipe.getUserCreated();
        boolean favorited = currRecipe.getFavorited();
        String notes = currRecipe.getRecipeNotes();

        int newId = -1;
        //check if there's a recipe with the same name, if there is, just return this recipe
        newId = contains(currRecipe);
        if(newId == -1)
            return null;

        try(final Connection dbConnect = connectDB();){

            //if the ingredient is not in the database, insert it
            final PreparedStatement updateRecipe = dbConnect.prepareStatement("UPDATE Recipes SET Title = ?, Description = ?, Instruction = ?, Notes = ?, Style = ?, Type = ?, UserCreated = ?, Favorited = ?");
            updateRecipe.setString(1, name);
            updateRecipe.setString(2, description);
            updateRecipe.setString(3, instruction);
            updateRecipe.setString(4, notes);
            updateRecipe.setString(5, style);
            updateRecipe.setString(6, type);
            updateRecipe.setBoolean(7, userCreated);
            updateRecipe.setBoolean(8, favorited);
            updateRecipe.executeUpdate();

        }catch (final SQLException e){
            Log.d("RecipeDBPersistence", "insertRecipe failed DB connect: "+e.getMessage());
            throw new PersistenceException("Fail to connect to database, please contact the developer.", e);
        }
        return currRecipe;
    }

    @Override
    public void deleteRecipe(Recipe discardRecipe)
    {
        int id = discardRecipe.getRecipeId();

        try(final Connection dbConnect = connectDB();){
            final PreparedStatement deleteRecipe = dbConnect.prepareStatement("Delete from Recipes where Recipes.RecipeID = ?");
            deleteRecipe.setInt(1,id);
            deleteRecipe.executeUpdate();

        }
        catch(final SQLException e){
            Log.d("RecipeDBPersistence", "deleteRecipe failed DB connect: "+e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    private int contains(Recipe recipe){
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
            Log.d("RecipeDBPersistence", "Contains failed before DB connect: "+e.getMessage());
        }
        return -1;
    }

}
