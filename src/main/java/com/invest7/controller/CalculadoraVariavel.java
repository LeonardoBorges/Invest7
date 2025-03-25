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

    public List<Fiis> simularFundoImobiliario(Fiis calculadoraV) {
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
    }

    public Acoes simularAcao(Acoes calculadoraV) {
        AcoesDao dao = new AcoesDao();
        List <Acoes> resultados = dao.buscarAcao();
        double precoCompra = calculadoraV.getPrecoAcao(),
                precoVenda = calculadoraV.getPrecoVenda();




        double capital = calculadoraV.getValorInvestido();
        double quantidade = (capital / resultados.set);
        double txIr = calculadoraV.getTxIr();
        double custoTotal = precoCompra * quantidade;
        double valorVenda = precoVenda * quantidade;
        double saldo = valorVenda - custoTotal;


        if (saldo > 20000){
            double saldoFinal = saldo - (saldo*txIr);
        }
        return null;
    }
}




