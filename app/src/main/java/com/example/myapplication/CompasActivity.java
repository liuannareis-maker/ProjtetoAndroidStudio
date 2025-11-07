package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CompasActivity extends AppCompatActivity {

    EditText txtItem;
    Button bntAdicionar;
    Button bntRemover;
    ListView listCompras;

    Button bntSaindo;
    ArrayList<String> itensLista;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compras);

        txtItem = findViewById(R.id.txtItem);
        bntAdicionar = findViewById(R.id.bntAdicionar);
        bntRemover = findViewById(R.id.bntRemover);
        listCompras = findViewById(R.id.listCompras);
        bntSaindo = findViewById(R.id.bntSaindo);
        bntSaindo.setOnClickListener(v -> finish());

        itensLista = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itensLista
        );


        listCompras.setAdapter(adapter);

        bntAdicionar.setOnClickListener(v -> {
            String itemParaAdicionar = txtItem.getText().toString().trim();

            if (!itemParaAdicionar.isEmpty()) {
                itensLista.add(itemParaAdicionar.toUpperCase());
                adapter.notifyDataSetChanged();
                txtItem.setText("");
                Toast.makeText(this, itemParaAdicionar + " adicionado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Digite um item!", Toast.LENGTH_SHORT).show();
            }
        });

        bntRemover.setOnClickListener(v -> {
            if (!itensLista.isEmpty()) {
                String itemRemovido = itensLista.remove(itensLista.size() - 1);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, itemRemovido + " Item removido!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "A lista já está vazia.", Toast.LENGTH_SHORT).show();
            }
        });

        listCompras.setOnItemClickListener((parent, view, position, id) -> {
            String itemClicado = itensLista.get(position);
            Toast.makeText(this, "Você clicou em: " + itemClicado, Toast.LENGTH_SHORT).show();
        });

    }
}

