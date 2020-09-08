package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Outgoings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoings);
    }

    public void submitOutgoings(View view) {

        EditText outgoingsInput = findViewById(R.id.outgoingsInput);
        Integer outgoingsValue = 0;
        try {
            outgoingsValue = Integer.parseInt(outgoingsInput.getText().toString());
        } catch (Exception e) {
            outgoingsInput.requestFocus();
            outgoingsInput.setError("Please enter your outgoing spend");
        }

        if (outgoingsValue > 0) {
            SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
            SharedPreferences.Editor editor = store.edit();
            editor.putInt("outgoings", outgoingsValue);
            editor.apply();

            Intent emergencyfund = new Intent(this, Emergencyfund.class);
            startActivity(emergencyfund);
        } else {
            outgoingsInput.requestFocus();
            outgoingsInput.setError("Please enter your monthly outgoing spend");
        }
    }
}