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

    Spinner spinner_wzrost, spinner_waga;
    Button  btn_oblicz;
    String[] wzrost, wagaJednostka, wzrostJednostka;
    TextView tv_wynik_liczba, tv_wynik_opis, tv_wynik_Imp;
    EditText et_waga, et_wiek, et_wzrost;
    private double kg, m;
    private DecimalFormat TWO_DECIMAL_PLACES = new DecimalFormat(".##");

    CategoryBMI categoryBMI = new CategoryBMI();
    MetricFormula metricFormula;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_bmi);

        tv_wynik_liczba = findViewById(R.id.tv_wynik_liczba);
        tv_wynik_opis = findViewById(R.id.tv_wynik_opis);
        spinner_wzrost = findViewById(R.id.spinner_wzrost);
        spinner_waga = findViewById(R.id.spinner_waga);
        btn_oblicz = findViewById(R.id.btn_oblicz);
        et_waga = findViewById(R.id.et_waga);
        et_wiek= findViewById(R.id.et_wiek);
        et_wzrost = findViewById(R.id.et_wzrost);

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

    @Override
    public void onClick(View v) {

        kg = Double.parseDouble(et_waga.getText().toString());
        m = Double.parseDouble((et_wzrost.getText().toString()));

        metricFormula = new MetricFormula(kg, m);

        tv_wynik_liczba.setText(String.valueOf(TWO_DECIMAL_PLACES.format(metricFormula.computeBMI(metricFormula.getInputKg(),metricFormula.getInputM()))));
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
