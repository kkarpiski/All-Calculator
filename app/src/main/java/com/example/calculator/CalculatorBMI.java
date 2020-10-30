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
import java.text.DecimalFormat;

import static android.R.layout.simple_spinner_dropdown_item;

public class CalculatorBMI extends AppCompatActivity implements View.OnClickListener {

    Spinner sp_wzrost, spinner_wzrost, spinner_waga;
    Button  btn_oblicz;
    String[] wzrost, wagaJednostka, wzrostJednostka;
    TextView tv_wynik_liczba, tv_wynik_opis;
    EditText et_waga, et_wiek;
    private double kg, m;
    private DecimalFormat TWO_DECIMAL_PLACES = new DecimalFormat(".##");

    CategoryBMI categoryBMI = new CategoryBMI();
    MetricFormula metricFormula;
    ImperialFormula imperialFormula;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_bmi);

        tv_wynik_liczba = findViewById(R.id.tv_wynik_liczba);
        tv_wynik_opis = findViewById(R.id.tv_wynik_opis);
        sp_wzrost = findViewById(R.id.sp_wzrost);
        spinner_wzrost = findViewById(R.id.spinner_wzrost);
        spinner_waga = findViewById(R.id.spinner_waga);
        btn_oblicz = findViewById(R.id.btn_oblicz);
        et_waga = findViewById(R.id.et_waga);
        et_wiek= findViewById(R.id.et_wiek);
        CategoryBMI categoryBMI = new CategoryBMI();


        spinnerWzrost();
        spinnerWzrostJednostka();
        spinnerWagaJednostka();
        porownianieWyniku();

        btn_oblicz.setOnClickListener(this);
    }

    private void porownianieWyniku() {

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

        kg = Double.parseDouble(et_waga.getText().toString());
        m = Double.parseDouble((sp_wzrost.getSelectedItem().toString()));

        metricFormula = new MetricFormula(kg, m);
        imperialFormula = new ImperialFormula(kg, m);

        tv_wynik_liczba.setText(String.valueOf(TWO_DECIMAL_PLACES.format(metricFormula.computeBMI(metricFormula.getInputKg(), metricFormula.getInputM()))));
        //showImpBMI.setText("In imperial formula: " + String.valueOf(TWO_DECIMAL_PLACES.format(imperialFormula.computeBMI(imperialFormula.getInputKg(), imperialFormula.getInputM()))));
        tv_wynik_opis.setText(CategoryBMI.getCategory(metricFormula.computeBMI(metricFormula.getInputKg(), metricFormula.getInputM())));

     /*
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
 */
    }
}
