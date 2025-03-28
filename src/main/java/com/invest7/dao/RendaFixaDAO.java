// src/main/java/com/invest7/dao/RendaFixaDAO.java
package com.invest7.dao;

import com.invest7.model.produtos.RendaFixa;
import com.invest7.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendaFixaDAO {
    public List<RendaFixa> buscarTodosProdutos() {
        List<RendaFixa> produtos = new ArrayList<>();
        String sql = "SELECT nome_produto, tipo_produto, rentabilidade_bruta, is_taxable FROM renda_fixa";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(new RendaFixa(
                        rs.getString("nome_produto"),
                        rs.getDouble("rentabilidade_bruta"),
                        rs.getString("tipo_produto"),
                        rs.getBoolean("is_taxable")
                ));
            }

            // Add Poupança programmatically
            produtos.add(new RendaFixa(
                    "Poupança",
                    6.17,
                    "POUPANCA",
                    false
            ));

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        }
        return produtos;
    }
}