package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.MembroEquipeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembroEquipeDAO {
    public void cadastrarMembroEquipe(MembroEquipeDTO membroEquipe) {
        String sql = "INSERT INTO membroequipe " +
                "(nome_completo, cpf, email, telefone, cargo_funcao, data_inicio, data_saida, tipo_membro, id_doador_voluntario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoMySQL().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, membroEquipe.getNome_completo());
            stmt.setString(2, membroEquipe.getCpf());
            stmt.setString(3, membroEquipe.getEmail());
            stmt.setString(4, membroEquipe.getTelefone());
            stmt.setString(5, membroEquipe.getCargo_funcao());
            stmt.setDate(6, new java.sql.Date(membroEquipe.getData_inicio().getTime()));

            if (membroEquipe.getData_saida() != null) {
                stmt.setDate(7, new java.sql.Date(membroEquipe.getData_saida().getTime()));
            } else {
                stmt.setNull(7, Types.DATE);
            }

            if (membroEquipe.getTipo_membro() != null) {
                stmt.setString(8, membroEquipe.getTipo_membro());
            } else {
                stmt.setNull(8, Types.VARCHAR);
            }

            if (membroEquipe.getId_doador_voluntario() != null) {
                stmt.setInt(9, membroEquipe.getId_doador_voluntario());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }

            stmt.executeUpdate();
            System.out.println("Membro da Equipe " + membroEquipe.getNome_completo() + "registrado com sucesso");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar Membro da Equipe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deletarMembroEquipe(int id) {
        String sql = "DELETE FROM membroequipe WHERE id_membro_equipe = ?";

        try (Connection conexao = new ConexaoMySQL().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Membro da Equipe com ID " + id + " foi deletado com sucesso.");
            } else {
                System.out.println("Nenhum membro encontrado com ID " + id + ".");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar Membro da Equipe: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<MembroEquipeDTO> listarMembros() {
        List<MembroEquipeDTO> membros = new ArrayList<>();
        String sql = "SELECT * FROM membroequipe";

        try (Connection conexao = new ConexaoMySQL().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MembroEquipeDTO membro = new MembroEquipeDTO();

                membro.setId_membro_equipe(rs.getInt("id_membro_equipe"));
                membro.setNome_completo(rs.getString("nome_completo"));
                membro.setCpf(rs.getString("cpf"));
                membro.setEmail(rs.getString("email"));
                membro.setTelefone(rs.getString("telefone"));
                membro.setCargo_funcao(rs.getString("cargo_funcao"));
                membro.setData_inicio(rs.getDate("data_inicio"));
                membro.setData_saida(rs.getDate("data_saida")); // pode ser null
                membro.setTipo_membro(rs.getString("tipo_membro")); // pode ser null

                int idDoadorVoluntario = rs.getInt("id_doador_voluntario");
                if (!rs.wasNull()) {
                    membro.setId_doador_voluntario(idDoadorVoluntario);
                } else {
                    membro.setId_doador_voluntario(null);
                }

                membros.add(membro);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar membros da equipe: " + e.getMessage());
            e.printStackTrace();
        }

        return membros;
    }

    public void atualizarCampoMembroEquipe(int id, String campo, String novoValor) {
        String sql = "UPDATE membroequipe SET " + campo + " = ? WHERE id_membro_equipe = ?";

        try (Connection conexao = new ConexaoMySQL().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoValor);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("Campo '" + campo + "' atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar membro da equipe: " + e.getMessage());
        }
    }
}