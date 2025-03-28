package com.invest7.view.menus.simulacao;

import com.invest7.controller.CalculadoraVariavel;
//import com.invest7.controller.InvestimentoController;
import com.invest7.model.produtos.Acoes;
import com.invest7.model.produtos.Fiis;


import java.util.List;
import java.util.Scanner;


public class MenuSimulacaoVariavel {

    public static <InvestimentoController> void simulacaoCompleta() {
        Scanner sc = new Scanner(System.in);
        double capital = 0.0, aporteMensal = 0.0, precoCota = 0.0,
                dividendoPorCota = 0.0, precoCompra = 0.0, precoVenda = 0.0;

        int prazo = 0, quantidadeCotas = 0, quantidade = 0, reinvestir = 0, simula=0;
        boolean digitoCerto = false;

        do {
            System.out.println("-----------TELA DE SIMULACAO COMPLETA--------");

            while (!digitoCerto) {
                System.out.println("1- Digite um Capital inicial: ");
                if (sc.hasNextDouble()) {
                    capital = sc.nextDouble();
                    if (capital >= 0) digitoCerto = true;
                    else System.out.println("Valor Incorreto, digite novamente...");
                } else {
                    System.out.println("Valor Incorreto, digite novamente...");
                    sc.next();
                }
            }

            digitoCerto = false;
            while (!digitoCerto) {
                System.out.println("2- Digite Quantidade de cotas: ");
                if (sc.hasNextDouble()) {
                    quantidadeCotas = sc.nextInt();
                    if (quantidadeCotas >= 0) digitoCerto = true;
                    else System.out.println("Valor Incorreto, digite novamente...");
                } else {
                    System.out.println("Valor Incorreto, digite novamente...");
                    sc.next();
                }
            }

            digitoCerto = false;
            while (!digitoCerto) {
                System.out.println("3- Digite um valor para aporte mensal: ");
                if (sc.hasNextDouble()) {
                    aporteMensal = sc.nextDouble();
                    if (aporteMensal >= 0) digitoCerto = true;
                    else System.out.println("Valor Incorreto, digite novamente...");
                } else {
                    System.out.println("Valor Incorreto, digite novamente...");
                    sc.next();
                }
            }

            digitoCerto = false;
            while (!digitoCerto) {
                System.out.println("4- Digite um prazo para a simulacao: ");
                if (sc.hasNextInt()) {
                    prazo = sc.nextInt();
                    if (prazo >= 0) digitoCerto = true;
                    else System.out.println("Valor Incorreto, digite novamente...");
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
                    if (reinvestir >= 1 && reinvestir <= 2) digitoCerto = true;
                    else System.out.println("Valor Incorreto, digite novamente...");
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

            List<Acoes> acoes = calculadoraV.simularAcao(capital, prazo);
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                             Simulação de Ações                                             ║");
            System.out.println("╠════════╦═══════════════╦════════╦═══════════╦═══════════════╦═══════════════╦══════════════╦═══════════════╣");
            System.out.println("║  Ação  ║    Capital    ║  Qtd   ║ Resultado ║ Total Compra  ║ Total Venda   ║  Saldo Final ║ Troco capital ║");
            System.out.println("╠════════╬═══════════════╬════════╬═══════════╬═══════════════╬═══════════════╬══════════════╣═══════════════╣");

            for (Acoes resultados : acoes) {
                String sinal = resultados.getSaldoFinal() > 0 ? "+" : "";
                System.out.printf("║ %-6s ║ %-13s ║ %-6d ║ %-9s ║ %-13s ║ %-13s ║ %-12s ║ %-13s ║%n",
                        resultados.getNome(),
                        String.format("R$ %,.2f", capital),
                        resultados.getQtdAcoes(),
                        resultados.getSaldoFinal() > 0 ? "LUCRO" : "PREJUÍZO",
                        String.format("R$ %,.2f", resultados.getCustoTotalCompra()),
                        String.format("R$ %,.2f", resultados.getValorTotalVenda()),
                        sinal + String.format("R$ %,.2f", resultados.getSaldoFinal()),
                        String.format("R$ %,.2f", resultados.getTroco()));

                System.out.println("╚════════╩═══════════════╩════════╩═══════════╩═══════════════╩═══════════════╩══════════════╩═══════════════╝");

            }

            digitoCerto = false;
            while (!digitoCerto) {
                System.out.println("Deseja realizar uma nova simulação? 1-Sim | 2-Não");
                if (sc.hasNextInt()) {
                    simula = sc.nextInt();
                    if (simula >= 1 && simula <=2) digitoCerto = true;
                    else System.out.println("Valor Incorreto, digite novamente...");
                } else {
                    System.out.println("Valor Incorreto, digite novamente...");
                    sc.next();
                }
            }

        } while (simula == 1);
    }

    }
