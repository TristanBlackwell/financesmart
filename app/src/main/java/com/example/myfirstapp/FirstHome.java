package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FirstHome extends AppCompatActivity {
    public static boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_home);

        Switch firstHomeSwitch = findViewById(R.id.firstHomeSwitch);
        firstHomeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checked = isChecked;

                EditText firstHomeInput = findViewById(R.id.firstHomeInput);
                if (isChecked) {
                    firstHomeInput.setVisibility(View.VISIBLE);
                } else {
                    firstHomeInput.setVisibility(View.GONE);
                }
            }
        });
    }

    public void submitFirstHome(View view) {

        EditText firstHomeInput = findViewById(R.id.firstHomeInput);
        int firstHomeYears = -1;

        if (checked) {
            try {
                firstHomeYears = Integer.parseInt(firstHomeInput.getText().toString());
            } catch (Exception e) {
                firstHomeInput.requestFocus();
                firstHomeInput.setError("Number of years until planned purchase");
            }
        }

        if (firstHomeYears >= 0) {
            SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
            SharedPreferences.Editor editor = store.edit();
            editor.putBoolean("firstHome", checked);
            editor.putInt("firstHomeYears", firstHomeYears);
            editor.apply();

            Intent shortTermGoals = new Intent(this, ShortTermGoals.class);
            startActivity(shortTermGoals);
        }
    }
}