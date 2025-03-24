package com.invest7.dao;

import com.invest7.model.produtos.RendaFixa;
import com.invest7.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendaFixaDAO {
    public List<RendaFixa> buscarTodosProdutos() {
        List<RendaFixa> produtos = new ArrayList<>();
        String sql = "SELECT nome_produto, tipo_produto, rentabilidade_bruta, rentabilidade_liquida, "
                   + "risco_financeiro, investimento_minimo, is_taxable, taxa_base, porcentagem_taxa "
                   + "FROM renda_fixa";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                RendaFixa produto = new RendaFixa(
                    rs.getString("nome_produto"),
                    rs.getString("tipo_produto"),
                    rs.getDouble("rentabilidade_bruta"),
                    rs.getDouble("rentabilidade_liquida"),
                    rs.getInt("risco_financeiro"),
                    rs.getDouble("investimento_minimo"),
                    rs.getBoolean("is_taxable"),
                    rs.getString("taxa_base"),
                    rs.getDouble("porcentagem_taxa")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        }
        return produtos;
    }
}
