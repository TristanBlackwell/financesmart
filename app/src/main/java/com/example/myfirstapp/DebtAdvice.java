package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DebtAdvice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_advice);

        ArrayList<Debt> debtList;
        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = store.getString("debtlist", "");
        Type type = new TypeToken<ArrayList<Debt>>(){}.getType();
        debtList = gson.fromJson(json, type);

        TextView avalancheDescription = findViewById(R.id.avalancheDescription);
        int takehome = store.getInt("takehome", 0);
        int outgoings = store.getInt("outgoings", 0);
        int afterBills = takehome - outgoings;

        int minPayments = 0;
        int largestMin = Integer.MIN_VALUE;
        int smallestMin = Integer.MAX_VALUE;
        String greatestDebt = "";
        String leastDebt = "";
        assert debtList != null;
        // calculate minimum payments that must be met
        for (int i = 0; i < debtList.size(); i++) {
            minPayments += debtList.get(i).getMin();
            // Store most expensive debts name and minimum payment
            if (debtList.get(i).getMin() > largestMin) {
                largestMin = debtList.get(i).getMin();
                greatestDebt = debtList.get(i).getName();
            } else if (debtList.get(i).getMin() < smallestMin) { // Store least expensive debts name and minimum payment
                smallestMin = debtList.get(i).getMin();
                leastDebt = debtList.get(i).getName();
            }
        }
        // The amount that can be contributed after min payments to wiping off debt.
        int leftOver = afterBills - minPayments;

        avalancheDescription.setText(getResources().getString(R.string.avalancheDescription, afterBills, leftOver, greatestDebt));

        TextView snowballDescription = findViewById(R.id.snowballDescription);
        snowballDescription.setText(getResources().getString(R.string.snowballDescription, afterBills, leftOver, leftOver, leastDebt));

    }
}