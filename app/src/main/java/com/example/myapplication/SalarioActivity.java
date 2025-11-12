package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

public abstract class SalarioActivity extends AppCompatActivity {
    private String nome;
    private double salarioBruto;
    private double imposto;

    public SalarioActivity(String nome, double salarioBruto, double imposto) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
        this.imposto = imposto;
    }

    public double salarioLiquido() {
        return salarioBruto - imposto;
    }

    public void aumentarSalario(double percentual) {
        salarioBruto += salarioBruto * percentual / 100.0;
    }

    public String dadosFormatados() {
        return "Funcionário: " + nome + ", R$ " + String.format("%.2f", salarioLiquido());
    }

    public abstract float calcularSalario();
}
