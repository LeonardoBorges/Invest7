package com.invest7.view;

import com.invest7.controller.CalculadoraRFprovisoria;

import java.text.DecimalFormat;
import java.util.Scanner;

import static com.invest7.controller.CalculadoraRFprovisoria.*;

public class SimulPrevia {
    static DecimalFormat df = new DecimalFormat("#,##0.00");
    public static void simPrevia () {
        Scanner sc = new Scanner(System.in);
        String [] produtos = {"CDB/CDI", "Tesouro Direto Prefixado", "Tesouro Direto Selic",
                "Tesouro Direto IPCA+"};
        double [] rentabilidade = {0.0916, 0.00833, 0.0111, 0.00375};

        double capital = 0.0, aporteMensal = 0.0;
        int prazo = 0;
        boolean digitoCerto = false;

        System.out.println("-----------SIMULACAO PRÉVIA--------");

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

        double aliqIR = calculoImpRenda(prazo);

        for (int i = 0; i < produtos.length ; i++) {
            double tx = rentabilidade[i];
            double rendBruto = calculoRendBruto(capital, aporteMensal, tx, prazo);
            double rendLiq = calculoRendLiq(rendBruto, capital, aliqIR);

            System.out.println("\nProduto: " + produtos[i]);
            System.out.println("Taxa Juros Mensal: " + df.format(tx*100) + "%");
            System.out.println("Rendimento Bruto: R$ " + df.format(rendBruto));
            System.out.println("Imposto de Renda: R$ " + df.format(rendBruto*aliqIR) );
            System.out.println("Rendimento Líquido: R$ " + df.format(rendLiq) + "\n");
        }


    }

}


