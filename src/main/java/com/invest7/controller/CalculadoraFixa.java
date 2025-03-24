package com.invest7.controller;

import com.invest7.dao.RendaFixaDAO;
import com.invest7.model.produtos.RendaFixa;
import com.invest7.util.TaxService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculadoraFixa {
    private final RendaFixaDAO dao = new RendaFixaDAO();

    public List<RendaFixa> simularInvestimentos(BigDecimal capitalInicial, 
                                              BigDecimal aporteMensal, 
                                              int meses) {
        List<RendaFixa> produtos = dao.buscarRendaFixa();
        int dias = meses * 30; // Approximation
        
        for (RendaFixa produto : produtos) {
            BigDecimal taxaAnual = BigDecimal.valueOf(produto.getRentabilidadeBruta())
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
            
            BigDecimal taxaMensalBruta = BigDecimal.ONE.add(taxaAnual)
                .pow(1/12) // Convert annual to monthly rate
                .subtract(BigDecimal.ONE);

            BigDecimal taxaIR = TaxService.calcularTaxaIR(dias, produto.isTaxable());
            
            // Calculate scenarios
            produto.setPessimistic(calcularCenario(
                capitalInicial, aporteMensal, meses, taxaMensalBruta, taxaIR, 0.9
            ));
            
            produto.setAverage(calcularCenario(
                capitalInicial, aporteMensal, meses, taxaMensalBruta, taxaIR, 1.0
            ));
            
            produto.setOptimistic(calcularCenario(
                capitalInicial, aporteMensal, meses, taxaMensalBruta, taxaIR, 1.1
            ));
            
            calcularPorcentagens(produto, capitalInicial, aporteMensal, meses);
        }
        return produtos;
    }

    private BigDecimal calcularCenario(BigDecimal capitalInicial, BigDecimal aporteMensal,
                                      int meses, BigDecimal taxaMensalBruta,
                                      BigDecimal taxaIR, double multiplicador) {
        BigDecimal taxa = taxaMensalBruta.multiply(BigDecimal.valueOf(multiplicador));
        BigDecimal total = capitalInicial;
        
        for (int i = 0; i < meses; i++) {
            BigDecimal rendimentoBruto = total.multiply(taxa);
            BigDecimal imposto = rendimentoBruto.multiply(taxaIR);
            total = total.add(rendimentoBruto.subtract(imposto)).add(aporteMensal);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    private void calcularPorcentagens(RendaFixa produto, BigDecimal capitalInicial,
                                     BigDecimal aporteMensal, int meses) {
        BigDecimal totalInvestido = capitalInicial.add(
            aporteMensal.multiply(BigDecimal.valueOf(meses))
        );
        
        produto.setPercentPessimistic(formatarPorcentagem(
            produto.getPessimistic(), totalInvestido));
        produto.setPercentAverage(formatarPorcentagem(
            produto.getAverage(), totalInvestido));
        produto.setPercentOptimistic(formatarPorcentagem(
            produto.getOptimistic(), totalInvestido));
    }

    private String formatarPorcentagem(BigDecimal valorFinal, BigDecimal totalInvestido) {
        BigDecimal diferenca = valorFinal.subtract(totalInvestido);
        BigDecimal porcentagem = diferenca.divide(totalInvestido, 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100))
            .setScale(1, RoundingMode.HALF_UP);
        return (porcentagem.compareTo(BigDecimal.ZERO) > 0 ? "+" : "") 
               + porcentagem + "%";
    }
}
