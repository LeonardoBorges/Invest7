package com.invest7.controller;

import com.invest7.dao.RendaFixaDAO;
import com.invest7.model.produtos.RendaFixa;
import com.invest7.util.TaxService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalculadoraFixa {
    private final RendaFixaDAO dao = new RendaFixaDAO();
    private final BigDecimal SELIC_ANUAL = new BigDecimal("13.25");
    private final BigDecimal CDI_ANUAL = new BigDecimal("13.15");

    public List<RendaFixa> simularInvestimento(BigDecimal valorInicial, int meses, String tipoPeriodo) {
        List<RendaFixa> produtos = dao.buscarTodosProdutos();
        int dias = meses * 30;
        
        for (RendaFixa produto : produtos) {
            BigDecimal taxaAnual = calcularTaxaAnual(produto);
            BigDecimal taxaPeriodo = converterTaxaPeriodo(taxaAnual, tipoPeriodo);
            
            BigDecimal valorInvestido = valorInicial;
            BigDecimal rendimentoBruto = valorInvestido.multiply(taxaPeriodo)
                .setScale(2, RoundingMode.HALF_UP);
            
            BigDecimal impostoIR = TaxService.calcularTaxaIR(dias, produto.isTaxable())
                .multiply(rendimentoBruto)
                .setScale(2, RoundingMode.HALF_UP);
            
            BigDecimal rendimentoLiquido = rendimentoBruto.subtract(impostoIR);
            BigDecimal valorTotal = valorInvestido.add(rendimentoLiquido);
            
            BigDecimal lucro = valorTotal.subtract(valorInvestido);
            String percentLucro = lucro.divide(valorInvestido, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP) + "%";

            produto.setSimulationResults(
                valorInvestido,
                rendimentoBruto,
                impostoIR,
                rendimentoLiquido,
                valorTotal,
                percentLucro
            );
        }
        return produtos;
    }

    private BigDecimal calcularTaxaAnual(RendaFixa produto) {
        switch (produto.getTaxaBase()) {
            case "SELIC":
                return SELIC_ANUAL.multiply(BigDecimal.valueOf(produto.getPorcentagemTaxa() / 100));
            case "CDI":
                return CDI_ANUAL.multiply(BigDecimal.valueOf(produto.getPorcentagemTaxa() / 100));
            case "FIXA":
                return BigDecimal.valueOf(produto.getRentabilidadeBruta());
            default:
                return BigDecimal.ZERO;
        }
    }

    private BigDecimal converterTaxaPeriodo(BigDecimal taxaAnual, String tipoPeriodo) {
        if ("MENSAL".equalsIgnoreCase(tipoPeriodo)) {
            return taxaAnual.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        }
        return taxaAnual.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
    }
}
