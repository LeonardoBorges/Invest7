import java.math.BigDecimal;

import com.invest7.model.produtos.Produto;

public class RendaFixa extends Produto {
    private double rentabilidade_bruta;
    private double rentabilidade_liquida;
    private int risco_financeiro;
    private double investimento_minimo;
    private double imposto_renda_IR;
    private BigDecimal pessimistic;
    private BigDecimal average;
    private BigDecimal optimistic;
    private String percentPessimistic;
    private String percentAverage;
    private String percentOptimistic;
    private BigDecimal aporteMensal;
    private BigDecimal capital ;  
    // Default constructor
    public RendaFixa() {
        super();
    }
    
    // Full constructor
    public RendaFixa(BigDecimal capital, BigDecimal aporte, int prazo) {
        super(capital, prazo); // Add matching constructor in Produto
        this.aporteMensal = aporte;
    }
    public RendaFixa(double rentabilidade_bruta, double rentabilidade_liquida, 
                    int risco_financeiro, double investimento_minimo, 
                    double imposto_renda_IR, BigDecimal pessimistic, 
                    BigDecimal average, BigDecimal optimistic, 
                    String percentPessimistic, String percentAverage, 
                    String percentOptimistic) {
        super();
        this.rentabilidade_bruta = rentabilidade_bruta;
        this.rentabilidade_liquida = rentabilidade_liquida;
        this.risco_financeiro = risco_financeiro;
        this.investimento_minimo = investimento_minimo;
        this.imposto_renda_IR = imposto_renda_IR;
        this.pessimistic = pessimistic;
        this.average = average;
        this.optimistic = optimistic;
        this.percentPessimistic = percentPessimistic;
        this.percentAverage = percentAverage;
        this.percentOptimistic = percentOptimistic;
    }
    // return DAO
    public RendaFixa (String nome_produto, double rendabilidade_bruta, double rentabilidade_liquida,
         int risco_financeiro, double investimento_minimo, double imposto_renda_IR){
        super(nome_produto);
        this.rentabilidade_bruta = rendabilidade_bruta;
        this.rentabilidade_liquida = rentabilidade_liquida;
        this.risco_financeiro = risco_financeiro;
        this.investimento_minimo = investimento_minimo;
        this.imposto_renda_IR = imposto_renda_IR;
    }
                                                                                                                
    // Getters and setters for all properties
    public double getRentabilidade_bruta() {
        return rentabilidade_bruta;
    }
    
    public void setRentabilidade_bruta(double rentabilidade_bruta) {
        this.rentabilidade_bruta = rentabilidade_bruta;
    }
    
    public double getRentabilidade_liquida() {
        return rentabilidade_liquida;
    }
    
    public void setRentabilidade_liquida(double rentabilidade_liquida) {
        this.rentabilidade_liquida = rentabilidade_liquida;
    }
    
    public double getRisco_financeiro() {
        return risco_financeiro;
    }
    
    public void setRisco_financeiro(double risco_financeiro) {
        this.risco_financeiro = risco_financeiro;
    }
    
    public double getInvestimento_minimo() {
        return investimento_minimo;
    }
    
    public void setInvestimento_minimo(double investimento_minimo) {
        this.investimento_minimo = investimento_minimo;
    }
    
    public double getImposto_renda_IR() {
        return imposto_renda_IR;
    }
    
    public void setImposto_renda_IR(double imposto_renda_IR) {
        this.imposto_renda_IR = imposto_renda_IR;
    }
    
    public BigDecimal getPessimistic() {
        return pessimistic;
    }
    
    public void setPessimistic(BigDecimal pessimistic) {
        this.pessimistic = pessimistic;
    }
    
    public BigDecimal getAverage() {
        return average;
    }
    
    public void setAverage(BigDecimal average) {
        this.average = average;
    }
    
    public BigDecimal getOptimistic() {
        return optimistic;
    }
    
    public void setOptimistic(BigDecimal optimistic) {
        this.optimistic = optimistic;
    }
    
    public String getPercentPessimistic() {
        return percentPessimistic;
    }
    
    public void setPercentPessimistic(String percentPessimistic) {
        this.percentPessimistic = percentPessimistic;
    }
    
    public String getPercentAverage() {
        return percentAverage;
    }
    
    public void setPercentAverage(String percentAverage) {
        this.percentAverage = percentAverage;
    }
    
    public String getPercentOptimistic() {
        return percentOptimistic;
    }
    
    public void setPercentOptimistic(String percentOptimistic) {
        this.percentOptimistic = percentOptimistic;
    }
    public double getAporteMensal() {
        return aporteMensal;
    }
    public void setAporteMensal(double aporteMensal) {
        this.aporteMensal = aporteMensal;
    }

}