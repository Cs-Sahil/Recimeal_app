package comp3350.recimeal.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import comp3350.recimeal.R;
import comp3350.recimeal.objects.Recipe;

public class CreateActivity extends Activity {

    private TableLayout table;
    private Messages popup;
    private int nextID = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        try
        {
            table = (TableLayout) findViewById(R.id.ingredientTable);
            popup = new Messages();
        }
        catch (final Exception e)
        {
            Messages.fatalError(this, e.getMessage());
        }

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

    //-----------------------------------------------------------
    //This function is untested and is not called anywhere
    //it returns null on failure, returns a recipe on success
    private Recipe makeRecipeFromUI()
    {
        Recipe input=null;
        int ingStart = 2;  //The first two rows in table are not ingredients, do not read them
        int ingEnd = table.getChildCount();

        //validate for title, desc, prep in some logic class
        //validate that there is as least one ingredient (ingEnd>ingStart)

        EditText title= (EditText) findViewById(R.id.createTitleText);
        EditText desc = (EditText) findViewById(R.id.createDescriptionText);
        EditText prep = (EditText) findViewById(R.id.createPrepText);

        input =  new Recipe(title.getText().toString(),prep.getText().toString(),desc.getText().toString());

        //get ingredients
        for(int i = ingStart;i<ingEnd;i++)
        {
            View view = table.getChildAt(i);
            if(view instanceof TableRow)
            {
                TableRow row = (TableRow) view;
                try
                {
                    TextView ingAmount = (TextView) row.getChildAt(0);
                    TextView ingName = (TextView) row.getChildAt(1);

                    //we would add ingredients here, but recipe does not accept a string for amount
                    input.addIngred(ingAmount.getText().toString(),5);
                }
                catch (Exception e)
                {
                    popup.warning(this, "An error occurred while reading ingredients. Try removing them and re-entering.");
                    input=null;
                }
            }
        }

        return input;
    }
}
