package com.example.myfirstapp;

public class Debt {

    public String name;
    public Integer amount, min, rate;

    public Debt(String debtName, Integer debtAmount, Integer debtMin, Integer debtRate) {
        name = debtName;
        amount = debtAmount;
        min = debtMin;
        rate = debtRate;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setAmount(Integer newAmount) {
        amount = newAmount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setMin(Integer newMin) {
        min = newMin;
    }

    public Integer getMin() {
        return min;
    }

    public void setRate(Integer newRate) {
        rate = newRate;
    }

    public Integer getRate() {
        return rate;
    }
}
