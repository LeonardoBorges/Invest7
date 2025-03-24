package com.invest7.view;

import com.invest7.model.produtos.RendaFixa;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ConsoleView {
    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    
    public void mostrarResultados(List<RendaFixa> resultados) {
        System.out.println("\n=== RESULTADOS DA SIMULAÇÃO ===");
        
        for (RendaFixa produto : resultados) {
            System.out.println("\n" + produto.getNome());
            System.out.println("Tipo: " + produto.getTipoProduto());
            System.out.println("Risco: " + produto.getRiscoFinanceiro() + "/5");
            System.out.println("Investimento mínimo: " + nf.format(produto.getInvestimentoMinimo()));
            
            System.out.println("\nPessimista: " + nf.format(produto.getPessimistic())
                + " (" + produto.getPercentPessimistic() + ")");
            System.out.println("Médio:    " + nf.format(produto.getAverage())
                + " (" + produto.getPercentAverage() + ")");
            System.out.println("Otimista: " + nf.format(produto.getOptimistic())
                + " (" + produto.getPercentOptimistic() + ")");
            
            System.out.println("---------------------------------");
        }
    }
}
