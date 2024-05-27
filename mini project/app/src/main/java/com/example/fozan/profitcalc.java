package com.example.fozan;

public class profitcalc {
    private double qt,cp,sp;

    public static double calcprofit(double qt,double cp, double sp){
        return (qt*(sp-cp));
    }
}
