package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConversorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversor);

        EditText txtValor = findViewById(R.id.txtValor);
        Spinner spMoeda = findViewById(R.id.spMoeda);
        TextView txtResultado = findViewById(R.id.txtResultado);
        Button bntConverter = findViewById(R.id.bntConverter);
        Button bntVltr = findViewById(R.id.bntVltr);


        final double taxaDolar = 0.20;
        final double taxaEuro = 0.18;


        String[] moedas = {"Dólar", "Euro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                moedas
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMoeda.setAdapter(adapter);

        bntConverter.setOnClickListener(v -> {
            String valorStr = txtValor.getText().toString();

            if (valorStr.isEmpty()) {
                Toast.makeText(ConversorActivity.this, "Digite um valor em Reais", Toast.LENGTH_SHORT).show();
                return;
            }

            double valor = Double.parseDouble(valorStr);
            double resultado = 0;
            String moedaSelecionada = spMoeda.getSelectedItem().toString();

            if (moedaSelecionada.equals("Dólar")) {
                resultado = valor * taxaDolar;
            } else if (moedaSelecionada.equals("Euro")) {
                resultado = valor * taxaEuro;
            }

            txtResultado.setText(String.format("Resultado: %.2f %s", resultado, moedaSelecionada));
        });
        bntVltr.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}

