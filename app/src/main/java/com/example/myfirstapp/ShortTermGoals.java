package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class ShortTermGoals extends AppCompatActivity {
    public boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_term_goals);

        ToggleButton shortTermToggle = findViewById(R.id.shortTermToggle);
        shortTermToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checked = isChecked;
            }
        });
    }

    public void submitShortTerm(View view) {

        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putBoolean("shortTermGoals", checked);
        editor.apply();

        Intent longTermGoals = new Intent(this, LongTermGoals.class);
        startActivity(longTermGoals);
    }
}