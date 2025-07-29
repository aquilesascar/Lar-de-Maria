package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.AlocacaoDTO;
import org.example.DTO.AlocacaoRecursoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; // CORRETO
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



    /**
     * Lista todas as alocações de recursos que ainda não foram entregues.
     * A lógica identifica uma entrega pendente se o id da alocação NÃO EXISTE
     * na tabela de distribuição.
     * @return Uma lista de AlocacaoRecursoDTO pendentes.
     * @throws SQLException
     */
    public List<AlocacaoRecursoDTO> listarEntregasPendentes() throws SQLException {
        List<AlocacaoRecursoDTO> pendentes = new ArrayList<>();
        String sql = "SELECT ar.* FROM alocacaorecurso ar " +
                "LEFT JOIN distribuicaorecurso dr ON ar.id_alocacao = dr.idAlocacaoRecurso " +
                "WHERE dr.id_distribuicao IS NULL";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                AlocacaoRecursoDTO alocacao = new AlocacaoRecursoDTO();
                alocacao.setIdAlocacao(rs.getInt("id_alocacao"));
                alocacao.setDescricao(rs.getString("descricao"));
                alocacao.setQuantidadeAlocada(rs.getInt("quantidade_alocada"));
                alocacao.setValorAlocado(rs.getFloat("valor_alocado"));
                alocacao.setDestino(rs.getString("destino"));
                alocacao.setDataDecisao(rs.getTimestamp("data_descisao"));
                pendentes.add(alocacao);
            }
        }
        return pendentes;
    }
}
