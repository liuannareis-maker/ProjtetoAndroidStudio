package com.example.myapplication;

public class Estagiario {
    private String nome;
    private float salarioBase;

    public Estagiario(String nome, float salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public float calcularSalario() {
        return salarioBase;
    }
}

