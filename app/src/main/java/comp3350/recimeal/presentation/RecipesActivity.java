package comp3350.recimeal.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import comp3350.recimeal.R;
import comp3350.recimeal.application.Services;
import comp3350.recimeal.business.AccessIngredients;
import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.business.CreateRecipes;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;


public class RecipesActivity extends Activity {

    private AccessRecipes accessRecipes;
    private AccessIngredients accessIngredients;
    private CreateRecipes createRecipes;
    Recipe recipeToDisplay;
    private List<Ingredient> ingredientList;
    private ArrayAdapter<Ingredient> ingredientArrayAdapter;
    private int selectedRecipePosition = -1;

    TextView recipeTitle;
    TextView recipeDescription;
    TextView recipeInstruct;
    Switch favSwitch;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        Bundle b = getIntent().getExtras();
        int recipeID = b.getInt("RecipeToRead");

        accessRecipes = new AccessRecipes();
        accessIngredients = new AccessIngredients();
        recipeToDisplay = accessRecipes.getRecipeById(recipeID);
        createRecipes = new CreateRecipes(accessRecipes, accessIngredients);

        recipeTitle = (TextView) findViewById(R.id.textRecipeTitle);
        recipeDescription = (TextView) findViewById(R.id.textRecipeDescription);
        recipeInstruct = (TextView) findViewById(R.id.textRecipeInstruct);
        favSwitch = (Switch) findViewById(R.id.favoriteSwitch);

        if (recipeToDisplay != null) {
            updateTitle(recipeToDisplay.getRecipeName());
            updateDescription(recipeToDisplay.getRecipeDescription());
            updateInstruct(recipeToDisplay.getRecipeInstruction());
            favSwitch.setChecked(recipeToDisplay.getFavorited());
        }
        try {
            ingredientList = accessIngredients.getRecipeIngredients(recipeID);
            ingredientArrayAdapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, ingredientList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(ingredientList.get(position).getName());

                    String amountStr = ingredientList.get(position).getAmount() + "";
                    if (amountStr.endsWith(".0"))
                        amountStr = amountStr.substring(0, amountStr.length() - 2);
                    if (ingredientList.get(position).getUnit() != null && ingredientList.get(position).getAmount() > 0)
                        text2.setText(amountStr + " " + ingredientList.get(position).getUnit());
                    else if (ingredientList.get(position).getAmount() > 0)
                        text2.setText(amountStr + "");
                    else
                        text2.setText(ingredientList.get(position).getUnit());
                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listIngredients);
            listView.setAdapter(ingredientArrayAdapter);
        } catch (final Exception e) {
            Messages.fatalError(this, e.getMessage());
        }

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.newRecipe:
                        startActivity(new Intent(getApplicationContext(),CreateActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void updateTitle(String newTitle) {
        recipeTitle.setText(newTitle);
    }

    private void updateDescription(String newDescription) {
        recipeDescription.setText(newDescription);
    }

    public void updateInstruct(String newInstruct) {
        recipeInstruct.setText(newInstruct);
    }

    public void buttonRecipeDeleteOnClick(View v) {
        try {

            accessRecipes.deleteRecipe(recipeToDisplay);
            // returning to home screen after deleting the recipe...
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            overridePendingTransition(0, 0);

        } catch (final Exception e) {
            Messages.fatalError(this, e.getMessage());
        }
    }

    public void favoriteButton(View view) {
        if (recipeToDisplay.getFavorited())
            recipeToDisplay.unsetFavorited();
        else
            recipeToDisplay.setFavorited();

        try
        {
            Recipe r = createRecipes.updateRecipe(recipeToDisplay);
        }
        catch (final Exception e)
        {
            Messages.warning(this,"Could not mark favourite: "+e.getMessage());

            //we don't want the switch to get out of sync
            if(recipeToDisplay.getFavorited())
            {
                recipeToDisplay.unsetFavorited();
                favSwitch.setChecked(false);
            }
            else
            {
                recipeToDisplay.setFavorited();
                favSwitch.setChecked(true);
            }
        }
    }

}
