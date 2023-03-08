package comp3350.recimeal.persistence.hsqldb;

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

        }
        return null;
    }

    @Override
    public int insertIngredient(Ingredient newIngredient, int recipeID) {
        //get the fields from the object
        int newId = newIngredient.getId();
        String newName = newIngredient.getName();
        float newAmount = newIngredient.getAmount();
        String newUnit = newIngredient.getUnit();

        //check if there's a recipe with the same id, if there is, give the recipe a new id
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            String select = String.format("SELECT * FROM Ingredients WHERE RecipeID = %d", newId);
            String ingredientInsert = "";
            String containsInsert = "";
            final ResultSet rset = state.executeQuery(select);
            //if not empty, assign a valid id
            if(rset.next()){
                newId = getNewestId();
            }
            ingredientInsert = String.format("INSERT INTO Ingredients(IngredientID, Title) VALUES(%d,%s)", newId, newName);
            state.execute(ingredientInsert);
            containsInsert = String.format("INSERT INTO Contains(RecipeID, IngredientID, Amount, UnitOfMeasure) VALUES (%d, %d, %d, %s)", recipeID, newId, newAmount, newUnit);
            state.execute(containsInsert);
        }catch (final SQLException e){
            System.out.println("Database reading error!");
        }
        return newId;
    }

    @Override
    public Ingredient updateIngredient(Ingredient currIngredient) {
        return null;
    }

    @Override
    public void deleteIngredient(Ingredient discardIngredient) {

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
            System.out.println("Database reading error!");
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
}
