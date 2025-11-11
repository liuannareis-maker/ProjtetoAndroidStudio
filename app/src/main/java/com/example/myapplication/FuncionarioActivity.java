package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Funcionario;

public class FuncionarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funcionario);

        EditText txtNome = findViewById(R.id.txtFuncionario);
        EditText txtSalarioBruto = findViewById(R.id.txtSalario);
        EditText txtImposto = findViewById(R.id.txtImposto);
        EditText txtPercentual = findViewById(R.id.txtPercentual);
        Button bntBuscarInfor = findViewById(R.id.bntBuscarInfor);
        TextView txtTotalizando = findViewById(R.id.txtTotalizando);

        Button bntSaindo3 = findViewById(R.id.bntSaindo3);
        bntSaindo3.setOnClickListener(v -> finish());


        bntBuscarInfor.setOnClickListener(v -> {
            try {
                String nome = txtNome.getText().toString();
                double salarioBruto = Double.parseDouble(txtSalarioBruto.getText().toString());
                double imposto = Double.parseDouble(txtImposto.getText().toString());
                double percentual = Double.parseDouble(txtPercentual.getText().toString());

                Funcionario f = new Funcionario(nome, salarioBruto, imposto);

                String antes = f.dadosFormatados();
                f.aumentarSalario(percentual);
                String depois = f.dadosFormatados();

                txtTotalizando.setText(antes + "\n\nApós aumento: " + depois);

            } catch (Exception e) {
                txtTotalizando.setText(" Verifique se todos os campos foram preenchidos corretamente!");
            }
        });
    }
}
