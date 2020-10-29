package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorBMI extends AppCompatActivity {

    Spinner sp_wzrost;
    Button btn_oblicz;
    String[] wzrost;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_bmw);

        sp_wzrost = findViewById(R.id.sp_wzrost);
        btn_oblicz = findViewById(R.id.btn_oblicz);
        popuateSpinnerWzrost();
        btn_oblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wzrost = sp_wzrost.getSelectedItem().toString();
            }
        });


    }

    private void popuateSpinnerWzrost() {
        ArrayAdapter<String> wzrostAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.sp_wzrost));
        wzrostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_wzrost.setAdapter(wzrostAdapter);
    }
}
