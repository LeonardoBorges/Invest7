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


            InvestimentoView view = new InvestimentoView();
            InvestimentoController rendaSimulados = new InvestimentoController();
            //Inicia a aplicação
            controller.iniciar();

        CalculadoraVariavel calculadoraV = new CalculadoraVariavel();
        List<Fiis> fiis = calculadoraV.simularFundoImobiliario(new Fiis(capital, aporteMensal,
                prazo, quantidadeCotas, reinvestir));


        for (Fiis resultados : fiis) {
            System.out.println("nome" + resultados.getNome()
                    + "Saldo cotas" + resultados.getSaldoCotas()
                    + "Saldo Dividendos" + resultados.getSaldoDividendos());


        }


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
