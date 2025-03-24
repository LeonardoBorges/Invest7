package com.invest7.model.produtos;

import java.math.BigDecimal;

public class RendaFixa extends Produto {
    private double rentabilidadeBruta;
    private int riscoFinanceiro;
    private double investimentoMinimo;
    private String tipoProduto;
    private boolean isTaxable;
    private BigDecimal pessimistic;
    private BigDecimal average;
    private BigDecimal optimistic;
    private String percentPessimistic;
    private String percentAverage;
    private String percentOptimistic;

    public RendaFixa(String nome, double rentabilidadeBruta, int riscoFinanceiro,
                    double investimentoMinimo, String tipoProduto, boolean isTaxable) {
        super(nome);
        this.rentabilidadeBruta = rentabilidadeBruta;
        this.riscoFinanceiro = riscoFinanceiro;
        this.investimentoMinimo = investimentoMinimo;
        this.tipoProduto = tipoProduto;
        this.isTaxable = isTaxable;
    }

    // Getters and setters
    public double getRentabilidadeBruta() { return rentabilidadeBruta; }
    public int getRiscoFinanceiro() { return riscoFinanceiro; }
    public double getInvestimentoMinimo() { return investimentoMinimo; }
    public String getTipoProduto() { return tipoProduto; }
    public boolean isTaxable() { return isTaxable; }
    public BigDecimal getPessimistic() { return pessimistic; }
    public void setPessimistic(BigDecimal val) { this.pessimistic = val; }
    public BigDecimal getAverage() { return average; }
    public void setAverage(BigDecimal val) { this.average = val; }
    public BigDecimal getOptimistic() { return optimistic; }
    public void setOptimistic(BigDecimal val) { this.optimistic = val; }
    public String getPercentPessimistic() { return percentPessimistic; }
    public void setPercentPessimistic(String val) { this.percentPessimistic = val; }
    public String getPercentAverage() { return percentAverage; }
    public void setPercentAverage(String val) { this.percentAverage = val; }
    public String getPercentOptimistic() { return percentOptimistic; }
    public void setPercentOptimistic(String val) { this.percentOptimistic = val; }
}
