package com.example;

class Funcionario {
    private String nome;
    private double salarioBruto;
    private double imposto;

    public Funcionario(String nome, double salarioBruto, double imposto) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
        this.imposto = imposto;
    }

    // Calcula o salário líquido
    public double salarioLiquido() {
        return salarioBruto - imposto;
    }

    // Aumenta o salário em um percentual
    public void aumentarSalario(double percentual) {
        salarioBruto += salarioBruto * (percentual / 100);
    }

    // Retorna os dados formatados
    public String dadosFormatados() {
        return "Nome: " + nome +
                "\nSalário Bruto: R$ " + String.format("%.2f", salarioBruto) +
                "\nImposto: R$ " + String.format("%.2f", imposto) +
                "\nSalário Líquido: R$ " + String.format("%.2f", salarioLiquido());
    }
}
