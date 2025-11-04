package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CarroActivity extends AppCompatActivity {

    Carro carroAtual = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carro);

        EditText txtPlaca = findViewById(R.id.txtPlaca);
        Button bntBuscar = findViewById(R.id.bntBuscar);
        Button bntLigar = findViewById(R.id.bntLigar);
        Button bntDesligar = findViewById(R.id.bntDesligar);
        TextView txtExibir = findViewById(R.id.txtExibir);
        Button bntRetorno2 = findViewById(R.id.bntRetorno2);


        bntBuscar.setOnClickListener(v -> {
            String placaDigitada = txtPlaca.getText().toString().trim().toUpperCase();


            if (placaDigitada.isEmpty()) {
                Toast.makeText(this, "Informe a placa do veículo!", Toast.LENGTH_SHORT).show();
                return;
            }

            switch (placaDigitada) {
                case "1515":
                    carroAtual = new Carro("1515", "Honda", "Civic", "2.0 Flex", "Prata", 2020, "Sedan");
                    break;

                case "2020":
                    carroAtual = new Carro("2020", "Fiat", "Uno", "1.0 Fire", "Branco", 2018, "Hatch");
                    break;

                case "3030":
                    carroAtual = new Carro("3030", "Chevrolet", "Onix", "1.4 Turbo", "Preto", 2021, "Hatch");
                    break;

                case "6060":
                    carroAtual = new Carro("6060", "Toyota", "Corolla", "2.0 Flex", "Cinza", 2022, "Sedan");
                    break;

                case "9090":
                    carroAtual = new Carro("9090", "Volkswagen", "Gol", "1.6", "Vermelho", 2019, "Hatch");
                    break;

                default:
                    carroAtual = null;
                    break;

            }

            if (carroAtual != null) {
                txtExibir.setText(carroAtual.exibirInformacoes());

            } else {
                txtExibir.setText("❌ Nenhum carro encontrado com a placa: /n" + placaDigitada);
            }
        });

        bntLigar.setOnClickListener(v -> {
            if (carroAtual != null) {
                txtExibir.setText(carroAtual.ligarCarro());
                txtExibir.setText(carroAtual.exibirInformacoes());
            } else {
                Toast.makeText(this, "Busque um carro primeiro!", Toast.LENGTH_SHORT).show();
            }
        });

        bntDesligar.setOnClickListener(v -> {
            if (carroAtual != null) {
                txtExibir.setText(carroAtual.desligarCarro());
                txtExibir.setText(carroAtual.exibirInformacoes());
            } else {
                Toast.makeText(this, "Busque um carro primeiro!", Toast.LENGTH_SHORT).show();
            }
        });

        bntRetorno2.setOnClickListener(v -> finish());
    }
}
