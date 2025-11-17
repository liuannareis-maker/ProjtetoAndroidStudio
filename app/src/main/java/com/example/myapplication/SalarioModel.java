package com.example.myapplication;

public class SalarioModel {
    private String nome;
    private double salarioBruto;
    private double imposto;

    public SalarioModel(String nome, double salarioBruto, double imposto) {
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
        return "Funcionário: " + nome +
                "\nSalário Líquido: R$ " + String.format("%.2f", salarioLiquido());
    }
}

