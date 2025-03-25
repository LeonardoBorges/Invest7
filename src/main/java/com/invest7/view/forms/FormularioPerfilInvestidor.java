package com.invest7.view.forms;

import java.util.Scanner;

public class FormularioPerfilInvestidor {

    public int calcularPontuacao() {
        Scanner scanner = new Scanner(System.in);
        int pontuacao = 0;
        String[][] perguntas = {
                {"Qual é o seu principal objetivo ao investir?", "A) Preservar o capital com segurança, mesmo que o retorno seja baixo", "B) Equilibrar segurança e crescimento do patrimônio", "C) Buscar altos retornos, mesmo que isso envolva riscos maiores"},
                {"Por quanto tempo você pretende manter seus investimentos antes de precisar do dinheiro?", "A) Menos de 1 ano", "B) Entre 1 e 5 anos", "C) Mais de 5 anos"},
                {"Como você reagiria se seus investimentos perdessem 10% do valor em um curto período?", "A) Ficaria muito preocupado e tentaria resgatar o dinheiro imediatamente", "B) Ficaria incomodado, mas esperaria para ver se a situação melhora", "C) Aceitaria a perda como parte do processo e manteria os investimentos"},
                {"Qual percentual do seu patrimônio você está disposto a investir em produtos de maior risco?", "A) Até 10%", "B) Entre 10% e 30%", "C) Mais de 30%"},
                {"Qual é o seu nível de conhecimento sobre investimentos financeiros?", "A) Básico (conheço apenas opções seguras, como poupança)", "B) Intermediário (já ouvi falar de fundos e ações, mas não domino o assunto)", "C) Avançado (entendo bem o mercado e acompanho as tendências)"},
                {"Você já tem uma reserva de emergência (equivalente a 6 meses de despesas)?", "A) Sim, e não pretendo usá-la para investimentos", "B) Sim, mas posso usar uma parte para investir", "C) Não, e pretendo investir tudo o que tenho disponível"},
                {"Qual é a sua tolerância a variações no valor dos seus investimentos?", "A) Prefiro estabilidade, mesmo com ganhos menores", "B) Aceito pequenas variações em busca de melhores retornos", "C) Estou confortável com grandes oscilações por retornos altos"},
                {"Qual dessas opções de investimento você se sente mais confortável?", "A) Títulos públicos ou renda fixa com liquidez diária", "B) Fundos mistos com renda fixa e um pouco de renda variável", "C) Ações ou fundos de maior risco"},
                {"Caso precisasse escolher, o que seria mais importante para você?", "A) Segurança do valor investido", "B) Um equilíbrio entre segurança e rentabilidade", "C) Maximizar a rentabilidade, mesmo com risco"},
                {"Qual é a sua experiência prévia com investimentos?", "A) Nenhuma ou apenas em produtos simples (ex.: poupança)", "B) Já investi em fundos ou renda fixa com orientação", "C) Já investi por conta própria em ações ou outros produtos de risco"}
        };

        int[] valores = {10, 15, 20};

        for (int i = 0; i < perguntas.length; i++) {
            System.out.println("\n" + (i + 1) + ". " + perguntas[i][0]);
            System.out.println(perguntas[i][1]);
            System.out.println(perguntas[i][2]);
            System.out.println(perguntas[i][3]);
            System.out.print("Escolha (A, B, C): ");

            String resposta = scanner.next().toUpperCase();

            switch (resposta) {
                case "A":
                    pontuacao += valores[0];
                    break;
                case "B":
                    pontuacao += valores[1];
                    break;
                case "C":
                    pontuacao += valores[2];
                    break;
                default:
                    System.out.println("Resposta inválida! Contabilizando como 0 pontos.");
            }
        }

        int perfi_id =  exibirResultado(pontuacao);
        return perfi_id;

    }

    private static int exibirResultado(int pontuacao) {
        System.out.println("\n===== Resultado do Perfil de Investidor =====");
        System.out.println("Pontuação total: " + pontuacao);

        if (pontuacao >= 100 && pontuacao <= 130) {
            System.out.println("🔹 Perfil Conservador: Você valoriza estabilidade e baixa tolerância ao risco.");
            return 1;
        } else if (pontuacao >= 140 && pontuacao <= 170) {
            System.out.println("🔹 Perfil Moderado: Você busca um equilíbrio entre segurança e crescimento.");
            return 2;
        } else if (pontuacao >= 180 && pontuacao <= 200) {
            System.out.println("🔹 Perfil Arrojado: Você tem alta tolerância ao risco e foca na rentabilidade.");
            return 3;
        } else {
            System.out.println("🔹 Perfil Indefinido: Tente refazer o teste.");
            return 0;
        }
    }
}

