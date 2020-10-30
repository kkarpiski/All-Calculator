package com.example.calculator;

public class ImperialFormula {

    private double et_waga;
    private double et_wzrost;

    private final double KG_TO_KG = 1;
    private final double CM_TO_M = 0.01;

    public ImperialFormula(double et_waga, double et_wzrost){
        this.et_waga = et_waga;
        this.et_wzrost = et_wzrost;
    }

    public double getEt_waga() {
        return et_waga;
    }

    public double getEt_wzrost() {
        return et_wzrost;
    }

    public double computeBMI(double kg, double m){

        double result = 0;
        double kgs = Math.round((kg * KG_TO_KG));
        double in = Math.round((m * CM_TO_M)* 100);

        result = (kg / (Math.pow((in/100), 2))) * 703;

        return result;
    }
}
