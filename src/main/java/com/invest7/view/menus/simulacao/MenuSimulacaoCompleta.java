package com.invest7.view.menus.simulacao;

import com.invest7.controller.CalculadoraFixa;
import com.invest7.model.produtos.RendaFixa;
import com.invest7.view.ConsoleView;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuSimulacaoCompleta {

    public static void simulacaoCompleta(Scanner sc
    ) { // Scanner recebido como parâmetro

        BigDecimal capital = BigDecimal.ZERO;
        BigDecimal aporteMensal = BigDecimal.ZERO;
        int meses = 0;
        boolean digitoCerto;

        System.out.println("-----------TELA DE SIMULACAO COMPLETA - RENDA FIXA--------");

        // 1. Captura do Capital Inicial
        digitoCerto = false;
        while (!digitoCerto) {
            System.out.print("1- Digite um Capital inicial: ");
            String inputCapital = sc.nextLine().trim();
            try {
                capital = new BigDecimal(inputCapital);
                if (capital.compareTo(BigDecimal.ZERO) >= 0) {
                    digitoCerto = true;
                } else {
                    System.out.println("Erro: Valor deve ser positivo ou zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato inválido. Exemplo correto: 1500.50");
            }
        }

        // 2. Captura do Aporte Mensal
        digitoCerto = false;
        while (!digitoCerto) {
            System.out.print("2- Digite um valor para aporte mensal: ");
            String inputAporte = sc.nextLine().trim();
            try {
                aporteMensal = new BigDecimal(inputAporte);
                if (aporteMensal.compareTo(BigDecimal.ZERO) >= 0) {
                    digitoCerto = true;
                } else {
                    System.out.println("Erro: Valor deve ser positivo ou zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Formato inválido. Exemplo correto: 300.00");
            }
        }

        // 3. Captura do Prazo em Meses
        digitoCerto = false;
        while (!digitoCerto) {
            System.out.print("3- Digite um prazo para a simulação (meses): ");
            String inputMeses = sc.nextLine().trim();
            try {
                meses = Integer.parseInt(inputMeses);
                if (meses > 0) {
                    digitoCerto = true;
                } else {
                    System.out.println("Erro: O prazo deve ser maior que zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido. Exemplo: 12");
            }
        }

        // Simulação e Exibição de Resultados
        CalculadoraFixa calculadoraFixa = new CalculadoraFixa();
        List<RendaFixa> resultados = calculadoraFixa.simularInvestimento(capital, aporteMensal, meses);

        String idSimulacao = UUID.randomUUID().toString();
        ConsoleView view = new ConsoleView();
        view.exibirSimulacao(resultados, idSimulacao, capital, aporteMensal, meses);

    }

}