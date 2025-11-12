package com.example.myapplication;

public class Gerente extends Trabalhador {
    private float bonus;

    public Gerente(String nome, float salarioBase, float bonus) {
        super(nome, salarioBase);
        this.bonus = bonus;
    }

    @Override
    public float calcularSalario() {
        return salarioBase + bonus;
    }
}
