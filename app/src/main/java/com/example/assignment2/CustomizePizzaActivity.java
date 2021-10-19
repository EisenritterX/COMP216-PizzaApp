package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CustomizePizzaActivity extends AppCompatActivity {
    private SharedPreferences myPreference;
    SharedPreferences.Editor prefEditor;
    String crustType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customize_pizza);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radg_crust);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radThickCrust = (RadioButton) findViewById(R.id.rad_thick_crust);
                if (radThickCrust.isChecked()) {
                    crustType = "Thin Crust";
                } else {
                    crustType = "Thick Crust";;
                }
            }
        });

        //instantiate the preference data variable
        myPreference = getSharedPreferences("info", 0);
        //prepare it for edit by creating and Edit object
        prefEditor = myPreference.edit();
        //store "set" in  a preference named "program"
    }

    public void onCheckoutClick(View view){

        Spinner spin_pizzasize = (Spinner) findViewById(R.id.spin_pizza_size);
        TextView txt_pizzaSize = (TextView)spin_pizzasize.getSelectedView();

        prefEditor.putString("pizzaSize", (String)txt_pizzaSize.getText());
        prefEditor.putString("pizzaCrust", crustType);
        //commit the transaction
        prefEditor.commit();

        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
}
