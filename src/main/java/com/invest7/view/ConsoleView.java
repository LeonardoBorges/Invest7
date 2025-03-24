package com.invest7.view;

import com.invest7.model.produtos.RendaFixa;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ConsoleView {
    private final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public void exibirSimulacao(List<RendaFixa> produtos, String idSimulacao) {
        System.out.println("\n********** Renda Fixa **********");
        System.out.println("=== Investimento ===");
        System.out.println("Simulação ID: " + idSimulacao);
        
        exibirCategoria(produtos, "POUPANCA", "Poupança");
        exibirCategoria(produtos, "CDB", "CDB/RDB");
        exibirCategoria(produtos, "LCI", "LCI/LCA");
        exibirCategoria(produtos, "TESOURO", "Tesouro Direto");
        
        exibirResumoOutrasAplicacoes(produtos);
    }

    private void exibirCategoria(List<RendaFixa> produtos, String tipo, String titulo) {
        System.out.println("\n********** " + titulo + " **********");
        produtos.stream()
            .filter(p -> p.getTipoProduto().equalsIgnoreCase(tipo))
            .forEach(this::exibirProduto);
    }

    private void exibirProduto(RendaFixa produto) {
        System.out.println("\n" + produto.getNome());
        System.out.println("Valor Investido: " + nf.format(produto.getValorInvestido()));
        System.out.println("Rendimento Bruto: " + nf.format(produto.getRendimentoBruto()));
        
        if (produto.isTaxable()) {
            System.out.println("Imposto de Renda (" + produto.getImpostoIR().divide(produto.getRendimentoBruto(), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)) + "%): " 
                + nf.format(produto.getImpostoIR()));
        }
        
        System.out.println("Rendimento Líquido: " + nf.format(produto.getRendimentoLiquido()));
        System.out.println("Valor Total: " + nf.format(produto.getValorTotal()));
        System.out.println("Lucro: " + produto.getPercentualLucro());
        System.out.println("---------------------------------");
    }

    private void exibirResumoOutrasAplicacoes(List<RendaFixa> produtos) {
        System.out.println("\n**** Outras Aplicações ****");
        produtos.stream()
            .filter(p -> !List.of("POUPANCA", "CDB", "LCI", "TESOURO").contains(p.getTipoProduto()))
            .forEach(p -> System.out.println(p.getNome() + ": " + nf.format(p.getValorTotal())));
    }
}
