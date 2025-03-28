package com.invest7.view;

import com.invest7.model.produtos.RendaFixa;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ConsoleView {
    private final NumberFormat currencyFormatter;
    private final NumberFormat percentFormatter;

    public ConsoleView() {
        Locale brazil = new Locale("pt", "BR");
        this.currencyFormatter = NumberFormat.getCurrencyInstance(brazil);
        this.percentFormatter = NumberFormat.getPercentInstance(brazil);
        this.percentFormatter.setMinimumFractionDigits(2);
        this.percentFormatter.setMaximumFractionDigits(2);
    }

    public void exibirSimulacao(List<RendaFixa> produtos, String idSimulacao,
                                BigDecimal valorInicial, BigDecimal aporteMensal, int meses) {
        System.out.println("\n********** Renda Fixa **********");
        System.out.println("=== Investimento ===");
        System.out.println("Simulação ID: " + idSimulacao);
        System.out.println("Valor da Aplicação: " + formatCurrency(valorInicial));
        System.out.println("Aporte Mensal: " + formatCurrency(aporteMensal));
        System.out.println("Vencimento: " + meses + " meses");
        System.out.println("Dias: " + (meses * 30));
        System.out.println("Taxa SELIC: 13,25% ao ano");
        System.out.println("Taxa CDI: 13,15% ao ano");

        System.out.println("\n############# Simulação " + idSimulacao + " ############");

        exibirCategoria(produtos, "POUPANCA", "Poupança");
        exibirCategoria(produtos, "CDB", "CDB/RDB");
        exibirCategoria(produtos, "LCI", "LCI/LCA");
        exibirCategoria(produtos, "LCA", "LCI/LCA");
        exibirCategoria(produtos, "TESOURO", "Tesouro Direto");
        exibirResumoOutrasAplicacoes(produtos);

        exibirRecomendacao(produtos);
    }

    private void exibirCategoria(List<RendaFixa> produtos, String tipo, String titulo) {
        List<RendaFixa> filtrados = produtos.stream()
                .filter(p -> p.getTipoProduto().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());

        if (!filtrados.isEmpty()) {
            System.out.println("\n********** " + titulo + " **********");
            filtrados.forEach(this::exibirProduto);
        }
    }

    private void exibirProduto(RendaFixa produto) {
        System.out.println("\n" + produto.getNome());
        System.out.println("Valor Total Investido: " + formatCurrency(produto.getTotalInvestido()));
        System.out.println("Rendimento Bruto: " + formatCurrency(produto.getRendimentoBruto()));

        if (produto.isTaxable()) {
            BigDecimal irPercent = produto.getRendimentoBruto().compareTo(BigDecimal.ZERO) > 0 ?
                    produto.getImpostoIR()
                            .divide(produto.getRendimentoBruto(), 4, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100)) :
                    BigDecimal.ZERO;

            System.out.println("Imposto de Renda (" + formatPercent(irPercent) + "): " +
                    formatCurrency(produto.getImpostoIR()));
            System.out.println("Rendimento Líquido: " + formatCurrency(produto.getRendimentoLiquido()));
        } else {
            System.out.println("Rendimento Líquido (isento de IR): " +
                    formatCurrency(produto.getRendimentoLiquido()));
        }

        System.out.println("Valor Total Líquido: " + formatCurrency(produto.getValorTotal()));
        System.out.println("Lucro: " + formatCurrency(produto.getRendimentoLiquido()) +
                " (" + produto.getPercentualLucro() + ")");
        System.out.println("---------------------------------");
    }

    private void exibirResumoOutrasAplicacoes(List<RendaFixa> produtos) {
        List<RendaFixa> outras = produtos.stream()
                .filter(p -> !List.of("POUPANCA", "CDB", "LCI", "LCA", "TESOURO")
                        .contains(p.getTipoProduto()))
                .collect(Collectors.toList());

        if (!outras.isEmpty()) {
            System.out.println("\n**** Outras Aplicações (Resumo) ****");
            outras.forEach(p -> System.out.println(p.getNome() + ": " +
                    formatCurrency(p.getValorTotal())));
        }
    }

    private void exibirRecomendacao(List<RendaFixa> produtos) {
        produtos.stream()
                .max(Comparator.comparing(RendaFixa::getRendimentoLiquido))
                .ifPresent(best -> {
                    System.out.println("\n*****************************************************************");
                    System.out.println("Recomendação: " + best.getNome() +
                            " por oferecer o maior retorno líquido de " +
                            formatCurrency(best.getRendimentoLiquido()));
                });
    }

    private String formatCurrency(BigDecimal value) {
        return currencyFormatter.format(value);
    }

    private String formatPercent(BigDecimal value) {
        return percentFormatter.format(value.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
    }
}