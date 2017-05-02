package com.kevinponce.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView operation;
    private String pedingOpteration = "";
    private Double operand1 = null;
    private static final String STATE_PENDING_OPERATION = "pending_operation";
    private static final String STATE_OPERAND1 = "operand1";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pedingOpteration);
        outState.putDouble(STATE_OPERAND1, operand1);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        pedingOpteration = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operation.setText(pedingOpteration);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        operation = (TextView) findViewById(R.id.operation);

        Button zero = (Button) findViewById(R.id.zero);
        Button one = (Button) findViewById(R.id.one);
        Button two = (Button) findViewById(R.id.two);
        Button three = (Button) findViewById(R.id.three);
        Button four = (Button) findViewById(R.id.four);
        Button five = (Button) findViewById(R.id.five);
        Button six = (Button) findViewById(R.id.six);
        Button seven = (Button) findViewById(R.id.seven);
        Button eight = (Button) findViewById(R.id.eight);
        Button nine = (Button) findViewById(R.id.nine);
        Button decemail = (Button) findViewById(R.id.decemail);

        Button delete = (Button) findViewById(R.id.del);
        Button equal = (Button) findViewById(R.id.equal);
        Button plus = (Button) findViewById(R.id.plus);
        Button sub = (Button) findViewById(R.id.sub);
        Button mult = (Button) findViewById(R.id.mult);
        Button divide = (Button) findViewById(R.id.divide);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };

        zero.setOnClickListener(numberListener);
        one.setOnClickListener(numberListener);
        two.setOnClickListener(numberListener);
        three.setOnClickListener(numberListener);
        four.setOnClickListener(numberListener);
        zero.setOnClickListener(numberListener);
        five.setOnClickListener(numberListener);
        six.setOnClickListener(numberListener);
        seven.setOnClickListener(numberListener);
        eight.setOnClickListener(numberListener);
        nine.setOnClickListener(numberListener);
        decemail.setOnClickListener(numberListener);

        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();

                try {
                    peformOperation(Double.valueOf(value), op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pedingOpteration = op;
                operation.setText(op);
            }
        };

        plus.setOnClickListener(operationListener);
        sub.setOnClickListener(operationListener);
        divide.setOnClickListener(operationListener);
        mult.setOnClickListener(operationListener);
        equal.setOnClickListener(operationListener);

        View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                if(value.length() > 1) {
                    value = value.substring(0, value.length() -1);
                } else {
                    value = "";
                }
                newNumber.setText(value);
            }
        };

        delete.setOnClickListener(deleteListener);
    }

    private void peformOperation(Double value, String op) {
        if(null == operand1) {
            operand1 = value;
        } else {
            if(pedingOpteration.equals("=")) {
                pedingOpteration = op;
            }

            switch (pedingOpteration) {
                case "=":
                    operand1 = value;
                    break;
                case "+":
                    operand1 += value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "/":
                    if(value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
            }
        }

        newNumber.setText("");
        result.setText(operand1.toString());
    }
}
