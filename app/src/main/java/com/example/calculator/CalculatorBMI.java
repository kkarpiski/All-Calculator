package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.math.BigDecimal;

import static android.R.layout.simple_spinner_dropdown_item;

public class CalculatorBMI extends AppCompatActivity implements View.OnClickListener {

    Spinner sp_wzrost;
    Spinner spinner_wzrost;
    Spinner spinner_waga;
    Button  btn_oblicz;
    String[] wzrost;
    String[] wagaJednostka;
    String[] wzrostJednostka;
    EditText et_waga;
    TextView et_wynik;
    EditText et_wiek;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_bmi);

        et_wynik = findViewById(R.id.tv_wynik);
        sp_wzrost = findViewById(R.id.sp_wzrost);
        btn_oblicz = findViewById(R.id.btn_oblicz);
        et_waga = findViewById(R.id.et_waga);

        spinnerWzrost();
        spinnerWzrostJednostka();
        spinnerWagaJednostka();

        btn_oblicz.setOnClickListener(this);
    }

    private void spinnerWagaJednostka() {
        ArrayAdapter<String> wagaJednostkaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.wagaJednostka));
        wagaJednostkaAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinner_waga.setAdapter(wagaJednostkaAdapter);
    }

    private void spinnerWzrostJednostka() {
        ArrayAdapter<String> wzrostJednostkaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.wzrostJednostka));
        wzrostJednostkaAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinner_wzrost.setAdapter(wzrostJednostkaAdapter);
    }


    private void spinnerWzrost() {
        ArrayAdapter<String> wzrostAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.sp_wzrost));
        wzrostAdapter.setDropDownViewResource(simple_spinner_dropdown_item);
        sp_wzrost.setAdapter(wzrostAdapter);
    }

    @Override
    public void onClick(View v) {
        String sWzrost = sp_wzrost.getSelectedItem().toString();
        String sWaga = et_waga.getText().toString().trim();

        double waga = Double.parseDouble(sWaga);
        double wzrost = Double.parseDouble(sWzrost);
        double wzrostWmetrach = wzrost / 100;

        double a = waga;
        double b = wzrostWmetrach * wzrostWmetrach;
        double c = a / b;
        double wynik = round(c, 1, BigDecimal.ROUND_HALF_UP);
        String sbmi = String.valueOf(wynik);

        et_wynik.setText(sbmi);

    }
    public static double round(double unrounded, int precision, int roundingMode)
    {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }
}
