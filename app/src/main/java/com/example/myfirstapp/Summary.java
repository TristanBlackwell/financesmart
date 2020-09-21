package com.example.myfirstapp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

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
    }
}