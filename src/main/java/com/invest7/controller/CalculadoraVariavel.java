package com.invest7.controller;
import com.invest7.dao.AcoesDao;
import com.invest7.dao.FiisDAO;
import com.invest7.model.Fiis;
import com.invest7.model.Acoes;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraVariavel {

    public List<Fiis> simularFundoImobiliario(Fiis calculadoraV) {
        FiisDAO dao = new FiisDAO();
        List<Fiis> resultados = dao.buscarFiis();
        List<Fiis> fiisSimulados = new ArrayList<>();

        for (Fiis fiis : resultados) {
            double saldoDividendos = 0;
            double dividendoPorCota = fiis.getDividendYield();
            double valorAporte = calculadoraV.getAporte();
            double precoCota = fiis.getPrecoFiis();
            double dvCotas = fiis.getDesvioCotas();
            double dvDiv = fiis.getDesvioDividendos();
            int meses = calculadoraV.getMeses();
            int quantidadeCotas = calculadoraV.getQtdCotas();
            int reinvestir = calculadoraV.getReinvestir();


            for (int i = 1; i <= meses; i++) {
                double dividendosRecebidos = quantidadeCotas * dividendoPorCota;
                saldoDividendos += dividendosRecebidos + valorAporte;

                if (reinvestir == 1) {
                    int novasCotas = (int) (saldoDividendos / precoCota);
                    quantidadeCotas += novasCotas;
                    saldoDividendos -= novasCotas * precoCota;
                } else {
                    int novasCotasAporte = (int) (valorAporte / precoCota);
                    quantidadeCotas += novasCotasAporte;
                    saldoDividendos -= novasCotasAporte * precoCota;
                }
            }

            // Calcula saldo de cotas e dividendos reais
            double saldoCotas = quantidadeCotas * precoCota;
            double saldoCotasReal = saldoCotas - saldoCotas * dvCotas;
            double saldoDivReal = saldoDividendos - saldoDividendos * dvDiv;

            // Atualiza o objeto Fiis com os resultados da simulação
            fiis.setSaldoCotas(saldoCotasReal);
            fiis.setSaldoDividendos(saldoDivReal);

            // Adiciona o Fiis simulado na lista de resultados
            fiisSimulados.add(fiis);
        }
        return fiisSimulados;
    }
}
/*
    public Acoes simularAcao(Acoes calculadoraV) {
        AcoesDao dao = new AcoesDao();
        Acoes resultados = dao.buscarAcoes(new Acoes());
        double precoCompra = calculadoraV.getPrecoAcao(), precoVenda = calculadoraV.getPrecoVenda();
        int quantidade = calculadoraV.getQtdAcoes();

        double custoTotal = precoCompra * quantidade;
        double valorVenda = precoVenda * quantidade;
        double saldo = valorVenda - custoTotal;

        return null;
    }
}


 */