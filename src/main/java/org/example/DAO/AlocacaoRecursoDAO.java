package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.AlocacaoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlocacaoRecursoDAO {
    public List<AlocacaoDTO> listarAlocacoes() throws SQLException {
        List<AlocacaoDTO> alocacoes = new ArrayList<>();
        String sql = "SELECT id_alocacao, descricao FROM alocacao_recurso WHERE categoria_destino = 'Necessidade Específica de Menina' ORDER BY data_alocacao DESC";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                AlocacaoDTO alocacao = new AlocacaoDTO();
                alocacao.setIdAlocacao(rs.getInt("id_alocacao"));
                alocacao.setDescricao(rs.getString("descricao"));
                alocacoes.add(alocacao);
            }
        }
        return alocacoes;
    }
}
