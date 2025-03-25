package com.invest7.dao;
import com.invest7.model.produtos.Fiis;
import com.invest7.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FiisDAO {

        public List<Fiis> buscarFiis() {
            List<Fiis> fiisAll = new ArrayList<>();
            String sql = "SELECT nome_prod, preco_fiis, dividend_yeld, desvio_cotas" +
                    ", desvio_dividendos FROM fiis";

            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Fiis fii = new Fiis(
                            rs.getString("nome_prod"),
                            rs.getDouble("preco_fiis"),
                            rs.getDouble("dividend_yeld"),
                            rs.getDouble("desvio_cotas"),
                            rs.getDouble("desvio_dividendos")
                    );
                    fiisAll.add(fii);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao buscar FIIs: " + e.getMessage());
                throw new RuntimeException("Erro ao buscar FIIs no banco de dados", e);
            }
            return fiisAll;
        }
}

