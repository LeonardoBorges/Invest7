package com.invest7.controller;

import com.invest7.model.produtos.Investimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CalculadoraInvestimentos {

    // Códigos de cores ANSI para terminal
    public static final String RESET = "\033[0m";
    public static final String VERDE = "\033[1;32m";
    public static final String AZUL = "\033[1;34m";
    public static final String BRANCO = "\033[1;37m";
    public static final String VERMELHO = "\033[1;31m";
    public static final String AMARELO = "\033[1;33m";
    public static final String ROXO = "\033[1;35m";
    public static final String CIANO = "\033[1;36m";

    public ArrayList<Investimento> calcularTodosInvestimentos(double valorInicial, double aporteMensal, int meses) {
        ArrayList<Investimento> resultados = new ArrayList<>();

        // Adicionando na ordem da sequência de cores: Azul, Branco, Vermelho, Verde
        resultados.add(calcularTesouroPrefixado(valorInicial, aporteMensal, meses));       // Azul
        resultados.add(calcularTesouroIPCA(valorInicial, aporteMensal, meses));           // Branco
        resultados.add(calcularCDB(valorInicial, aporteMensal, meses));                   // Vermelho
        resultados.add(calcularFundoDI(valorInicial, aporteMensal, meses));               // Verde
        resultados.add(calcularLCILCA(valorInicial, aporteMensal, meses));                // Azul (repetindo sequência)
        resultados.add(calcularPoupanca(valorInicial, aporteMensal, meses));             // Branco (repetindo sequência)
        resultados.add(calcularCRI(valorInicial, aporteMensal, meses));                  // Roxo
        resultados.add(calcularCRA(valorInicial, aporteMensal, meses));                  // Amarelo

        // Ordenar do maior para o menor rendimento líquido
        Collections.sort(resultados, Comparator.comparing(Investimento::getValorLiquido).reversed());

        return resultados;
    }

    private Investimento calcularPoupanca(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = BRANCO + "Poupança" + RESET;  // Branco
        double taxaMensal = 0.005; // 0,5% ao mês (TR + 0,1% a.m.)
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal, 0); // Isenta de IR
    }

    private Investimento calcularLCILCA(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = AZUL + "LCI/LCA" + RESET;  // Azul
        double taxaAnual = 0.085; // 8,5% ao ano
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal, 0); // Isentos de IR
    }

    private Investimento calcularFundoDI(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = VERDE + "Fundo DI" + RESET;  // Verde
        double percentualCDI = 1.0; // 100% do CDI
        double taxaAnual = 0.10 * percentualCDI; // Supondo CDI a 10% a.a.
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        double imposto = calcularImpostoRenda(valorFinal - valorInicial, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal - imposto, imposto);
    }

    private Investimento calcularCDB(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = VERMELHO + "CDB" + RESET;  // Vermelho
        double percentualCDI = 1.1; // 110% do CDI
        double taxaAnual = 0.10 * percentualCDI; // CDI a 10% a.a.
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        double imposto = calcularImpostoRenda(valorFinal - valorInicial, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal - imposto, imposto);
    }

    private Investimento calcularTesouroIPCA(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = BRANCO + "Tesouro IPCA+" + RESET;  // Branco
        double ipcaAnual = 0.045; // IPCA 4,5% a.a.
        double jurosReal = 0.05; // +5% a.a.
        double taxaAnual = (1 + ipcaAnual) * (1 + jurosReal) - 1;
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        double imposto = calcularImpostoTesouro(valorFinal - valorInicial, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal - imposto, imposto);
    }

    private Investimento calcularTesouroPrefixado(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = AZUL + "Tesouro Prefixado" + RESET;  // Azul
        double taxaAnual = 0.10; // 10% ao ano
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        double imposto = calcularImpostoTesouro(valorFinal - valorInicial, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal - imposto, imposto);
    }

    private Investimento calcularCRI(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = ROXO + "CRI" + RESET;  // Roxo
        double taxaAnual = 0.09; // 9% ao ano (normalmente um pouco acima do LCI/LCA)
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal, 0); // Isento de IR
    }

    private Investimento calcularCRA(double valorInicial, double aporteMensal, int meses) {
        String nomeColorido = AMARELO + "CRA" + RESET;  // Amarelo
        double taxaAnual = 0.092; // 9,2% ao ano (normalmente um pouco acima do CRI)
        double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
        double valorFinal = calcularJurosCompostosComAportes(valorInicial, aporteMensal, taxaMensal, meses);
        return new Investimento(nomeColorido, valorFinal, valorFinal, 0); // Isento de IR
    }

    // Métodos auxiliares (mantidos iguais)
    private double calcularJurosCompostosComAportes(double inicial, double aporte, double taxa, int periodos) {
        double valor = inicial;
        for (int i = 0; i < periodos; i++) {
            valor = (valor + aporte) * (1 + taxa);
        }
        return valor;
    }

    private double calcularImpostoRenda(double rendimento, int meses) {
        if (meses <= 6) return rendimento * 0.225;
        if (meses <= 12) return rendimento * 0.20;
        if (meses <= 24) return rendimento * 0.175;
        return rendimento * 0.15;
    }

    private double calcularImpostoTesouro(double rendimento, int meses) {
        if (meses <= 6) return rendimento * 0.225;
        if (meses <= 12) return rendimento * 0.20;
        if (meses <= 24) return rendimento * 0.175;
        if (meses <= 720) return rendimento * 0.15;
        return 0;
    }
}