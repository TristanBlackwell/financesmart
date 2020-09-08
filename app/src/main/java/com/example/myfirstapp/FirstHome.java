package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

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
            }
        });
    }

    public void submitFirstHome(View view) {

        EditText firstHomeInput = findViewById(R.id.firstHomeInput);
        Integer firstHomeYears = Integer.parseInt(firstHomeInput.getText().toString());

        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putBoolean("firstHome", checked);
        editor.putInt("firstHomeYears", firstHomeYears);
        editor.apply();
    }
}