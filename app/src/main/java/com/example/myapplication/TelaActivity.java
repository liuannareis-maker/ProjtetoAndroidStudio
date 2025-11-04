package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TelaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela);

        Button bntFrases = findViewById(R.id.bntFrases);
        bntFrases.setOnClickListener(v -> {
            Intent intent = new Intent(TelaActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        Button bntTela = findViewById(R.id.bntTela);
        bntTela.setOnClickListener(v -> {
            Intent intent = new Intent(TelaActivity.this, Tela2Activity.class);
            startActivity(intent);
        });
        Button bntVolt = findViewById(R.id.bntVolt);
        bntVolt.setOnClickListener(v -> {
            Intent intent = new Intent(TelaActivity.this, MainActivity.class);
            startActivity(intent);
    });
        Button bntConversor = findViewById(R.id.bntConversor);
        bntConversor.setOnClickListener(v->{
            Intent intent = new Intent(TelaActivity.this, ConversorActivity.class);
            startActivity(intent);

        });
        Button bntTmperatura = findViewById(R.id.bntTmperatura);
        bntTmperatura.setOnClickListener(v->{
            Intent intent = new Intent(TelaActivity.this, TemperaturaActivity.class);
            startActivity(intent);

        });
        Button bntCarro = findViewById(R.id.bntCarro);
        bntCarro.setOnClickListener(v->{
            Intent intent = new Intent(TelaActivity.this, CarroActivity.class);
            startActivity(intent);

        });
        Button bntCep = findViewById(R.id.bntCep);
        bntCep.setOnClickListener(v->{
            Intent intent = new Intent(TelaActivity.this,CepActivity.class);
            startActivity(intent);

        });
    }
}

