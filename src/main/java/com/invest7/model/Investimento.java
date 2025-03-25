package com.invest7.model;

import java.text.DecimalFormat;

public class Investimento {
    private String nome;
    private double valorInvestido;
    private int meses;
    private double aporte;
    private double preFix;
    private double posFix;
    private double ipcaSelic;
    private double valorBruto;
    private double valorLiquido;
    private double rentabilidade;

    /* - Nome - super
    * - Valor Investido - super
    * - meses - super
    * - aporte
    * - taxa de juros
    * - imposto de renda*/
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    public Investimento(String nome, double valorBruto, double valorLiquido, double rentabilidade) {
        this.nome = nome;
        this.valorBruto = valorBruto;
        this.valorLiquido = valorLiquido;
        this.rentabilidade = rentabilidade;
    }

    public Investimento(double valorInvestido, double aporte, int meses, double preFix) {
        this.valorInvestido = valorInvestido;
        this.aporte = aporte;
        this.meses = meses;
        this.preFix = preFix;
    }

    public Investimento(double valorInvestido, double aporte, double posFix, int meses) {
        this.valorInvestido = valorInvestido;
        this.aporte = aporte;
        this.posFix = posFix;
        this.meses = meses;
    }

    public Investimento(double valorInvestido,int meses, double aporte, double ipcaSelic) {
        this.valorInvestido = valorInvestido;
        this.meses = meses;
        this.aporte = aporte;
        this.ipcaSelic = ipcaSelic;
    }

    // Getters
    public String getNome() { return nome; }
    public double getValorBruto() { return valorBruto; }
    public double getValorLiquido() { return valorLiquido; }
    public double getRentabilidade() { return rentabilidade; }

    public double getAporte() {
        return aporte;
    }

    public void setAporte(double aporte) {
        this.aporte = aporte;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public double getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public double getPreFix() {
        return preFix;
    }

    public void setPreFix(double preFix) {
        this.preFix = preFix;
    }

    public double getPosFix() {
        return posFix;
    }

    public void setPosFix(double posFix) {
        this.posFix = posFix;
    }

    public double getIpcaSelic() {
        return ipcaSelic;
    }

    public void setIpcaSelic(double ipcaSelic) {
        this.ipcaSelic = ipcaSelic;
    }

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
