package com.invest7.controller;

import com.invest7.model.Endereco;
import com.invest7.util.ViaCep;

public class BuscarCep {
    public String buscarApi(String cep){
        try {
            Endereco endereco = ViaCep.buscarCep(cep);

            // Construindo a string formatada
            StringBuilder resultado = new StringBuilder();
            resultado.append(endereco.getLogradouro())
                    .append(", Bairro: ").append(endereco.getBairro())
                    .append(", Cidade: ").append(endereco.getLocalidade());

            return resultado.toString();
        } catch (Exception e) {
            return "Erro ao buscar o CEP: " + e.getMessage();
        }
    }
}
