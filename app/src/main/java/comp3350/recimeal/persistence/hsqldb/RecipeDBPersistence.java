package comp3350.recimeal.persistence.hsqldb;

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
        return new Recipe(recipeID,recipeName,recipeInstruction, recipeDescription);
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

        }
        return null;
    }

    @Override
    public int insertRecipe(Recipe newRecipe) {
        //get the fields from the object
        int newId = newRecipe.getRecipeId();
        String newName = newRecipe.getRecipeName();
        String newDescription = newRecipe.getRecipeDescription();
        String newInstruction = newRecipe.getRecipeInstruction();
        Map<Integer, Ingredient> ingredients = newRecipe.getIngredients();

        //check if there's a recipe with the same id, if there is, give the recipe a new id
        try(final Connection dbConnect = connectDB();){
            final Statement state = dbConnect.createStatement();
            String select = String.format("SELECT * FROM Recipes WHERE id = %d", newId);
            String insert = "";
            final ResultSet rset = state.executeQuery(select);
            //if not empty, assign a valid id
            if(rset.next()){
                final ResultSet maxId = state.executeQuery("SELECT MAX(id) FROM Recipes");
                newId = maxId.getInt("id") + 1;
            }
            insert = String.format("INSERT INTO Recipes(Title,Description,Instructions,Style,Type,UserCreated,Favorited) VALUES(%s, %s, %s, %s, %s, %d, %d)", newName, newDescription, newInstruction, "", "", 0, 0);
            state.execute(insert);

            //add all the ingredients
            IngredientPersistence ingredientPersistence = Services.getIngredientPersistence();
            for(Ingredient ingredient: ingredients.values()){
                //ingredient does not exist in the database
                if(ingredientPersistence.getIngredientById(ingredient.getId()) == null){
                    ingredientPersistence.insertIngredient(ingredient, newId);
                }else{
                    //only update the Contains, although I feel like this is not the right place to do it...
                }
            }
        }catch (final SQLException e){
            System.out.println("Database reading error!");
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

}
