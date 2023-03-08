package comp3350.recimeal.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import comp3350.recimeal.R;
import comp3350.recimeal.application.Main;
import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.objects.Recipe;

public class MainActivity extends Activity {

    private AccessRecipes accessRecipes;
    private List<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private int selectedRecipePosition = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseToDevice();
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

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

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
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        selectedRecipePosition = -1;
    }

    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";
        String[] aNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            aNames = assetManager.list(DB_PATH);
            for (int i = 0; i < aNames.length; i++) {
                aNames[i] = DB_PATH + "/" + aNames[i];

                copyAssetsToDirectory(aNames, dataDirectory);

                Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());


            }

        }
        catch (final IOException ioe)
        {
            Messages.warning(this, "Failed to access application data: " + ioe.getMessage());
        }


    }
    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }




    public void selectRecipeAtPosition(int position) {
        Recipe selected = recipeArrayAdapter.getItem(position);

        //this makes it change 'scenes' when something picked
        Intent recipeIntent = new Intent(this, RecipesActivity.class);
        //String recipeInfo[] = new String[] {selected.getRecipeName(),selected.getRecipeDescription(), selected.getRecipeInstruction()};
        recipeIntent.putExtra("RecipeToRead",(Parcelable)selected);
        this.startActivity(recipeIntent);
    }

    public void addButtonPressed(View view)
    {
        Intent addIntent = new Intent(this,CreateActivity.class);
        this.startActivity(addIntent);
    }

    public void searchButtonPressed(View view)
    {
        EditText searchBar = (EditText) findViewById(R.id.searchRecipeText);
        String searchTerm = searchBar.getText().toString().toLowerCase(Locale.ROOT);

        final List<Recipe> searchList = accessRecipes.getSearchedRecipes(recipeList,searchTerm);

        //try to shove the new list into the view
        try {
            recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, searchList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(searchList.get(position).getRecipeName());
                    text2.setText(searchList.get(position).getRecipeDescription());

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
}
