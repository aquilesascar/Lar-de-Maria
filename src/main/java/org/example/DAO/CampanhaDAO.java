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
                "campanha (nome_campanha, meta_arrecadacao, data_inicio, data_final, meta_descricao) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoMySQL().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, campanhaDTO.getNome_campanha());
            stmt.setInt(2, campanhaDTO.getMeta_arrecadacao());
            stmt.setDate(3, new java.sql.Date(campanhaDTO.getData_inicio().getTime()));
            stmt.setDate(4, new java.sql.Date(campanhaDTO.getData_final().getTime()));
            stmt.setString(5, campanhaDTO.getMeta_descricao());

            stmt.executeUpdate();
            System.out.println("Campanha cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar campanha: " + e.getMessage());
            e.printStackTrace();
        }
    }
}