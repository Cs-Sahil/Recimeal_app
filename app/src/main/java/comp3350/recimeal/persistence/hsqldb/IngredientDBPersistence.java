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
        return null;
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return null;
    }

    @Override
    public Ingredient insertIngredient(Ingredient newIngredient) {
        return null;
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
    private Ingredient fromResultSet(final ResultSet rset) throws SQLException
    {
        final int ingredientID = rset.getInt("IngredientID");
        final String ingredientName = rset.getString("Title");
        final Float ingredientAmount = rset.getFloat("Amount");
        final String ingredientUnit = rset.getString("UnitOfMeasure");
        return new Ingredient(ingredientID,ingredientName,ingredientAmount, ingredientUnit);
    }
}
