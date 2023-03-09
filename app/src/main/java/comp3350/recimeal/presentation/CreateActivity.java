package comp3350.recimeal.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import comp3350.recimeal.R;
import comp3350.recimeal.application.Main;
import comp3350.recimeal.business.CreateRecipes;
import comp3350.recimeal.business.VerifyRecipes;
import comp3350.recimeal.objects.Ingredient;
import comp3350.recimeal.objects.Recipe;

public class CreateActivity extends Activity {

    private TableLayout table;
    private Messages popup;
    private int nextID = 1;
    private VerifyRecipes verifyRecipes;
    private CreateRecipes createRecipes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        try
        {
            table = (TableLayout) findViewById(R.id.ingredientTable);
            popup = new Messages();
            verifyRecipes = new VerifyRecipes();
            createRecipes = new CreateRecipes();
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Create selected
        bottomNavigationView.setSelectedItemId(R.id.newRecipe);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.newRecipe:
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

    //will take input from the input part of the table
    //to add a new ingredient to the list
    public void addIngredientToTable(View view)
    {
        //check and get the input
        EditText amount = (EditText)findViewById(R.id.createIngredientAmount);
        EditText type = (EditText)findViewById(R.id.createIngredientName);
        String amountStr = amount.getText().toString();
        String typeStr = type.getText().toString();

        if(amountStr==null || amountStr.equals(""))
        {
            //they didn't give an amount
            popup.warning(this, "Ingredient amount is required. Ex:1 cup");
        }
        else if (typeStr==null || typeStr.equals(""))
        {
            //they didn't give an ingredient name
            popup.warning(this, "Ingredient name is required. Ex:Sugar");
        }
        else
        {
            int thisId = nextID++;  //ensures every row here has a unique id
            //valid input

            //create the table row
            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(new TableRow.LayoutParams
                    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            newRow.setId(thisId);

            //create the things in the row
            //amount
            TextView amountName = craftIngredientText(amountStr,0);
            newRow.addView(amountName);
            //type
            TextView typeName = craftIngredientText(typeStr,1);
            newRow.addView(typeName);
            //cancel button
            ImageButton cancelButton = craftCancelButton(newRow);
            newRow.addView(cancelButton);

            //add constructed row to table
            table.addView(newRow);

            //clear the add bars to quickly type next one
            amount.setText("");
            type.setText("");
        }

    }

    //creates a textview with a specific format
    private TextView craftIngredientText(String content, int column)
    {
        TextView tv = new TextView(this);
        tv.setText(content);
        tv.setPadding(8,8,8,8);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
        tv.setLayoutParams(new TableRow.LayoutParams(column));

        return tv;
    }

    //creates the cancel button for a row in the ingredient table
    private ImageButton craftCancelButton(TableRow newRow)
    {
        ImageButton cancelButton = new ImageButton(this);
        cancelButton.setMaxHeight(64);
        cancelButton.setMaxWidth(16);
        cancelButton.setMinimumWidth(16);
        cancelButton.setMinimumHeight(32);
        cancelButton.setPadding(16,8,16,8);
        cancelButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        cancelButton.setAdjustViewBounds(true);
        cancelButton.setImageResource(android.R.drawable.ic_delete);
        cancelButton.setLayoutParams(new TableRow.LayoutParams(2));
        CreateActivity act = this;
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TableRow myRow = newRow;
                table.removeView(myRow);
            }
        });
        return cancelButton;
    }

    public void makeRecipeFromUI(View view)
    {
        int ingStart = 2;  //The first two rows in table are not ingredients, do not read them
        int ingEnd = table.getChildCount();
        EditText title= (EditText) findViewById(R.id.createTitleText);
        String titleStr = title.getText().toString();
        EditText desc = (EditText) findViewById(R.id.createDescriptionText);
        String descStr = desc.getText().toString();
        EditText prep = (EditText) findViewById(R.id.createPrepText);
        String prepStr = prep.getText().toString();

        String check = verifyRecipes.validateAll(ingStart,ingEnd,title.getText().toString(),desc.getText().toString(),prep.getText().toString());

        if(check.length()>0)
        {
            popup.warning(this,check);
        }
        else
        {
            // createRecipe(name, instruction, description, String style, String type, List<Ingredient> ingredientList)
            //get ingredients
            List<Ingredient> ingList = makeIngredientListFromUI(ingStart,ingEnd);

            if(ingList.size()>0)
            {
                int res = createRecipes.createRecipe(titleStr,prepStr,descStr,"","",ingList);
                if(res!=-1)
                    popup.warning(this,"Created recipe for "+titleStr);
                else
                    popup.warning(this,"There was a problem submitting the recipe.");
            }
            else
            {
                popup.warning(this, "An error occurred while reading ingredients. Try removing them and re-entering.");
            }
        }
    }

    private List<Ingredient> makeIngredientListFromUI(int ingStart,int ingEnd)
    {
        List<Ingredient> ingList = new ArrayList<Ingredient>();

        //get ingredients
        for(int i = ingStart;i<ingEnd;i++)
        {
            View viewRow = table.getChildAt(i);
            if (viewRow instanceof TableRow) {
                TableRow row = (TableRow) viewRow;
                Ingredient newIng;
                try
                {
                    TextView ingAmount = (TextView) row.getChildAt(0);
                    float ingNum;
                    String ingAmountStr;
                    String[] split = ingAmount.getText().toString().split(" ", 2);
                    try {
                        ingNum = Float.parseFloat(split[0]);
                        if(split.length>1)
                            ingAmountStr = split[1];
                        else
                            ingAmountStr = "";
                    } catch (Exception e) {
                        ingNum = 0;
                        ingAmountStr = split[0];
                    }
                    TextView ingName = (TextView) row.getChildAt(1);

                    newIng = new Ingredient(-1, ingName.getText().toString(), ingNum, ingAmountStr);
                    ingList.add(newIng);
                }
                catch (Exception e)
                {
                    popup.warning(this, "An error occurred while reading ingredients. Try removing them and re-entering.");
                }
            }
        }

        return ingList;
    }
}