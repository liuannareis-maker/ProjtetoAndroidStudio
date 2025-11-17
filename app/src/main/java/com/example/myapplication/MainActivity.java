package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referenciando os elementos da interface
        EditText txtUsuario = findViewById(R.id.txtUsuario);
        EditText txtSenha = findViewById(R.id.txtSenha);
        Button bntTeste = findViewById(R.id.bntTeste);
        Button bntFechar = findViewById(R.id.bntFechar);

        // Ação ao clicar no botão
        bntTeste.setOnClickListener(v -> {
            String usuario = txtUsuario.getText().toString();
            String senha = txtSenha.getText().toString();

            if (usuario.equals("") && senha.equals("")) {
                // Abrir nova visualização
                Intent intent = new Intent(MainActivity.this, TelaActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Dados incorretos!\nInsira os dados novamente.", Toast.LENGTH_SHORT).show();
            }
        });
        bntFechar.setOnClickListener(v -> {
            finishAffinity();
        });

    }
}
