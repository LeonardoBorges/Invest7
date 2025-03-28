package com.invest7.view.menus.simulacao;

import com.invest7.model.produtos.Fiis;
import com.invest7.view.menus.simulacao.perfil.MenuPerfilArrojado;

import java.util.Scanner;



public class MenuSimulacaoPerfil {

    public void EscolherPerfilInvestidor() {
        Scanner sc = new Scanner(System.in);
        int escolhas = 0;
        double capital = 0.0, aporteMensal = 0.0, precoCota = 0.0,
                dividendoPorCota = 0.0, precoCompra = 0.0, precoVenda = 0.0;

        int prazo = 0, quantidadeCotas = 0, quantidade = 0, reinvestir = 0, simula=0;
        boolean digitoCerto = false, historico = true;

        System.out.println("Digite o numero referente ao perfil que deseja simular: ");
        System.out.println("1- Conservador");
        System.out.println("2- Moderado");
        System.out.println("3 - Arrojado");

        escolhas = sc.nextInt();

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

        switch (escolhas) {
            case 1:
                MenuPerfilArrojado arrojado = new MenuPerfilArrojado();
                arrojado.simulacaoPerfilArrojado(capital, reinvestir, prazo,aporteMensal, quantidadeCotas);


                break;
            case 2:

                break;
            case 3:

                break;
            default:
                break;
        }

    }

    }



