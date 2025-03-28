package com.invest7.view.menus.simulacao;


import java.util.Scanner;

public class MenuPrincSimulacoesDisp {
    public static void exibeMenuSimDisp() {
        Scanner sc = new Scanner(System.in);
        int op;
        System.out.println("*********** Simulações ***********");
        do {
            System.out.println("1- Fazer Simulação Completa RF");
            System.out.println("2- Fazer Simulação por Perfil");
            System.out.println("3- Fazer Simulação RV");
            System.out.println("0- Voltar ao menu principal");
            op = Integer.parseInt(sc.next());
            switch (op) {
                case 1:
                    MenuSimulacaoCompleta.simulacaoCompleta(sc);
                    break;
                case 2:
                    MenuSimulacaoPerfil perfil = new MenuSimulacaoPerfil();
                    perfil.EscolherPerfilInvestidor();
                    break;
                case 3:
                    MenuSimulacaoVariavel.simulacaoCompleta();
                    break;
            }
        } while (op != 0);
    }
}
