// src/main/java/com/invest7/dao/RendaFixaDAO.java
package com.invest7.dao;

import com.invest7.model.produtos.Acoes;
import com.invest7.model.produtos.RendaFixa;
import com.invest7.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendaFixaDAO {
    public List<RendaFixa> buscarTodosProdutos() {
        List<RendaFixa> produtos = new ArrayList<>();
        String sql = "SELECT id_renda_fixa nome_produto, tipo_produto, rentabilidade_bruta, is_taxable FROM renda_fixa";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(new RendaFixa(
                        rs.getString("nome_produto"),
                        rs.getInt("id_renda_fixa"),
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

    public List<RendaFixa> buscarPorQuantidade(int quantidade) {
        List<RendaFixa> produtos = new ArrayList<>();
        String sql = "SELECT * FROM renda_fixa ORDER BY RAND() LIMIT ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Só prepara, não executa ainda

            stmt.setInt(1, quantidade); // ✅ Define o parâmetro ANTES de executar

            try (ResultSet rs = stmt.executeQuery()) { // Agora sim executa
                while (rs.next()) {
                    produtos.add(new RendaFixa(
                            rs.getString("nome_produto"),
                            rs.getInt("id_renda_fixa"),
                            rs.getDouble("rentabilidade_bruta"),
                            rs.getString("tipo_produto"),
                            rs.getBoolean("is_taxable")
                    ));
                }
            }

            // Adiciona Poupança (se necessário)
            produtos.add(new RendaFixa("Poupança", 6.17, "POUPANCA", false));

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        }
        return produtos;
    }
}