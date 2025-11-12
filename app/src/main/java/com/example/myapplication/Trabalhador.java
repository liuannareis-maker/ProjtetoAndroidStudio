package com.example.myapplication;

public class Trabalhador
{
    protected String nome;
    protected float salarioBase;

    public Trabalhador (String nome, float salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public float calcularSalario() {
        return salarioBase;
    }

    public String getNome() {
        return nome;
    }
}
