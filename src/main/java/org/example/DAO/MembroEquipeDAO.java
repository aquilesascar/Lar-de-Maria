package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.MembroEquipeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembroEquipeDAO {
    public void cadastrarMembroEquipe(MembroEquipeDTO membroEquipe) {
        String sql = "INSERT INTO membroequipe (nome_completo, cpf, email, telefone, cargo_funcao, data_inicio, data_saida) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoMySQL().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, membroEquipe.getNome_completo());
            stmt.setString(2, membroEquipe.getCpf());
            stmt.setString(3, membroEquipe.getEmail());
            stmt.setString(4, membroEquipe.getTelefone());
            stmt.setString(5, membroEquipe.getCargo_funcao());
            stmt.setDate(6, new java.sql.Date(membroEquipe.getData_inicio().getTime()));

            // Data de Saida e opcional
            if (membroEquipe.getData_saida() != null) {
                stmt.setDate(7, new java.sql.Date(membroEquipe.getData_saida().getTime()));
            } else {
                stmt.setNull(7, java.sql.Types.DATE);
            }

            stmt.executeUpdate();
            System.out.println("Membro da Equipe " + membroEquipe.getNome_completo() + "registrado com sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Membro da Equipe: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
