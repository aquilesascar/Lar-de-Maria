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
        /**
         * Lista todas as alocações de recursos associadas a uma necessidade específica de uma criança.
         * @param id_crianca O ID da criança para filtrar as necessidades e alocações.
         * @return Uma lista de AlocacaoRecursoDTO.
         * @throws SQLException
         */
        public List<AlocacaoRecursoDTO> listarAlocacoesPorCrianca(int id_crianca) throws SQLException {
            List<AlocacaoRecursoDTO> alocacoes = new ArrayList<>();

            // CORREÇÃO 1: Seleciona todas as colunas da tabela alocacaorecurso (usando o alias 'a').
            // CORREÇÃO 2: Usa um placeholder '?' na cláusula WHERE.
            String sql = "SELECT a.* FROM alocacaorecurso AS a " +
                    "JOIN necessidadeespecifica AS n ON a.id_necessidade = n.id_necessidade " +
                    "WHERE n.idCrianca = ?";

            try (Connection conn = ConexaoMySQL.getConnection();
                 PreparedStatement pstm = conn.prepareStatement(sql)) {

                // CORREÇÃO 3: Define o valor do placeholder.
                pstm.setInt(1, id_crianca);

                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        AlocacaoRecursoDTO alocacao = new AlocacaoRecursoDTO();

                        // CORREÇÃO 4: Popula o DTO com os dados corretos do ResultSet.
                        alocacao.setIdAlocacao(rs.getInt("id_alocacao"));
                        alocacao.setDescricao(rs.getString("descricao"));
                        alocacao.setQuantidadeAlocada(rs.getInt("quantidade_alocada"));
                        alocacao.setValorAlocado(rs.getFloat("valor_alocado"));
                        alocacao.setDestino(rs.getString("destino"));
                        alocacao.setDataDecisao(rs.getTimestamp("data_descisao"));

                        alocacoes.add(alocacao);
                    }
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
