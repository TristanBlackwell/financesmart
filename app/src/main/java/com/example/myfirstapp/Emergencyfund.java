package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Emergencyfund extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_fund);
    }

    public void submitEmergencyFund(View view) {

        EditText emergencyfundInput = findViewById(R.id.emergencyfundInput);
        Integer emergencyfundValue = -5;

        try {
            emergencyfundValue = Integer.parseInt(emergencyfundInput.getText().toString());
        } catch (Exception e) {
            emergencyfundInput.requestFocus();
            emergencyfundInput.setError("Please enter your saved amount");
        }

        if (emergencyfundValue >= 0) {
            SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
            SharedPreferences.Editor editor = store.edit();
            editor.putInt("emergencyfund", emergencyfundValue);
            editor.apply();

            Intent pensionScheme = new Intent(this, PensionScheme.class);
            startActivity(pensionScheme);
        } else {
            emergencyfundInput.requestFocus();
            emergencyfundInput.setError("Please enter a valid amount");
        }


    }

}