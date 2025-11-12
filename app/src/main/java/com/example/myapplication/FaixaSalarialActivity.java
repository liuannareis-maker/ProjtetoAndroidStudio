package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FaixaSalarialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faixasalarial);

        EditText txtNomeFun = findViewById(R.id.txtNomeFun);
        EditText txtSalarioFun = findViewById(R.id.txtSalarioFun);
        EditText txtExtra1 = findViewById(R.id.txtExtra1);
        Spinner spiSalario = findViewById(R.id.spiSalario);
        Button btnCalcularSal = findViewById(R.id.btnCalcularSal);
        TextView txtResTo = findViewById(R.id.txtResTo);
        Button bntExit = findViewById(R.id.bntExit);

        // Opções no spinner
        String[] tipos = {"Gerente", "Desenvolvedor", "Estagiário"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        spiSalario.setAdapter(adapter);

        btnCalcularSal.setOnClickListener(v -> {
            try {
                String nome = txtNomeFun.getText().toString();
                float salarioBase = Float.parseFloat(txtSalarioFun.getText().toString());
                String tipo = spiSalario.getSelectedItem().toString();
                float salarioFinal = 0;

                Trabalhador func;

                switch (tipo) {
                    case "Gerente":
                        float bonus = Float.parseFloat(txtExtra1.getText().toString());
                        func = new Gerente(nome, salarioBase, bonus);
                        break;
                    case "Desenvolvedor":
                        int horas = Integer.parseInt(txtExtra1.getText().toString());
                        func = new Desenvolvedor(nome, salarioBase, horas);
                        break;
                    default:
                        func = new Trabalhador(nome, salarioBase);
                        break;
                }

                salarioFinal = func.calcularSalario();
                txtResTo.setText(String.format("Funcionário: %s\nSalário Final: R$ %.2f", func.getNome(), salarioFinal));


            } catch (Exception e) {
                txtResTo.setText("⚠️ Preencha todos os campos corretamente!");
            }
        });

        bntExit.setOnClickListener(v -> finish());
    }
}