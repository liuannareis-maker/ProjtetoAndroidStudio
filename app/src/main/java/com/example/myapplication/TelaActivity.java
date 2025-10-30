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
}
}
