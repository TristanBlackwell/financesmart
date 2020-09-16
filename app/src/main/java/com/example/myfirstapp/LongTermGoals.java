package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class LongTermGoals extends AppCompatActivity {
    public static boolean longTermChecked, longTermAgeChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_term_goals);

        Switch longTermSwitch = findViewById(R.id.longTermSwitch);
        final Switch longTermSwitchAge = findViewById(R.id.longTermSwitchAge);

        longTermSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                longTermChecked = isChecked;

                if (isChecked) {
                    longTermSwitchAge.setEnabled(true);
                } else {
                    longTermSwitchAge.setEnabled(false);
                }
            }
        });

        longTermSwitchAge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                longTermAgeChecked = isChecked;
            }
        });
    }

    public void submitLongTerm(View view){

        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putBoolean("longTermGoals", longTermChecked);
        editor.putBoolean("longTermAge", longTermAgeChecked);
        editor.apply();

        Intent summary = new Intent(this, Summary.class);
        startActivity(summary);
    }
}