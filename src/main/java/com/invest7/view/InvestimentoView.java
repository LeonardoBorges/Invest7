package com.invest7.view;

import com.invest7.model.produtos.Investimento;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class InvestimentoView {
    static Scanner scanner = new Scanner(System.in);

    // Códigos de cores ANSI
    private static final String RESET = "\u001B[0m";
    private static final String VERDE = "\u001B[32m";
    private static final String AZUL = "\u001B[34m";
    private static final String AMARELO = "\u001B[33m";
    private static final String CIANO = "\u001B[36m";
    private static final String ROXO = "\u001B[35m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String NEGRITO = "\u001B[1m";


    public void mostrarMenu() {
        System.out.println(ROXO + NEGRITO + "================================================");
        System.out.println("|" + AZUL + "      CALCULADORA DE INVESTIMENTOS       " + ROXO + "|");
        System.out.println("================================================" + RESET);
    }

   /* public double lerInvestimentoInicial() {
        System.out.print("\n" + AMARELO + "> " + VERDE + "Investimento inicial (R$): " + RESET);
        return scanner.nextDouble();
    }

    public int lerPeriodoMeses() {
        System.out.print(AMARELO + "> " + CIANO + "Período (meses): " + RESET);
        return scanner.nextInt();
    }

    public double lerAporteMensal() {
        System.out.print(AMARELO + "> " + AZUL + "Aporte mensal (R$): " + RESET);
        return scanner.nextDouble();
    }

    */

    public void mostrarResultados(List<Investimento> investimentos) {
        DecimalFormat df = new DecimalFormat("#,##0.00");

        // Linha de cabeçalho
        String headerLine = ROXO + "+-------------------+------------------+------------------+-------------------+" + RESET;
        System.out.println("\n" + headerLine);

        // Cabeçalho
        System.out.printf(ROXO + "| " + RESET + "%-17s " +
                        ROXO + "| " + RESET + "%16s " +
                        ROXO + "| " + RESET + "%16s " +
                        ROXO + "| " + RESET + "%17s " +
                        ROXO + "|" + RESET + "\n",
                AZUL + NEGRITO + "INVESTIMENTO" + RESET,
                AZUL + NEGRITO + "VALOR BRUTO" + RESET,
                AZUL + NEGRITO + "I.R." + RESET,
                AZUL + NEGRITO + "VALOR LÍQUIDO" + RESET);

        System.out.println(headerLine);

        // Corpo da tabela
        for (Investimento inv : investimentos) {
            System.out.printf(ROXO + "| " + RESET + "%-17s " +
                            ROXO + "| " + RESET + "%16s " +
                            ROXO + "| " + RESET + "%16s " +
                            ROXO + "| " + RESET + "%17s " +
                            ROXO + "|" + RESET + "\n",
                    CIANO + inv.getNome() + RESET,
                    AMARELO + df.format(inv.getValorBruto()) + RESET,
                    VERMELHO + "-" + df.format(inv.getValorBruto() - inv.getValorLiquido()) + RESET,
                    VERDE + NEGRITO + df.format(inv.getValorLiquido()) + RESET);
        }

        // Rodapé
        System.out.println(headerLine);
    }

    public void mostrarErro(String mensagem) {
        System.out.println("\n" + VERMELHO + NEGRITO + "[ERRO] " + RESET + mensagem + "\n");
    }

    public void fecharScanner() {
        scanner.close();
    }
}