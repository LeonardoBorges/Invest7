package com.invest7.view.menus.simulacao;

import com.invest7.controller.CalculadoraVariavel;
//import com.invest7.controller.InvestimentoController;
import com.invest7.controller.CalculadoraInvestimentos;
import com.invest7.model.produtos.Acoes;
import com.invest7.model.produtos.Fiis;
import com.invest7.view.InvestimentoView;


import java.util.List;
import java.util.Scanner;


public class MenuSimulacaoCompleta {


    public static <InvestimentoController> void simulacaoCompleta() {
        Scanner sc = new Scanner(System.in);
        double capital = 0.0, aporteMensal = 0.0, precoCota = 0.0,
                dividendoPorCota = 0.0, precoCompra = 0.0, precoVenda = 0.0;


        int prazo = 0, quantidadeCotas = 0, quantidade = 0, reinvestir = 0;
        boolean digitoCerto = false;


        System.out.println("-----------TELA DE SIMULACAO COMPLETA--------");


        while (!digitoCerto) {
            System.out.println("1- Digite um Capital inicial: ");
            if (sc.hasNextDouble()) {
                capital = sc.nextDouble();
                digitoCerto = true;
            } else {
                System.out.println("Valor Incorreto, digite novamente...");
                sc.next();
            }
        }

        digitoCerto = false;
        while (!digitoCerto) {
            System.out.println("1- Digite Quantidade de cotas: ");
            if (sc.hasNextDouble()) {
                quantidadeCotas = sc.nextInt();
                digitoCerto = true;
            } else {
                System.out.println("Valor Incorreto, digite novamente...");
                sc.next();
            }
        }


        digitoCerto = false;
        while (!digitoCerto) {
            System.out.println("2- Digite um valor para aporte mensal: ");
            if (sc.hasNextDouble()) {
                aporteMensal = sc.nextDouble();
                digitoCerto = true;
            } else {
                System.out.println("Valor Incorreto, digite novamente...");
                sc.next();
            }
        }


        digitoCerto = false;
        while (!digitoCerto) {
            System.out.println("3- Digite um prazo para a simulacao: ");
            if (sc.hasNextInt()) {
                prazo = sc.nextInt();
                digitoCerto = true;
            } else {
                System.out.println("Valor Incorreto, digite novamente...");
                sc.next();
            }
        }


        digitoCerto = false;
        while (!digitoCerto) {
            System.out.println("5- Deseja reinvestir os dividendos?\n1-sim | 2-não: ");
            if (sc.hasNextInt()) {
                reinvestir = sc.nextInt();
                digitoCerto = true;
            } else {
                System.out.println("Valor Incorreto, digite novamente...");
                sc.next();
            }
        }


        //InvestimentoView view = new InvestimentoView();
        //InvestimentoController rendaSimulados = new InvestimentoController();
        //Inicia a aplicação
        //controller.iniciar();

        /*CalculadoraVariavel calculadoraV = new CalculadoraVariavel();
        List<Fiis> fiis = calculadoraV.simularFundoImobiliario(new Fiis(aporteMensal,
                prazo, quantidadeCotas));

        System.out.println();

        System.out.println("///// Simulação de fiis /////");
        for (Fiis resultados : fiis) {
            System.out.println("nome" + resultados.getNome()
                    + "Saldo cotas" + resultados.getSaldoCotas()
                    + "Saldo Dividendos" + resultados.getSaldoDividendos());


        }*/


        CalculadoraVariavel calculadoraV = new CalculadoraVariavel();
        List<Fiis> fiis = calculadoraV.simularFundoImobiliario(new Fiis(aporteMensal, prazo, quantidadeCotas));

// Cabeçalho da simulação
        System.out.println("\n===== SIMULAÇÃO DE FIIs =====");
        System.out.printf("%-10s %15s %18s%n", "FII", "Saldo Cotas", "Saldo Dividendos");
        System.out.println("--------------------------------------------");

// Corpo dos resultados
        for (Fiis resultados : fiis) {
            System.out.printf("%-10s R$ %,12.2f R$ %,15.2f%n",
                    resultados.getNome(),
                    resultados.getSaldoCotas(),
                    resultados.getSaldoDividendos());
        }

// Rodapé
        System.out.println("╚════════════════════════════════════════════╝");

// Resumo estatístico (opcional)
        double totalCotas = fiis.stream().mapToDouble(Fiis::getSaldoCotas).sum();
        double totalDividendos = fiis.stream().mapToDouble(Fiis::getSaldoDividendos).sum();

        System.out.println("--------------------------------------------");
        System.out.printf("TOTAL: R$ %,12.2f (Cotas) + R$ %,15.2f (Dividendos) = R$ %,15.2f%n%n",
                fiis.stream().mapToDouble(Fiis::getSaldoCotas).sum(),
                fiis.stream().mapToDouble(Fiis::getSaldoDividendos).sum(),
                fiis.stream().mapToDouble(f -> f.getSaldoCotas() + f.getSaldoDividendos()).sum());


        System.out.println();

        System.out.println("///// Simulação de ações /////");
        List<Acoes> acoes = calculadoraV.simularAcao(capital, prazo);
        for (Acoes resultados : acoes) {
            if (resultados.getSaldoFinal() > 0) {
                System.out.println("nome" + resultados.getNome() + "Valor investido" + capital + "Quantidade de acoes: " + resultados.getQtdAcoes() + "lucro: R$ " + resultados.getSaldoFinal());
            } else {
                System.out.println("nome" + resultados.getNome() + "Valor investido" + capital + "Quantidade de acoes: " + resultados.getQtdAcoes() + "Prejuizo: R$ " + resultados.getSaldoFinal());
            }
        }
    }
}
