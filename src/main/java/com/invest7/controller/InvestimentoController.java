package com.invest7.controller;

import com.invest7.model.RendaFixa_Result;
import com.invest7.model.produtos.RendaFixa;
import com.invest7.view.InvestimentoView;

import java.util.List;

public class InvestimentoController {
    private final CalculadoraInvestimentos model;
    private final InvestimentoView view;

    public List<RendaFixa>simularRendaFixa () {
        if (model == null || view == null) {
            throw new IllegalArgumentException("Model e View não podem ser nulos");
        }
        this.model = model;
        this.view = view;
    }

    public void iniciar() {
        try {
            view.mostrarMenu();

            double inicial = view.lerInvestimentoInicial();
            double aporte = view.lerAporteMensal();
            int meses = view.lerPeriodoMeses();



            validarEntradas(inicial, aporte, meses);

            List<Investimento> resultados = model.calcularTodosInvestimentos(inicial, aporte, meses);

            view.mostrarResultados(resultados);
        } catch (IllegalArgumentException e) {
            view.mostrarErro(e.getMessage());
        } catch (Exception e) {
            view.mostrarErro("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    private void validarEntradas(double inicial, double aporte, int meses) {
        if (inicial < 0) {
            throw new IllegalArgumentException("Investimento inicial não pode ser negativo");
        }
        if (aporte < 0) {
            throw new IllegalArgumentException("Aporte mensal não pode ser negativo");
        }
        if (meses <= 0) {
            throw new IllegalArgumentException("Período em meses deve ser maior que zero");
        }
    }
}

