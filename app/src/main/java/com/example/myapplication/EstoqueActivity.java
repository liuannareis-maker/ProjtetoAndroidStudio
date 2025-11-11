package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EstoqueActivity extends AppCompatActivity {

    ArrayList<String> produtos;
    ArrayAdapter<String> adapter;
    int posicaoSelecionada = -1;

    double totalPreco = 0;
    int totalQuantidade = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.estoque);

        EditText editNome = findViewById(R.id.editNome);
        EditText txtPreco = findViewById(R.id.txtPreco);
        EditText editQuant = findViewById(R.id.editQuant);
        TextView textTotal=findViewById(R.id.textTotal);
        Button butaddi = findViewById(R.id.butaddi);
        Button bntRemo = findViewById(R.id.bntRemo);
        ListView listEstoq = findViewById(R.id.listEstoq);
        Button bntVolt2 = findViewById(R.id.bntVolt2);
        bntVolt2.setOnClickListener(v -> finish());


        produtos = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, produtos);
        listEstoq.setAdapter(adapter);
        // Permite selecionar itens
        listEstoq.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Quando clicar em um item da lista, marca a posição
        listEstoq.setOnItemClickListener((parent, view, position, id) -> {
            posicaoSelecionada = position;
            listEstoq.setItemChecked(position, true);
        });

        //Botão adicionar
        butaddi.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String precoStr = txtPreco.getText().toString().trim();
            String quantStr = editQuant.getText().toString().trim();
            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite um produto!", Toast.LENGTH_SHORT).show();
            } try{

                double preco = Double.parseDouble(precoStr);
                int quantidade = Integer.parseInt(quantStr);
                String item = nome + " - Preço: R$ " + preco + " - Qtd: " + quantidade;
                produtos.add(item);
                adapter.notifyDataSetChanged();

                totalPreco += preco * quantidade;
                totalQuantidade += quantidade;

                textTotal.setText("Total Qtd: " + totalQuantidade + " | Total R$: " + totalPreco);

                editNome.setText("");
                txtPreco.setText("");
                editQuant.setText("");
                Toast.makeText(this, "Produto adicionado!", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Digite números válidos!", Toast.LENGTH_SHORT).show();
            }
        });
        bntRemo.setOnClickListener(v -> {
            if (posicaoSelecionada != -1) {
                produtos.remove(posicaoSelecionada);
                adapter.notifyDataSetChanged();
                listEstoq.clearChoices();
                posicaoSelecionada = -1;
                Toast.makeText(this, "Produto removido!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Selecione um produto para remover!", Toast.LENGTH_SHORT).show();
            }
        });

        bntRemo.setOnClickListener(v -> {
            if (posicaoSelecionada != -1) {
                String item = produtos.get(posicaoSelecionada);

                // Extrai quantidade e preço do item antes de remover
                try {
                    String[] partes = item.split(" - ");
                    double preco = Double.parseDouble(partes[1].replace("Preço: R$ ", "").trim());
                    int quantidade = Integer.parseInt(partes[2].replace("Qtd: ", "").trim());

                    totalPreco -= preco * quantidade;
                    totalQuantidade -= quantidade;
                    textTotal.setText("Total Qtd: " + totalQuantidade + " | Total R$: " + totalPreco);
                } catch (Exception ignored) {}

                produtos.remove(posicaoSelecionada);
                adapter.notifyDataSetChanged();
                listEstoq.clearChoices();
                posicaoSelecionada = -1;
                Toast.makeText(this, "Produto removido!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Selecione um produto para remover!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


