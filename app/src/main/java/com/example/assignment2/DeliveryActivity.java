package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.jar.Attributes;

public class DeliveryActivity extends AppCompatActivity {
    private SharedPreferences myPreference;
    SharedPreferences.Editor prefEditor;
    EditText NameEditText, AddressEditText, PostalEditText, CityEditText, PhoneEditText, EmailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_info);

        NameEditText  = (EditText) findViewById(R.id.editTxtFullName);
        AddressEditText  = (EditText) findViewById(R.id.editTextPostalAddress);
        PostalEditText  = (EditText) findViewById(R.id.editTextPostalCode);
        CityEditText  = (EditText) findViewById(R.id.editTextCity);
        PhoneEditText  = (EditText) findViewById(R.id.editTextPhone);
        EmailEditText  = (EditText) findViewById(R.id.editTextEmailAddress);

        myPreference = getSharedPreferences("info", 0);
        //prepare it for edit by creating and Edit object
        prefEditor = myPreference.edit();
    }

    public boolean Validate(){
        final String name = NameEditText.getText().toString();
        final String address = AddressEditText.getText().toString();
        final String postalCode = PostalEditText.getText().toString();
        final String city = CityEditText.getText().toString();
        final String phone = PhoneEditText.getText().toString();
        final String email = EmailEditText.getText().toString();

        //Validation Conditions
        if(name.length()==0){
            NameEditText.requestFocus();
            NameEditText.setError("Please enter your name.");
            return false;
        }
        else if(address.length()==0){
            AddressEditText.requestFocus();
            AddressEditText.setError("Please enter valid address.");
            return false;
        }
        else if(postalCode.length()==0){
            PostalEditText.requestFocus();
            PostalEditText.setError("Please enter valid postal code.");
            return false;
        }
        else if(city.length()==0){
            CityEditText.requestFocus();
            CityEditText.setError("Please enter valid city.");
            return false;
        }
        else if(phone.length()==0){
            PhoneEditText.requestFocus();
            PhoneEditText.setError("Please enter valid phone number.");
            return false;
        }
        else if(email.length()==0){
            EmailEditText.requestFocus();
            EmailEditText.setError("Please enter valid email.");
            return false;
        }
        else {
            return true;
        }

    }

    public void onConfirmOrderClick(View view){
        // Only proceed if validation cleared
        if (Validate()){

            prefEditor.putString("CustomerName", NameEditText.getText().toString());
            prefEditor.putString("CustomerAddress", AddressEditText.getText().toString());
            prefEditor.putString("CustomerPostalCode", PostalEditText.getText().toString());
            prefEditor.putString("CustomerCity", CityEditText.getText().toString());
            prefEditor.putString("CustomerPhone", PhoneEditText.getText().toString());
            prefEditor.putString("CustomerEmail", EmailEditText.getText().toString());

            //commit the transaction
            prefEditor.commit();

            Intent intent = new Intent(this, ConfirmationActivity.class);
            startActivity(intent);
        }
    }
}
