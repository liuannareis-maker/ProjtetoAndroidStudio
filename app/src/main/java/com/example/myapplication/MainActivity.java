package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> insets);

        // Inicializa o banco
        myDb = new DatabaseHelper(this);

        // INSERIR O ADMIN APENAS UMA VEZ (NÃO REPETE)
        if (!myDb.checkUser("admin", "123")) {
            myDb.insertUser("admin", "123");
        }

        EditText txtUsuario = findViewById(R.id.txtUsuario);
        EditText txtSenha = findViewById(R.id.txtSenha);
        Button bntTeste = findViewById(R.id.bntTeste);
        Button bntFechar = findViewById(R.id.bntFechar);
        TextView txtAdd = findViewById(R.id.txtAdd);

        // Botão para abrir a tela Novo
        txtAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Novo.class);
            startActivity(intent);
        });

        // Login
        bntTeste.setOnClickListener(v -> {
            String usuario = txtUsuario.getText().toString().trim();
            String senha = txtSenha.getText().toString().trim();

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha usuário e senha!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (myDb.checkUser(usuario, senha)) {
                Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, TelaActivity.class));
            } else {
                Toast.makeText(this, "Dados incorretos!", Toast.LENGTH_SHORT).show();
            }
        });

        bntFechar.setOnClickListener(v -> finishAffinity());
    }
}
