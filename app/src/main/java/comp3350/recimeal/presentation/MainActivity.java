package comp3350.recimeal.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.recimeal.R;
import comp3350.recimeal.business.AccessCourses;
import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.objects.Course;
import comp3350.recimeal.objects.Recipe;

public class MainActivity extends Activity {

    private AccessRecipes accessRecipes;
    private List<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private int selectedRecipePosition = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessRecipes = new AccessRecipes();

        try {
            recipeList = accessRecipes.getRecipes();
            recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, recipeList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(recipeList.get(position).getRecipeName());
                    text2.setText(recipeList.get(position).getRecipeDescription());

                    return view;
                }
            };

            final ListView listView = (ListView)findViewById(R.id.listRecipes);
            listView.setAdapter(recipeArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    selectedRecipePosition = position;
                    selectRecipeAtPosition(position);

                }
            });

        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }

    }
    public void selectRecipeAtPosition(int position) {
        Recipe selected = recipeArrayAdapter.getItem(position);

        //    EditText editID = (EditText)findViewById(R.id.editCourseID);
        //   EditText editName = (EditText)findViewById(R.id.editCourseName);

        // editID.setText(selected.getCourseID());
        // editName.setText(selected.getCourseName());
        //editName.setText("it worked");

        //this makes it change 'scenes' back to the home screen when something picked
        Intent recipeIntent = new Intent(this, RecipesActivity.class);
        String recipeInfo[] = new String[] {selected.getRecipeName(),selected.getRecipeDescription(), selected.getRecipeInstruction()};
        recipeIntent.putExtra("RecipeToRead",recipeInfo);
        this.startActivity(recipeIntent);
    }
}
