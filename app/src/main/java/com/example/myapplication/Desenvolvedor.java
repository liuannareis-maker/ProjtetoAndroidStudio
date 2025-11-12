package com.example.myapplication;


public class Desenvolvedor extends Trabalhador {
    private int horasExtras;

    public Desenvolvedor(String nome, float salarioBase, int horasExtras) {
        super(nome, salarioBase);
        this.horasExtras = horasExtras;
    }

    @Override
    public float calcularSalario() {
        // Calcula o valor da hora normal (220 horas mensais)
        float valorHora = salarioBase / 220f;

        // Hora extra vale 50% a mais
        float valorHoraExtra = valorHora * 1.5f;

        // Salário total = base + valor total das horas extras
        return salarioBase + (valorHoraExtra * horasExtras);

    }
    }

