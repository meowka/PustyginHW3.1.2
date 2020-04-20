package com.example.hw312;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView resultTxt;
    TextView operationField;
    TextView resultCalc;
    Double operand = null;
    String lastOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTxt = findViewById(R.id.resultField);
        operationField = findViewById(R.id.operationField);
        resultCalc = findViewById(R.id.resultF);
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        resultCalc.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String number = resultCalc.getText().toString();

        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                resultCalc.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {

        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
                case "C":
                    resultTxt.setText("");
                    resultCalc.setText("");
                    operationField.setText("");
                    break;
                case "+/-":
                    operand *= -1;
                    break;
                case "%":
                    operand = (operand*number)/100;
                    break;
            }

        }
        resultTxt.setText(operand.toString().replace('.', ','));
        resultCalc.setText("");
    }

    public void onOperClick(View view) {

        Button button = (Button) view;
        switch (button.getText().toString()) {
            case "C":
                resultTxt.setText("");
                resultCalc.setText("");
                operationField.setText("");
                break;
            case "+/-":
                try {
                    operand = Double.parseDouble(resultTxt.getText().toString());
                } catch (NumberFormatException ex) {
                    operand *= -1;
                    operationField.setText("");
                    resultCalc.setText("");
                    resultTxt.setText(operand.toString().replace('.', ','));
                }
                break;
        }
    }

}
