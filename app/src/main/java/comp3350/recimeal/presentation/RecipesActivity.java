package comp3350.recimeal.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import comp3350.recimeal.R;
import comp3350.recimeal.objects.business.AccessRecipes;
import comp3350.recimeal.objects.Recipe;


public class RecipesActivity extends Activity {

    private AccessRecipes accessRecipes;
    Recipe recipeToDisplay;
    private String[] ingredientArray;
    private ArrayAdapter<String> ingredientArrayAdapter;
    private int selectedRecipePosition = -1;

    TextView recipeTitle;
    TextView recipeDescription;
    TextView recipeInstruct;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        Intent recipeInfo = getIntent();//.getExtras();
        recipeToDisplay = (Recipe)recipeInfo.getParcelableExtra("RecipeToRead");
        recipeTitle = (TextView)findViewById(R.id.textRecipeTitle);
        recipeDescription = (TextView)findViewById(R.id.textRecipeDescription);
        recipeInstruct = (TextView)findViewById(R.id.textRecipeInstruct);
        if(recipeInfo!= null) {
            updateTitle(recipeToDisplay.getRecipeName());
            updateDescription(recipeToDisplay.getRecipeDescription());
            updateInstruct(recipeToDisplay.getRecipeInstruction());

        }
        try {
            ingredientArray = recipeToDisplay.getIngredientList();
            ingredientArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, ingredientArray)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(ingredientArray[position]);
                    text2.setText(Integer.toString(recipeToDisplay.getIngredientAmount(ingredientArray[position])));

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listIngredients);
            listView.setAdapter(ingredientArrayAdapter);
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }

    }

    private void updateTitle(String newTitle) {
        recipeTitle.setText(newTitle);
    }

    private void updateDescription(String newDescription) {
        recipeDescription.setText(newDescription);
    }
    public void updateInstruct(String newInstruct){
        recipeInstruct.setText(newInstruct);
    }
}
