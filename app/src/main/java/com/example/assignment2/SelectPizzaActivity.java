package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.ChineseCalendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class SelectPizzaActivity extends AppCompatActivity {
    String curPizza = "";
    Set<String> pizzaList = new HashSet<String>();
    Hashtable<String, HashSet<String>> pizzaCategories = new Hashtable<String, HashSet<String>>();
    //preference data variable
    private SharedPreferences myPreference;
    //variiable to modify preference data
    SharedPreferences.Editor prefEditor;
    ImageView pizzaImage;

    void BuildPizzaLibrary()
    {
        // Initialize Ingredient Sets
        HashSet<String> cadIng = new HashSet<String>();
        HashSet<String> cknIng = new HashSet<String>();
        HashSet<String> hwaIng = new HashSet<String>();
        HashSet<String> bacIng = new HashSet<String>();
        HashSet<String> vegIng = new HashSet<String>();
        HashSet<String> basIng = new HashSet<String>();

        //Populate Ingredients
        //Base Ingredients(Shared)
        basIng.add("Mozarella Cheese");
        bacIng.add("Tomato Sauce");
        //Canadian
        cadIng.addAll(basIng);
        cadIng.add("Bacon");
        cadIng.add("Pepperoni");
        cadIng.add("Mushrooms");
        //Chicken
        cknIng.addAll(basIng);
        cknIng.add("Chicken Breast");
        cknIng.add("Alfredo Sauce");
        cknIng.add("Roasted Garlic");
        cknIng.add("Parmesan Cheese");
        //Hawaian
        hwaIng.addAll(basIng);
        hwaIng.add("Pineapple");
        hwaIng.add("Ham");
        hwaIng.add("Bacon");
        hwaIng.add("Red Onion");
        //Bacon
        bacIng.add("Alfredo Sauce");
        bacIng.add("Maple Bacon Strips");
        bacIng.add("Bacon Strips");
        bacIng.add("Bacon Crumble");
        bacIng.add("Bacon Mushrooms");
        bacIng.add("Cheddar Cheese");
        //Veggie
        vegIng.addAll(basIng);
        vegIng.add("Mushrooms");
        vegIng.add("Green Pepper");
        vegIng.add("Red Onion");

        //Fill Dictionary
        pizzaCategories.put("Canadian",cadIng);
        pizzaCategories.put("Chicken",cknIng);
        pizzaCategories.put("Hawaiian",hwaIng);
        pizzaCategories.put("Bacon",bacIng);
        pizzaCategories.put("Veggie",vegIng);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_builder);
        BuildPizzaLibrary();
        myPreference = getSharedPreferences("info",MODE_PRIVATE);
        prefEditor= myPreference.edit();
        //pizzaList = myPreference.getStringSet("pizzas", new HashSet<String>());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pizza_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        ImageView pizzaImage = findViewById(R.id.pizzaImage);
        TextView ingList = findViewById(R.id.pizzaIngredient);
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Canadian:
                curPizza = "Canadian Pizza";
                Toast.makeText(this, "You selected Canadian!", Toast.LENGTH_LONG).show();
                pizzaImage.setImageResource(R.drawable.pizza_canadian);
                ingList.setText(IngredientBuilder(pizzaCategories.get("Canadian")));

                break;

            case R.id.Chicken:
                curPizza = "Chicken Caesar Pizza";
                Toast.makeText(this, "You selected Chicken!", Toast.LENGTH_LONG).show();
                pizzaImage.setImageResource(R.drawable.pizza_chicken_caesar);
                ingList.setText(IngredientBuilder(pizzaCategories.get("Chicken")));
                //show HealthInformaticsActivity
                //intent = new Intent(this, HealthInformaticsActivity.class);
                //startActivity(intent);
                break;

            case R.id.Hawaiian:
                curPizza = "Hawaiian Pizza";
                Toast.makeText(this, "You selected Hawaiian!", Toast.LENGTH_LONG).show();
                pizzaImage.setImageResource(R.drawable.pizza_hawaiian);
                ingList.setText(IngredientBuilder(pizzaCategories.get("Hawaiian")));
                //Toast.makeText(this, "You selected Game Programming!", Toast.LENGTH_LONG).show();
                //show GamingActivity
                //intent = new Intent(this, GamingActivity.class);
                //startActivity(intent);
                break;

            case R.id.Bacon:
                curPizza = "Maple Smoked Bacon Pizza";
                Toast.makeText(this, "You selected Bacon!", Toast.LENGTH_LONG).show();
                pizzaImage.setImageResource(R.drawable.pizza_smokey_maple_bacon);
                ingList.setText(IngredientBuilder(pizzaCategories.get("Bacon")));
                break;

            case R.id.Veggie:
                curPizza = "Veggie Lover Pizza";
                Toast.makeText(this, "You selected Veggie!", Toast.LENGTH_LONG).show();
                pizzaImage.setImageResource(R.drawable.pizza_veggie_lovers);
                ingList.setText(IngredientBuilder(pizzaCategories.get("Veggie")));
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void onCheckboxClicked (View view){
        //Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if (checked)
            pizzaList.add(curPizza);
        else
            pizzaList.remove(curPizza);
    }

    public String IngredientBuilder(HashSet<String> set){
        String result = "Ingredients: \n";
        for (String s:set
             ) {
            result+= "\t\u2022"+s+"\n";
        }
        return result;
    }

    public void onClickCustomize(View view){
        prefEditor.putStringSet("pizzas", pizzaList);
        prefEditor.commit();

        Intent intent = new Intent(SelectPizzaActivity.this, CustomizePizzaActivity.class);
        startActivity(intent);
    }

}
