package com.example.assignment2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class ConfirmationActivity extends AppCompatActivity {
    private SharedPreferences myPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        myPreferences = getSharedPreferences("info",MODE_PRIVATE);
        //Set<String> pizzaList = myPreferences.getStringSet("Pizzas", new HashSet<String>());
        Set<String> pizzas =myPreferences.getStringSet("pizzas",new HashSet<String>());
        String pizzaSize = myPreferences.getString("pizzaSize","");
        String pizzaCrust = myPreferences.getString("pizzaCrust","");

        String result="";
        result+="Pizzas: \n";
        for (String strPizza: pizzas){
            result+=strPizza+"\n";
        }
        result+="Pizza Size: " + pizzaSize + "\n";
        result+="Pizza Crust: " + pizzaCrust + "\n";
        TextView pizzaConfirmation = (TextView)findViewById(R.id.txtFinalPizzaInfo);
        pizzaConfirmation.setText(result);

        String result2="Customer Info: \n";
        result2+="Name: " + myPreferences.getString("CustomerName","") + "\n";
        result2+="Address: " + myPreferences.getString("CustomerAddress","") + "\n";
        result2+="City: " + myPreferences.getString("CustomerCity","") + "\n";
        result2+="Postal Code: " + myPreferences.getString("CustomerPostalCode","") + "\n";
        result2+="Phone: " + myPreferences.getString("CustomerPhone","") + "\n";
        result2+="Email: " + myPreferences.getString("CustomerEmail","") + "\n";

        TextView customerConfirmation = (TextView)findViewById(R.id.txtFinalCustomerInfo);
        customerConfirmation.setText(result2);
    }
}
