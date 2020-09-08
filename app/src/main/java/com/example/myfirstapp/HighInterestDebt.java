package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;

public class HighInterestDebt extends AppCompatActivity {
    public static boolean checked;
    ArrayList<Debt> debts = new ArrayList<>();
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

    public void addDebt(View view) {

        EditText highInterestDebtName = findViewById(R.id.highInterestDebtName);
        EditText highInterestDebtAmount = findViewById(R.id.highInterestDebtAmount);
        EditText highInterestDebtMin = findViewById(R.id.highInterestDebtMin);
        EditText highInterestDebtRate = findViewById(R.id.highInterestDebtRate);

        String debtName = "";
        Integer debtAmount = 0, debtMin = 0, debtRate = 0;

        try {
            debtName = highInterestDebtName.getText().toString();
        } catch (Exception E) {
            highInterestDebtName.requestFocus();
            highInterestDebtName.setError("Enter a name for this debt");
        }

        try {
            debtAmount = Integer.parseInt(highInterestDebtAmount.getText().toString());
        } catch (Exception E) {
            highInterestDebtAmount.requestFocus();
            highInterestDebtAmount.setError("Enter the debt amount");
        }

        try {
            debtMin = Integer.parseInt(highInterestDebtMin.getText().toString());
        } catch (Exception E) {
            highInterestDebtMin.requestFocus();
            highInterestDebtMin.setError("Enter the minimum monthly payment");
        }

        try {
            debtRate = Integer.parseInt(highInterestDebtRate.getText().toString());
        } catch (Exception E) {
            highInterestDebtRate.requestFocus();
            highInterestDebtRate.setError("Enter the current interest rate");
        }

        Debt debt = new Debt(debtName, debtAmount, debtMin, debtRate);
        debts.add(debt);

        highInterestDebtName.setText("");
        highInterestDebtAmount.setText("");
        highInterestDebtMin.setText("");
        highInterestDebtRate.setText("");
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
