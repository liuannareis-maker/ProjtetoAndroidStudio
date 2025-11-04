package com.example.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


    public class TemperaturaActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.temperatura);

            Spinner spGraus = findViewById(R.id.spGraus);
            EditText txtValores = findViewById(R.id.txtValores);
            Button bntConver = findViewById(R.id.bntConver);
            TextView txtresultados = findViewById(R.id.txtresultados);
            Button bntRetornar = findViewById(R.id.bntRetornar);

            String[] opcoes = {
                    "Celsius → Fahrenheit",
                    "Fahrenheit → Celsius",
                    "Celsius → Kelvin",
                    "Kelvin → Celsius",
                    "Fahrenheit → Kelvin",
                    "Kelvin → Fahrenheit"
            };

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    opcoes
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spGraus.setAdapter(adapter);

            bntConver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String valorTexto = txtValores.getText().toString().trim();
                    if (valorTexto.isEmpty()) {
                        Toast.makeText(TemperaturaActivity.this, "Digite um valor!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double valor;
                    try {
                        valor = Double.parseDouble(valorTexto);
                    } catch (NumberFormatException e) {
                        Toast.makeText(TemperaturaActivity.this, "Valor inválido!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String opcao = spGraus.getSelectedItem().toString();
                    double resultado;
                    String saida;

                    switch (opcao) {
                        case "Celsius → Fahrenheit":
                            resultado = (valor * 9.0 / 5.0) + 32.0;
                            saida = String.format("%.2f °C = %.2f °F", valor, resultado);
                            break;

                        case "Fahrenheit → Celsius":
                            resultado = (valor - 32.0) * 5.0 / 9.0;
                            saida = String.format("%.2f °F = %.2f °C", valor, resultado);
                            break;

                        case "Celsius → Kelvin":
                            resultado = valor + 273.15;
                            saida = String.format("%.2f °C = %.2f K", valor, resultado);
                            break;

                        case "Kelvin → Celsius":
                            if (valor < 0) {
                                Toast.makeText(TemperaturaActivity.this, "Kelvin não pode ser negativo!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            resultado = valor - 273.15;
                            saida = String.format("%.2f K = %.2f °C", valor, resultado);
                            break;

                        case "Fahrenheit → Kelvin":
                            resultado = (valor - 32.0) * 5.0 / 9.0 + 273.15;
                            saida = String.format("%.2f °F = %.2f K", valor, resultado);
                            break;

                        case "Kelvin → Fahrenheit":
                            if (valor < 0) {
                                Toast.makeText(TemperaturaActivity.this, "Kelvin não pode ser negativo!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            resultado = (valor - 273.15) * 9.0 / 5.0 + 32.0;
                            saida = String.format("%.2f K = %.2f °F", valor, resultado);
                            break;

                        default:
                            saida = "Opção desconhecida.";
                            break;
                    }

                    txtresultados.setText(saida);
                }
            });

            bntRetornar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }