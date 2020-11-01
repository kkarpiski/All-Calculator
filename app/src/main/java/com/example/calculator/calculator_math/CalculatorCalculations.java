package com.example.calculator.calculator_math;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import com.example.calculator.R;

public class CalculatorCalculations extends AppCompatActivity {

    private int[] numericButtons = {R.id.zero_btn, R.id.one_btn, R.id.two_btn, R.id.three_btn, R.id.four_btn, R.id.five_btn, R.id.six_btn, R.id.seven_btn, R.id.eight_btn, R.id.nine_btn};
    private int[] operatorButtons = {R.id.clear_everything_btn, R.id.clear_btn, R.id.backspace_btn, R.id.divide_btn, R.id.multipy_btn, R.id.addition_btn, R.id.subtract_btn, R.id.percent_btn, R.id.dot_btn, R.id.equal_btn};
    private TextView result_id;
    private boolean stateError, lastNumeric, lastDot;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_math);

        result_id = findViewById(R.id.result_id);

        setNumericOnClickListener();
        setOperatorOnClickListener();
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the current state is Error do not append the operator
                // If the last input is number only, append the operator
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    result_id.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;    // Zresetuj flagę DOT
                }
            }
        };

        // Assign the listener to all the operator buttons
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }

        // Decimal point
        findViewById(R.id.dot_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    result_id.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });

        // Clear button
        findViewById(R.id.clear_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result_id.setText("");  // Clear the screen
                // Reset all the states and flags
                lastNumeric = false;
                stateError = false;
                lastDot = false;
            }
        });

        // Equal button
        findViewById(R.id.equal_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });

    }
    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just append/set the text of clicked button
                Button button = (Button) v;
                if (stateError) {
                    // If current state is Error, replace the error message
                    result_id.setText(button.getText());
                    stateError = false;
                } else {
                    // If not, already there is a valid expression so append to it
                    result_id.append(button.getText());
                }
                // Set the flag
                lastNumeric = true;
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }

    }
    private void onEqual() {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
        if (lastNumeric && !stateError) {
            // Read the expression
            String txt = result_id.getText().toString();
            // Create an Expression (A class from exp4j library)
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                // Calculate the result and display
                double result = expression.evaluate();
                result_id.setText(Double.toString(result));
                lastDot = true; // Result contains a dot
            } catch (ArithmeticException ex) {
                // Display an error message
                result_id.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}
