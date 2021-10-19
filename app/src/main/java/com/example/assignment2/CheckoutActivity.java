package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class CheckoutActivity extends AppCompatActivity {
    private SharedPreferences myPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

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
        TextView checkoutInfo = (TextView)findViewById(R.id.txtCheckout);
        checkoutInfo.setText(result);
    }

    public void onNextClick(View view){
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

}
