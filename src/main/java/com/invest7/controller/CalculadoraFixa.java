package com.invest7.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import com.invest7.dao.RendaFixaDAO;

import com.invest7.model.produtos.RendaFixa;


public class CalculadoraFixa {
    private final RendaFixaDAO dao = new RendaFixaDAO();

    public List<RendaFixa> simularInvestimentos(BigDecimal capitalInicial, 
                                              BigDecimal aporteMensal, 
                                              int meses) {
        List<RendaFixa> produtos = dao.buscarRendaFixa();
        
        for(RendaFixa produto : produtos) {
            BigDecimal taxa = BigDecimal.valueOf(produto.getRentabilidade_liquida());
            
            BigDecimal pessimista = calcularProjecao(
                capitalInicial, 
                aporteMensal, 
                meses, 
                taxa.multiply(BigDecimal.valueOf(0.9))
            );
            
            BigDecimal medio = calcularProjecao(
                capitalInicial, 
                aporteMensal, 
                meses, 
                taxa
            );
            
            BigDecimal otimista = calcularProjecao(
                capitalInicial, 
                aporteMensal, 
                meses, 
                taxa.multiply(BigDecimal.valueOf(1.1))
            );
/*
            produto.setPercentPessimistic(pessimista);
            produto.setValorMedio(medio);
            produto.setValorOtimista(otimista);

 */
        }
        
        return produtos;
    }

    private BigDecimal calcularProjecao(BigDecimal capitalInicial,
                                      BigDecimal aporteMensal,
                                      int meses,
                                      BigDecimal taxaMensal) {
        BigDecimal total = capitalInicial;
        for(int i = 0; i < meses; i++) {
            BigDecimal rendimento = total.multiply(taxaMensal);
            total = total.add(rendimento).add(aporteMensal);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

}
