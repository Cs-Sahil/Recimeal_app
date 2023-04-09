package comp3350.recimeal.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.R;
import comp3350.recimeal.business.AccessIngredients;
import comp3350.recimeal.business.AccessRecipes;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;

public class GroceryActivity extends AppCompatActivity {

    private AccessRecipes accessRecipes;
    private AccessIngredients accessIngredients;
    Recipe recipeToDisplay;
    private List<Ingredient> ingredientList;
    private ArrayAdapter<Ingredient> ingredientArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        Bundle b = getIntent().getExtras();
//        int recipeID = b.getInt("RecipeToRead");
//        int recipeID = 0;

        //recipeIds.add(0);
        //recipeIds.add(1);

        accessRecipes = new AccessRecipes();
        accessIngredients = new AccessIngredients();
        List<Integer> recipeIds = accessRecipes.getGroceryRecipes();
//        recipeToDisplay = accessRecipes.getRecipeById(recipeID);

        List<Ingredient> allIngredients = new ArrayList<>();
        for (int recipeID : recipeIds) {
            try {
                List<Ingredient> recipeIngredients = accessIngredients.getRecipeIngredients(recipeID);
                allIngredients.addAll(recipeIngredients);
            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
                return;
            }
        }

        try {
            ingredientArrayAdapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, allIngredients) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(allIngredients.get(position).getName());

                    String amountStr = allIngredients.get(position).getAmount() + "";
                    if (amountStr.endsWith(".0"))
                        amountStr = amountStr.substring(0, amountStr.length() - 2);
                    if (allIngredients.get(position).getUnit() != null && allIngredients.get(position).getAmount() > 0)
                        text2.setText(amountStr + " " + allIngredients.get(position).getUnit());
                    else if (allIngredients.get(position).getAmount() > 0)
                        text2.setText(amountStr + "");
                    else
                        text2.setText(allIngredients.get(position).getUnit());
                    return view;
                }
            };

            final ListView listView = (ListView) findViewById(R.id.listIngredients);
            listView.setAdapter(ingredientArrayAdapter);
        } catch (final Exception e) {
            Messages.fatalError(this, e.getMessage());
        }


//        try {
//            ingredientList = accessIngredients.getRecipeIngredients(recipeID);
//            ingredientArrayAdapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, ingredientList) {
//                @Override
//                public View getView(int position, View convertView, ViewGroup parent) {
//                    View view = super.getView(position, convertView, parent);
//
//                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//                    text1.setText(ingredientList.get(position).getName());
//
//                    String amountStr = ingredientList.get(position).getAmount() + "";
//                    if (amountStr.endsWith(".0"))
//                        amountStr = amountStr.substring(0, amountStr.length() - 2);
//                    if (ingredientList.get(position).getUnit() != null && ingredientList.get(position).getAmount() > 0)
//                        text2.setText(amountStr + " " + ingredientList.get(position).getUnit());
//                    else if (ingredientList.get(position).getAmount() > 0)
//                        text2.setText(amountStr + "");
//                    else
//                        text2.setText(ingredientList.get(position).getUnit());
//                    return view;
//                }
//            };
//
//            final ListView listView = (ListView) findViewById(R.id.listIngredients);
//            listView.setAdapter(ingredientArrayAdapter);
//        } catch (final Exception e) {
//            Messages.fatalError(this, e.getMessage());
//        }


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set grocery selected
        bottomNavigationView.setSelectedItemId(R.id.groceryList);

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
                    case R.id.groceryList:
                        return true;
                }
                return false;
            }
        });


    }
}