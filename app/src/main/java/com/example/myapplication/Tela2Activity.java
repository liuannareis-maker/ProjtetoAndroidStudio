package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Tela2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);


    Button bntVoltr = findViewById(R.id.bntVoltr);
        bntVoltr.setOnClickListener(v -> {
        Intent intent = new Intent(Tela2Activity.this, TelaActivity.class);
        startActivity(intent);
    });
}
}
