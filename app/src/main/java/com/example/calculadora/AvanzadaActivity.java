package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AvanzadaActivity extends AppCompatActivity {

    private String num1 = "";
    private String num2 = "";
    private String operacion = "";
    private TextView tvOutputCalculadora;
    private Calculadora calculadora;

    private boolean hayPunto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avanzada);

        tvOutputCalculadora = findViewById(R.id.tvOutputCalculadora);

        //Crea el listener de los botones de números
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button boton = (Button) view;

                if (boton.getText().toString().equals(".")) {
                    if (!hayPunto) {
                        hayPunto = true;
                    } else {
                        return;
                    }
                } else {
                    hayPunto = false;
                }

                if (operacion.isEmpty()) {
                    num1 = num1 + boton.getText().toString();
                    tvOutputCalculadora.setText(num1);
                } else {
                    num2 = num2 + boton.getText().toString();
                    tvOutputCalculadora.setText(num2);
                }
            }
        };

        //Asigna los listeners
        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnPunto};
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }

        //Crea el listener de los botones de operaciones
        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button boton = (Button) v;

                if (!tvOutputCalculadora.getText().toString().isEmpty() && num1.isEmpty()) {
                    num1 = tvOutputCalculadora.getText().toString();
                }

                if (!num1.isEmpty()) {
                    operacion = boton.getText().toString();
                    tvOutputCalculadora.setText(operacion);
                }
            }
        };

        //Asigna los listeners
        int[] operationIds = {R.id.btnMas, R.id.btnMenos, R.id.btnMultiplicar, R.id.btnDivision};
        for (int id : operationIds) {
            findViewById(id).setOnClickListener(operationListener);
        }

        //Inicializamos la calculadora
        calculadora = new Calculadora();

        //Crea el listener del calulo de la operacion
        View.OnClickListener equalsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num2.isEmpty()) {
                    double o1 = Double.parseDouble(num1);
                    double o2 = Double.parseDouble(num2);

                    calculadora.setOper1(o1);
                    calculadora.setOper2(o2);
                    calculadora.setOperacion(Calculadora.OPERACION.fromSymbol(operacion));
                    double result = calculadora.opera();

                    tvOutputCalculadora.setText(String.valueOf(result));
                    num1 = "";
                    num2 = "";
                    operacion = "";
                }
            }
        };

        //Asigna el listener al boton de igual
        findViewById(R.id.btnIgual).setOnClickListener(equalsListener);

        //Crea el listener del boton de borrar
        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = "";
                num2 = "";
                operacion = "";
                tvOutputCalculadora.setText("");
            }
        };

        //Asigna el listener al boton de borrar
        findViewById(R.id.btnBorrar).setOnClickListener(clearListener);
    }
}
