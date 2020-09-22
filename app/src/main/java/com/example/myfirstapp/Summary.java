package com.example.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        SharedPreferences store = getApplicationContext().getSharedPreferences("store", MODE_PRIVATE);

        // Display take home entered previously
        TextView takeHomeSummaryContainer = findViewById(R.id.takeHomeSummaryValue);
        int takeHomeSummaryValue = store.getInt("takehome", 0);
        takeHomeSummaryContainer.setText(String.format(getResources().getString(R.string.takeHomeSummary), takeHomeSummaryValue));

        // Display outgoings entered previously. If outgoings are greater than 50% of income suggest
        // user to cut costs otherwise display good message.
        TextView outgoingsSummaryContainer = findViewById(R.id.outgoingsSummaryValue);
        TextView outgoingsSummaryDescription = findViewById(R.id.outgoingsSummaryDescription);
        int outgoingsSummaryValue = store.getInt("outgoings", 0);
        outgoingsSummaryContainer.setText(String.format(getResources().getString(R.string.outgoingsSummary), outgoingsSummaryValue));

        int outgoingsPercentage = (int)(100.0f * outgoingsSummaryValue) / takeHomeSummaryValue;
        if (outgoingsPercentage > 50) {
            outgoingsSummaryDescription.setText(String.format(getResources().getString(R.string.outgoingsSummaryDescriptionNegative), outgoingsPercentage));
        } else {
            outgoingsSummaryDescription.setText(String.format(getResources().getString(R.string.outgoingsSummaryDescriptionPositive), outgoingsPercentage));
        }

        /* Display emergency fund entered previously. if user hasn't saved 6 months worth of their monthly
        outgoings suggest an increase in fund otherwise keep said fund in an easy access account. */
        TextView emergencyFundSummaryContainer = findViewById(R.id.emergencyFundSummaryValue);
        TextView emergencyFundSummaryDescription = findViewById(R.id.emergencyFundSummaryDescription);
        int emergencyFundSummaryValue = store.getInt("emergencyfund", 0);
        emergencyFundSummaryContainer.setText(String.valueOf(emergencyFundSummaryValue));

        int emergencyFundTarget = outgoingsSummaryValue * 6;
        if (emergencyFundSummaryValue > emergencyFundTarget) {
            emergencyFundSummaryDescription.setText(String.format(getResources().getString(R.string.emergencyFundReached), emergencyFundTarget));
        } else {
            emergencyFundSummaryDescription.setText(String.format(getResources().getString(R.string.emergencyFundLacking), emergencyFundTarget));
        }

        // Suggest to user to find out more about their workplace pension if not contributing max
        // otherwise tell user to maintain contributions
        TextView pensionSummaryDescription = findViewById(R.id.pensionSummaryDescription);
        boolean pensionMax = store.getBoolean("pensionMax", false);
        if (pensionMax) {
            pensionSummaryDescription.setText(getResources().getString(R.string.pensionSummaryGood));
        } else {
            pensionSummaryDescription.setText(getResources().getString(R.string.pensionSummaryBad));
        }

        // Get arraylist stored as string in SP and convert back to ArrayList for view
        Gson gson = new Gson();
        String json = store.getString("debtlist", "");
        Type type = new TypeToken<ArrayList<Debt>>(){}.getType();
        ArrayList<Debt> debtList = gson.fromJson(json, type);

        // Display debts previously entered
        DebtAdapter debtAdapter = new DebtAdapter(this, debtList);
        ListView debtListSummary = findViewById(R.id.debtListSummary);
        debtListSummary.setAdapter(debtAdapter);

        TextView debtSummaryDescription = findViewById(R.id.debtSummaryDescription);
        Button debtSummaryButton = findViewById(R.id.debtSummaryButton);

        // If user has debts provide access to debt advice activity
        assert debtList != null;
        if (debtList.size() > 0) {
            debtSummaryButton.setVisibility(View.VISIBLE);
            debtSummaryDescription.setText(getResources().getString(R.string.debtsPresentSummary));
        } else {
            debtSummaryButton.setVisibility(View.GONE);
            debtSummaryDescription.setText(getResources().getString(R.string.debtsAbsentSummary));
        }

        TextView firstHomeSummaryDescription = findViewById(R.id.firstHomeSummaryDescription);
        int firstHomeYears = store.getInt("firstHomeYears", -1);
        if (firstHomeYears < 0) {
            firstHomeSummaryDescription.setText(getResources().getString(R.string.noFirstHome));
        } else if (firstHomeYears > 6) {
            firstHomeSummaryDescription.setText(getResources().getString(R.string.longTermFirstHome, firstHomeYears));
        } else {
            firstHomeSummaryDescription.setText(getResources().getString(R.string.shortTermFirstHome, firstHomeYears));
        }
    }

    public void startDebtAdvice(View view) {

        Intent debtAdvice = new Intent(this, DebtAdvice.class);
        startActivity(debtAdvice);
    }

}