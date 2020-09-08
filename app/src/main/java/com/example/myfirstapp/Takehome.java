package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.InputMismatchException;

public class Takehome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_home);
    }

    public void SubmitTakeHome(View view) {

        EditText takehomeInput = findViewById(R.id.takehomeInput);
        Integer takehomeValue = 0;

        try {
            takehomeValue = Integer.parseInt(takehomeInput.getText().toString());
        } catch (Exception e) {
            takehomeInput.requestFocus();
            takehomeInput.setError("Please enter a number");
        }

        if(takehomeValue  > 0) {

            // Create a SP and store takehome as Integer
            SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
            SharedPreferences.Editor editor = store.edit();
            editor.putInt("takehome", takehomeValue);
            editor.apply();

            // Create next activity and start
            Intent Outgoings = new Intent(this, Outgoings.class);
            startActivity(Outgoings);
        } else {
            takehomeInput.requestFocus();
            takehomeInput.setError("Enter your take home");
        }
    }
}