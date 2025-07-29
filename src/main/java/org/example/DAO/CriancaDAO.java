package org.example.DAO;
import org.example.Connection.ConexaoMySQL;
import org.example.DTO.CriancaDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CriancaDAO {

    /**
     * Insere uma nova criança no banco de dados.
     * @param criancaDTO Objeto DTO com todas as informações da criança.
     * @throws SQLException se ocorrer um erro durante a operação no banco.
     */
    public void cadastrarCrianca(CriancaDTO criancaDTO) throws SQLException {
        // SQL para inserção na tabela 'crianca'
        String sql = "INSERT INTO crianca (nome, data_nascimento, genero, data_entrada, "
                + "motivo_acolhimento, condicoes_medicas, escola, data_saida, motivo_saida, "
                + "logradouro, numero, complemento, bairro, cidade, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Usa o try-with-resources para garantir que a conexão e o statement sejam fechados
        try (Connection conn = new ConexaoMySQL().getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {

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
}
