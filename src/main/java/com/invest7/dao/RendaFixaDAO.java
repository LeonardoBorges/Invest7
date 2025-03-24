package com.invest7.dao;

import com.invest7.model.produtos.Rendafixa;
import com.invest7.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RendaFixaDAO {

    public List<RendaFixa> buscarRendaFixa() {
        List<RendaFixa> RendaFixaAll = new ArrayList<>();
        String sql = "SELECT nome_produto, " +
                "rentabilidade_bruta, " +
                "rentabilidade_liquida, " +
                "risco_financeiro, " +
                "investimento_minimo, " +
                "imposto_renda_IR FROM renda_fixa";
                
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RendaFixa acao = new RendaFixa(
                            rs.getString("nome_produto"),
                            rs.getDouble("rentabilidade_bruta"),
                            rs.getDouble("rentabilidade_liquida"),
                            rs.getInt("risco_financeiro"),
                            rs.getDouble("investimento_minimo"),
                            rs.getDouble("imposto_renda_IR")
                    );

                    RendaFixaAll.add(acao);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar renda fixa: " + e.getMessage());
            return null;
        }

        return RendaFixaAll;
    }
}