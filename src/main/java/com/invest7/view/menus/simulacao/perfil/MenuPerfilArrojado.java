package com.invest7.view.menus.simulacao.perfil;



import com.invest7.controller.CalculadoraVariavel;
import com.invest7.dao.AcoesDao;
import com.invest7.dao.FiisDAO;
import com.invest7.model.produtos.Acoes;
import com.invest7.model.produtos.Fiis;

import java.util.List;
import java.util.Scanner;

public class MenuPerfilArrojado {


    public void simulacaoPerfilArrojado(Double capital, int reinvestir, int prazo, double aporte, int quantidadeCotas) {
        FiisDAO daoF = new FiisDAO();
        AcoesDao daoA = new AcoesDao();
        //RendaFixaDAO daoR = new RendaFixaDAO
        CalculadoraVariavel calculadoraV = new CalculadoraVariavel();
        boolean historico = false;
        List<Fiis> fiisSimulados = calculadoraV.simularFundoImobiliarioPerfil(new Fiis(aporte, prazo, quantidadeCotas), historico, 2);
        for (Fiis fiis : fiisSimulados ){
            System.out.println("Nome " + fiis.getNome() + " saldo " + fiis.getPrecoFiis());
        }

        List<Acoes> acoesSimuladas = calculadoraV.simularAcaoPerfil(capital, prazo, historico,1 );

        for (Acoes acao: acoesSimuladas){
            System.out.println("Nome " + acao.getNome() + "saldo final " + acao.getSaldoFinal());
        }
    }
}



