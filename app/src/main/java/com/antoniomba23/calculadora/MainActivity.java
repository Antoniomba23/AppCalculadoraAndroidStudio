package com.antoniomba23.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private double firstNumber = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Números
        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(this::onNumberClick);
        }

        // Operadores
        findViewById(R.id.btnAdd).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.btnSub).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.btnMul).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.btnDiv).setOnClickListener(this::onOperatorClick);

        findViewById(R.id.btnEqual).setOnClickListener(this::onEqualClick);
        findViewById(R.id.btnClear).setOnClickListener(v -> clearAll());
    }

    private void onNumberClick(View v) {
        Button b = (Button) v;
        currentInput += b.getText().toString();
        tvDisplay.setText(currentInput);
    }

    private void onOperatorClick(View v) {
        Button b = (Button) v;
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = b.getText().toString();
            currentInput = "";
            isOperatorPressed = true;
        }
    }

    private void onEqualClick(View v) {
        if (isOperatorPressed && !currentInput.isEmpty()) {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "×": result = firstNumber * secondNumber; break;
                case "÷": result = secondNumber != 0 ? firstNumber / secondNumber : 0; break;
            }

            tvDisplay.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            isOperatorPressed = false;
        }
    }

    private void clearAll() {
        currentInput = "";
        firstNumber = 0;
        operator = "";
        isOperatorPressed = false;
        tvDisplay.setText("0");
    }
}
