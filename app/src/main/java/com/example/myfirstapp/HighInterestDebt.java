package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.google.gson.Gson;

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

                //
                ConstraintLayout highInterestDebt0 = (ConstraintLayout) findViewById(R.id.highInterestDebt0);
                if (isChecked) {
                    highInterestDebt0.setVisibility(View.VISIBLE);
                } else {
                    highInterestDebt0.setVisibility(View.GONE);
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

        if (!debtName.equals("") && debtAmount > 0 && debtMin >= 0 && debtRate >= 0) {
            Debt debt = new Debt(debtName, debtAmount, debtMin, debtRate);
            debts.add(debt);

            // construct listview for inputted debts
            ListView debtList = findViewById(R.id.debtList);
            DebtAdapter debtAdapter = new DebtAdapter(this, debts);
            debtList.setAdapter(debtAdapter);

            // Clear inputs, remove focus and collapse keyboard
            highInterestDebtName.setText("");
            highInterestDebtAmount.setText("");
            highInterestDebtMin.setText("");
            highInterestDebtRate.setText("");
            highInterestDebtRate.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void submitHighInterestDebt(View view) {

        System.out.println(debts);
        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.putBoolean("highInterestDebt", checked);

        // Convert debt object arraylist to json for storage in SP
        Gson gson = new Gson();
        String json = gson.toJson(debts);
        editor.putString("debtlist", json);

        editor.apply();

        Intent firstHome = new Intent(this, FirstHome.class);
        startActivity(firstHome);
    }
}
