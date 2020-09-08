package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class PensionScheme extends AppCompatActivity {
    public static boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pension_scheme);

        ToggleButton pensionSchemeToggle = findViewById(R.id.pensionSchemeToggle);
        pensionSchemeToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checked = isChecked;
            }
        });
    }

    public void submitPensionScheme(View view) {

        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putBoolean("pensionMax", checked);
        editor.apply();

        Intent highInterestDebt = new Intent(this, HighInterestDebt.class);
        startActivity(highInterestDebt);

    }
}