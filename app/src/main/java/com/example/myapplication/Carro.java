package com.example.myapplication;

public class Carro {
    private String placa;
    private String marca;
    private String nome;
    private String motor;
    private String cor;
    private int ano;
    private String modelo;
    private boolean ligado;

    // 🔹 Construtor
    public Carro(String placa, String marca, String nome, String motor, String cor, int ano, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.nome = nome;
        this.motor = motor;
        this.cor = cor;
        this.ano = ano;
        this.modelo = modelo;
        this.ligado = false;
    }

    // 🔹 Exibir informações do carro
    public String exibirInformacoes() {
        String status = ligado ? "✅ Ligado" : "❌ Desligado";
        return "Placa: " + placa + "\n" +
                "Marca: " + marca + "\n" +
                "Nome: " + nome + "\n" +
                "Motor: " + motor + "\n" +
                "Cor: " + cor + "\n" +
                "Ano: " + ano + "\n" +
                "Modelo: " + modelo + "\n" +
                "Status: " + status;
    }

    // 🔹 Ligar o carro
    public String ligarCarro() {
        if (!ligado) {
            ligado = true;
            return "🚗 O carro " + marca + " " + nome + " está ligado!";
        } else {
            return "⚠️ O carro já está ligado!";
        }
    }

    // 🔹 Desligar o carro
    public String desligarCarro() {
        if (ligado) {
            ligado = false;
            return "🛑 O carro " + marca + " " + nome + " está desligado!";
        } else {
            return "⚠️ O carro já está desligado!";
        }
    }
}
