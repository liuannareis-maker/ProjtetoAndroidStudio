package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Novo extends AppCompatActivity {

    DatabaseHelper db; // Instância do DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo);

        // Instancia o banco
        db = new DatabaseHelper(this);

        // Componentes da tela
        TextView txtAdicionarU = findViewById(R.id.txtAdicionarU);
        TextView txtAdicionarS = findViewById(R.id.txtAdicionarS);
        Button bntSalvarU = findViewById(R.id.bntSalvarU);
        Button bntFechar2 = findViewById(R.id.bntFechar2);

        // Botão fechar
        bntFechar2.setOnClickListener(v -> finish());

        // Clique no Salvar
        bntSalvarU.setOnClickListener(v -> {

            String username = txtAdicionarU.getText().toString().trim();
            String password = txtAdicionarS.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha usuário e senha!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = db.insertUser(username, password);

            if (inserted) {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                // Limpa os campos depois de salvar
                txtAdicionarU.setText("");
                txtAdicionarS.setText("");

            } else {
                Toast.makeText(this, "Erro! Usuário já existe.", Toast.LENGTH_LONG).show();
            }
        });
    }
}

