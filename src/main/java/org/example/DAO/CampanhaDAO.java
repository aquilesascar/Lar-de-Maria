package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.CampanhaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class CampanhaDAO {
    public void cadastrarCampanha(CampanhaDTO campanhaDTO) {
        String sql = "INSERT INTO " +
                "campanha (nome_campanha, valor_quantidade_arrecadado, meta_arrecadacao, data_inicio, data_final) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, campanhaDTO.getNome_campanha());
            stmt.setFloat(2, campanhaDTO.getValor_quantidade_arrcadado());
            stmt.setString(3, campanhaDTO.getMeta_arrecadacao());
            stmt.setDate(4, new java.sql.Date(campanhaDTO.getData_inicio().getTime()));
            stmt.setDate(5, new java.sql.Date(campanhaDTO.getData_final().getTime()));

            stmt.executeUpdate();
            System.out.println("Campanha cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar campanha: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


