package org.example.model;

import java.text.DecimalFormat;

public class Investimento {
    private String nome;
    private double valorBruto;
    private double valorLiquido;
    private double rentabilidade;

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    public Investimento(String nome, double valorBruto, double valorLiquido, double rentabilidade) {
        this.nome = nome;
        this.valorBruto = valorBruto;
        this.valorLiquido = valorLiquido;
        this.rentabilidade = rentabilidade;
    }

    // Getters
    public String getNome() { return nome; }
    public double getValorBruto() { return valorBruto; }
    public double getValorLiquido() { return valorLiquido; }
    public double getRentabilidade() { return rentabilidade; }

    // Método para formatar valores para exibição
    public String getValorBrutoFormatado() {
        return "R$ " + df.format(valorBruto);
    }

    public String getValorLiquidoFormatado() {
        return "R$ " + df.format(valorLiquido);
    }

    public String getRentabilidadeFormatada() {
        return new DecimalFormat("0.00%").format(rentabilidade);
    }
}
