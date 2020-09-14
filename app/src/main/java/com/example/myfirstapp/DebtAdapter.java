package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DebtAdapter extends ArrayAdapter<Debt> {

    private ArrayList<Debt> debts;

    public DebtAdapter(Context context, ArrayList<Debt> debts) {
        super(context, 0, debts);
        this.debts = debts;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get info for next position
        Debt debt = getItem(position);
        // Check if an existing view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.debt, parent, false);
        }

        // Set text to name of object
        TextView debtName = convertView.findViewById(R.id.debtName);
        debtName.setText(debt.name);

        Button delDebtButton = convertView.findViewById(R.id.delDebtButton);
        delDebtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                debts.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
