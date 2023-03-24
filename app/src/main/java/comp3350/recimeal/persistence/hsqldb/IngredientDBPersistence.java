package comp3350.recimeal.persistence.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;
import comp3350.recimeal.persistence.IngredientPersistence;

public class IngredientDBPersistence extends DBPersistence implements IngredientPersistence {

    public IngredientDBPersistence(final String newDbPath)
    {
        super(newDbPath);
    }
    @Override
    public List<Ingredient> getIngredientSequential() {
        List<Ingredient> ingredients = new ArrayList<>();
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            String query = String.format("SELECT * FROM Ingredients i INNER JOIN Contains c ON i.RecipeID = c.RecipeID");
            final ResultSet rset = state.executeQuery(query);
            while(rset.next()){
                ingredients.add(fromResultSet(rset));
            }
            return ingredients;
        }catch (final SQLException e){
            Log.d("IngredientDBPersistence", "Retrieve failed before DB connect" + e.getMessage());
        }
        return null;
    }

    @Override
    public Ingredient getIngredientById(int id) {
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            String query = String.format("SELECT * FROM Recipes WHERE RecipeID = %d", id);
            final ResultSet rset = state.executeQuery(query);
            if(rset.next())
                return fromResultSet(rset);
        }catch (final SQLException e){
            Log.d("IngredientDBPersistence", "Retrieve failed before DB connect" + e.getMessage());
        }
        return null;
    }

    @Override
    public int insertIngredient(Ingredient newIngredient, int recipeID) {
        //get the fields from the object
        String name = newIngredient.getName();
        float amount = newIngredient.getAmount();
        String unit = newIngredient.getUnit();
        int newId = -1;
        try{
            newId = contains(newIngredient);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        };

        //check if there's a recipe with the same id, if there is, give the recipe a new id
        try(final Connection dbConnect = connectDB();){

            //if the ingredient is not in the database, insert it
            if(newId == -1){
                final PreparedStatement insertIngredient = dbConnect.prepareStatement("INSERT INTO Ingredients(Title) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
                insertIngredient.setString(1, name);
                insertIngredient.executeUpdate();
                try(ResultSet rset = insertIngredient.getGeneratedKeys()){
                    if(rset.next())
                        newId = rset.getInt("IngredientID");
                    else{
                        throw new SQLException("Insert ingredient fail!");
                    }
                }
            }
            //insert into contains in all cases
            final PreparedStatement insertContains = dbConnect.prepareStatement("INSERT INTO Contains(RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (?,?,?,?)");
            insertContains.setInt(1, recipeID);
            insertContains.setInt(2, newId);
            insertContains.setFloat(3, amount);
            insertContains.setString(4, unit);
            insertContains.executeUpdate();
        }catch (final SQLException e){
            System.out.println(e.getMessage());
        }
        return newId;
    }

    @Override
    public Ingredient updateIngredient(Ingredient currIngredient) {
        return null;
    }

    @Override
    public void deleteIngredient(Ingredient discardIngredient) {
        int discardID = discardIngredient.getId();

        try(final Connection dbConnect = connectDB()){
            final PreparedStatement state = dbConnect.prepareStatement("DELETE FROM Ingredients WHERE IngredientID = ?");
            state.setInt(1, discardID);
            state.executeUpdate();
        }catch (final SQLException e){
            Log.d("IngredientDBPersistence", "Delete failed before DB connect" + e.getMessage());
        }
    }
    @Override
    public List<Ingredient> getRecipesIngredients(int recipeId) {
        final List<Ingredient> ingredients = new ArrayList<>();
        try(final Connection dbConnect = connectDB();)
        {
            final PreparedStatement state = dbConnect.prepareStatement("SELECT Ingredients.IngredientID, Ingredients.Title, Contains.Amount, Contains.UnitOfMeasure FROM (Ingredients JOIN Contains on Ingredients.IngredientID = Contains.IngredientID) WHERE RecipeID = ?");
            state.setString(1,Integer.toString(recipeId));
            final ResultSet rset = state.executeQuery();
            while (rset.next()) {
                final Ingredient ingredientToAdd = fromResultSet(rset);
                ingredients.add(ingredientToAdd);
            }
            rset.close();
            state.close();

            return ingredients;

        }
        catch (final SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getNewestId() {
        int newId = 0;
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            final ResultSet maxId = state.executeQuery("SELECT MAX(id) FROM Ingredients");
            newId = maxId.getInt("RecipeID") + 1;
        }catch (final SQLException e){
            System.out.println(e.getMessage());
        }
        return newId;
    }

    private Ingredient fromResultSet(final ResultSet rset) throws SQLException
    {
        final int ingredientID = rset.getInt("IngredientID");
        final String ingredientName = rset.getString("Title");
        final Float ingredientAmount = rset.getFloat("Amount");
        final String ingredientUnit = rset.getString("UnitOfMeasure");
        return new Ingredient(ingredientID,ingredientName,ingredientAmount, ingredientUnit);
    }

    private int contains(Ingredient ingredient) throws SQLException{
        String name = ingredient.getName();

        try(final Connection dbConnect = connectDB()){
            final PreparedStatement state = dbConnect.prepareStatement("SELECT * FROM Ingredient i WHERE i.Title = ?");
            state.setString(1,name);
            final ResultSet rset = state.executeQuery();
            //if no result, ingredient does not exist in the database
            if(!rset.next())
                return -1;
            else
                return rset.getInt("IngredientID");

        }catch (final SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
}
