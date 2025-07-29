package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.CriancaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriancaDAO {

    public void cadastrarCrianca(CriancaDTO criancaDTO) throws SQLException {
        // SQL para inserção na tabela 'crianca'
        String sql = "INSERT INTO crianca (nome, data_nascimento, genero, data_entrada, " +
                "motivo_acolhimento, condicoes_medicas, escola, data_saida, motivo_saida, " +
                "logradouro, numero, complemento, bairro, cidade, estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Usa o try-with-resources para garantir que a conexão e o statement sejam fechados
        try (Connection conn = new ConexaoMySQL().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            // Define os parâmetros do PreparedStatement com base no DTO
            pstm.setString(1, criancaDTO.getNome());
            pstm.setDate(2, Date.valueOf(criancaDTO.getData_nascimento()));
            pstm.setString(3, criancaDTO.getGenero());
            pstm.setDate(4, Date.valueOf(criancaDTO.getData_entrada()));
            pstm.setString(5, criancaDTO.getMotivo_acolhimento());
            pstm.setString(6, criancaDTO.getCondicoes_medicas());
            pstm.setString(7, criancaDTO.getEscola());

            // Trata campos que podem ser nulos (data_saida, motivo_saida)
            if (criancaDTO.getData_saida() != null) {
                pstm.setDate(8, Date.valueOf(criancaDTO.getData_saida()));
            } else {
                pstm.setNull(8, Types.DATE);
            }
            pstm.setString(9, criancaDTO.getMotivo_saida());

            // Campos de endereço
            pstm.setString(10, criancaDTO.getLogradouro());
            pstm.setInt(11, criancaDTO.getNumero());
            pstm.setString(12, criancaDTO.getComplemento());
            pstm.setString(13, criancaDTO.getBairro());
            pstm.setString(14, criancaDTO.getCidade());
            pstm.setString(15, criancaDTO.getEstado());

            // Executa a inserção
            pstm.executeUpdate();
        }
    }

    // DAO/CriancaDAO.java (Método auxiliar para listar crianças)
// Adicione este método à sua classe CriancaDAO existente.

    public List<CriancaDTO> listarCriancas() throws SQLException {
        List<CriancaDTO> criancas = new ArrayList<>();
        String sql = "SELECT id_crianca, nome FROM crianca ORDER BY nome ASC";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                CriancaDTO crianca = new CriancaDTO();
                crianca.setId_crianca(rs.getInt("id_crianca"));
                crianca.setNome(rs.getString("nome"));
                criancas.add(crianca);
            }
        }
        return criancas;
    }
}
