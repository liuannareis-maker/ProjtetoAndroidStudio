package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        TextView txtFrase = findViewById(R.id.txtFrase);
        Button bntMenu = findViewById(R.id.bntMenu);
        ImageView imgFrase = findViewById(R.id.imgFrase);
        Button bntVoltar = findViewById(R.id.bntVoltar);
        Button bntSair = findViewById(R.id.bntSair);



// Abrir vizualizações

        String[] frases = {
                "Acredite em mim, haha!",
                "Nunca desista!",
                "Eu sou capaz!",
                " Eu sou inevitável!",
                "Que a força esteja com você!",
                "Tem muito que aprender, meu jovem padawan..."
        };

       // imagens selecionadas
        int[] imagens = {
                R.drawable.florverde,
                R.drawable.coracao,
                R.drawable.florverde,
                R.drawable.galaxy,
                R.drawable.coracao,
                R.drawable.borboleta
        };

        bntMenu.setOnClickListener(v -> {
            Random random = new Random();
            int numero = random.nextInt(frases.length);
            txtFrase.setText(frases[numero]);
            imgFrase.setImageResource(imagens[numero]);
        });
        bntVoltar.setOnClickListener(v -> finish());
        bntSair.setOnClickListener(v -> {
            finishAffinity();
        });

    }
}
