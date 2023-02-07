package comp3350.recimeal.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import comp3350.recimeal.R;
import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.objects.Recipe;


public class RecipesActivity extends Activity {

    private AccessRecipes accessRecipes;
    private List<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private int selectedRecipePosition = -1;

    TextView recipeTitle;
    TextView recipeDescription;
    TextView recipeInstruct;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        Intent recipeInfo = getIntent();//.getExtras();
        recipeTitle = (TextView)findViewById(R.id.textRecipeTitle);
        recipeDescription = (TextView)findViewById(R.id.textRecipeDescription);
        recipeInstruct = (TextView)findViewById(R.id.textRecipeInstruct);
        if(recipeInfo!= null) {
            String newInfo[] = recipeInfo.getStringArrayExtra("RecipeToRead");

            updateTitle(newInfo[0]);
            updateDescription(newInfo[1]);
            updateInstruct(newInfo[2]);

        }
       // accessRecipes = new AccessRecipes();
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
