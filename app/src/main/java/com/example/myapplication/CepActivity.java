package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CepActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cep);

        EditText txtCep = findViewById(R.id.txtCep);
        Button bntBuscarCep = findViewById(R.id.bntBuscarCep);
        TextView txtInfo = findViewById(R.id.txtInfo);

        bntBuscarCep.setOnClickListener(v -> {

            String cep = txtCep.getText().toString();

            if (!cep.isEmpty()) {
                buscarEndereco(cep,txtInfo );
            } else {
                txtInfo.setText("Digite um CEP válido.");

            }

        });

    }

    private void buscarEndereco(String cep, TextView  txtInfo){
        new Thread(() -> {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            try {
                URL apiUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder resultado = new StringBuilder();
                String linha;

                while ((linha = reader.readLine()) != null) {
                    resultado.append(linha);
                }

                reader.close();

                JSONObject jsonObject = new JSONObject(resultado.toString());

                String logradouro = jsonObject.optString("logradouro", ""); // optString evita exceções
                String bairro = jsonObject.optString("bairro", "");
                String localidade = jsonObject.optString("localidade", "");
                String uf = jsonObject.optString("uf", "");

                String endereco = logradouro + ", " + bairro + "\n" + localidade + " - " + uf;

                runOnUiThread(() -> txtInfo.setText(endereco));

            } catch (Exception e) {
                runOnUiThread(() -> txtInfo.setText("Erro ao buscar endereço."));
                e.printStackTrace();
            }
        }).start();
    }
}