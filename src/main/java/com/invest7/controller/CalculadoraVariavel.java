package com.invest7.controller;


import com.invest7.dao.AcoesDao;
import com.invest7.dao.FiisDAO;
import com.invest7.model.produtos.Acoes;
import com.invest7.model.produtos.Fiis;


import java.util.ArrayList;
import java.util.List;


public class CalculadoraVariavel {
    public static final int REINVESTIR_SIM = 1;
    public static final int REINVESTIR_NAO = 2;
    private static final double LIMITE_ISENCAO_IR = 20000.0;


    //calculadora de FIIs
    /*public List<Fiis> simularFundoImobiliario(Fiis calculadoraV) {
        FiisDAO dao = new FiisDAO();
        List<Fiis> resultados = dao.buscarFiis();
        if (resultados == null || resultados.isEmpty()) {
            throw new RuntimeException("Nenhum FII encontrado para simulação");
        }


        List<Fiis> fiisSimulados = new ArrayList<>();


        for (Fiis fii : resultados) {
            double saldoDividendos = 0;
            double dividendoPorCota = fii.getDividendYield();
            double valorAporte = calculadoraV.getAporte();
            double precoCota = fii.getPrecoFiis();
            double desvioCotas = fii.getDesvioCotas();
            double desvioDividendos = fii.getDesvioDividendos();

            int meses = calculadoraV.getMeses();
            int quantidadeCotas = calculadoraV.getQtdCotas();
            int reinvestir = calculadoraV.getReinvestir();


            for (int mes = 1; mes <= meses; mes++) {
                double dividendosRecebidos = quantidadeCotas * dividendoPorCota;
                saldoDividendos += dividendosRecebidos + valorAporte;


                if (reinvestir == REINVESTIR_SIM) {
                    int novasCotas = (int) (saldoDividendos / precoCota);
                    quantidadeCotas += novasCotas;
                    saldoDividendos -= novasCotas * precoCota;
                } else {
                    int novasCotasAporte = (int) (valorAporte / precoCota);
                    quantidadeCotas += novasCotasAporte;
                    saldoDividendos -= novasCotasAporte * precoCota;
                }
            }


            double saldoCotas = quantidadeCotas * precoCota;
            double saldoCotasReal = saldoCotas * (1 - desvioCotas);
            double saldoDivReal = saldoDividendos * (1 - desvioDividendos);


            Fiis fiiSimulado = new Fiis(
                    fii.getNome(),
                    fii.getPrecoFiis(),
                    fii.getDividendYield(),
                    fii.getDesvioCotas(),
                    fii.getDesvioDividendos()
            );


            fiiSimulado.setSaldoCotas(saldoCotasReal);
            fiiSimulado.setSaldoDividendos(saldoDivReal);


            fiisSimulados.add(fiiSimulado);
        }

        return fiisSimulados;
    }*/

    public List<Fiis> simularFundoImobiliario(Fiis calculadoraV) {
        FiisDAO dao = new FiisDAO();
        List<Fiis> resultados = dao.buscarFiis();
        if (resultados == null || resultados.isEmpty()) {
            throw new RuntimeException("Nenhum FII encontrado para simulação");
        }

        List<Fiis> fiisSimulados = new ArrayList<>();
        for (Fiis fii : resultados) {
            // Dados específicos para cada FII
            double dividendYield = 0;
            double precoCota = 0;

            switch (fii.getNome()) {
                case "XPLG11":
                    dividendYield = 78.00;
                    precoCota = 1.3;
                    break;
                case "LVBI11":
                    dividendYield = 56.00;
                    precoCota = 1.9;
                    break;
                case "AAZQ11":
                    dividendYield = 87.00;
                    precoCota = 1.2;
                    break;
                case "MXRF11":
                    dividendYield = 53.00;
                    precoCota = 3.1;
                    break;
                case "TGAR11":
                    dividendYield = 1.26;
                    precoCota = 6.47;
                    break;
                default:
                    throw new IllegalArgumentException("FII não reconhecido: " + fii.getNome());
            }

            // Parâmetros da simulação
            int quantidadeCotas = calculadoraV.getQtdCotas(); // Definindo um valor fixo para simulação
            int meses = 12; // Simulação para 12 meses
            double valorAporte = 0; // Aporte mensal zero

            double saldoDividendos = 0;

            for (int mes = 1; mes <= meses; mes++) {
                // Cálculo de dividendos considerando o dividend yield
                double dividendosRecebidos = quantidadeCotas * (precoCota * (dividendYield / 100.0));

                // Adiciona os dividendos ao saldo
                saldoDividendos += dividendosRecebidos;
            }

            // Criação do FII simulado
            Fiis fiiSimulado = new Fiis(
                    fii.getNome(),
                    precoCota,
                    dividendYield,
                    0, // Desvio de Cotas
                    0  // Desvio de Dividendos
            );

            fiiSimulado.setSaldoCotas(quantidadeCotas * precoCota);
            fiiSimulado.setSaldoDividendos(saldoDividendos);

            fiisSimulados.add(fiiSimulado);
        }

        return fiisSimulados;
    }


    public List<Acoes> simularAcao(double capital, int prazo) {
        AcoesDao dao = new AcoesDao();
        List<Acoes> resultados = dao.buscarAcao();
        List<Acoes> acoesFeitas = new ArrayList<>();


        for (Acoes acaoSimulada : resultados) {
            double precoCompra = acaoSimulada.getPrecoAcao();
            int quantidadeAcao = (int) Math.floor(capital / acaoSimulada.getPrecoAcao());
            double txIr = acaoSimulada.getTxIr();
            double desvio = acaoSimulada.getDesvio() / 100;
            double variacao = 0;
            double custoTotal = precoCompra * quantidadeAcao;
            double valorVenda = 0;
            double saldoM = 0;
            double saldoFinal = 0;


            //olha o codigo - errado
            for (int i = 0; i < prazo; i++) {
                variacao = (Math.random() * 2 * desvio) - desvio;
                valorVenda = precoCompra * (1 + variacao);
                saldoM = saldoM + (valorVenda - precoCompra);
                i++;
            }
            if (saldoM > 20000) {
                saldoFinal = ((saldoM - (saldoM * txIr)) - custoTotal);


            } else {
                saldoFinal = saldoM - custoTotal;
            }


            Acoes acoesFinal = new Acoes(acaoSimulada.getNome());
            acoesFinal.setQtdAcoes(quantidadeAcao);
            acoesFinal.setValorInvestido(capital);
            acoesFinal.setSaldoFinal(saldoFinal);


            acoesFeitas.add(acoesFinal);
        }


        return acoesFeitas;
    }
}



