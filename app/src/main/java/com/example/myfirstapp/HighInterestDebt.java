package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class HighInterestDebt extends AppCompatActivity {
    public static boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_interest_debt);


        Switch debtSwitch = findViewById(R.id.highInterestDebtSwitch);
        debtSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checked = isChecked;
                System.out.print(isChecked);

                ConstraintLayout highInterestDebt0 = (ConstraintLayout) findViewById(R.id.highInterestDebt0);
                if (isChecked) {
                    highInterestDebt0.setVisibility(View.VISIBLE);
                    System.out.println("visible");
                } else {
                    highInterestDebt0.setVisibility(View.INVISIBLE);
                    System.out.println("invisible");
                }
            }
        });
    }

    public void submitHighInterestDebt(View view) {

        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putBoolean("highInterestDebt", checked);
        editor.apply();

        Intent firstHome = new Intent(this, FirstHome.class);
        startActivity(firstHome);
    }
}
