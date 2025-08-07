package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.RelatorioIdadeCriancaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioIdadeCriancaDAO {

    /**
     * Busca um relatório de crianças agrupadas por faixa etária, dentro de um intervalo de idade especificado.
     * @param idadeMin A idade mínima para o filtro.
     * @param idadeMax A idade máxima para o filtro.
     * @return Uma lista de RelatorioIdadeCriancaDTO com os dados agregados.
     * @throws SQLException
     */
    public List<RelatorioIdadeCriancaDTO> getRelatorioPorIdade(int idadeMin, int idadeMax) throws SQLException {
        List<RelatorioIdadeCriancaDTO> relatorio = new ArrayList<>();

        // Esta query SQL calcula a idade, define as faixas etárias usando CASE,
        // filtra pelo intervalo de idade e agrupa os resultados para contagem.
        String sql = "SELECT " +
                "    CASE " +
                "        WHEN TIMESTAMPDIFF(YEAR, data_nascimento, CURDATE()) BETWEEN 0 AND 6 THEN '0-6 anos' " +
                "        WHEN TIMESTAMPDIFF(YEAR, data_nascimento, CURDATE()) BETWEEN 7 AND 12 THEN '7-12 anos' " +
                "        WHEN TIMESTAMPDIFF(YEAR, data_nascimento, CURDATE()) BETWEEN 13 AND 18 THEN '13-18 anos' " +
                "    END AS faixa_etaria, " +
                "    COUNT(id_crianca) AS total_criancas " +
                "FROM crianca " +
                "WHERE " +
                "    data_saida IS NULL " +
                "    AND TIMESTAMPDIFF(YEAR, data_nascimento, CURDATE()) BETWEEN ? AND ? " +
                "GROUP BY faixa_etaria " +
                "HAVING faixa_etaria IS NOT NULL " + // Garante que faixas nulas não apareçam
                "ORDER BY MIN(TIMESTAMPDIFF(YEAR, data_nascimento, CURDATE()));";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idadeMin);
            pstm.setInt(2, idadeMax);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    RelatorioIdadeCriancaDTO dto = new RelatorioIdadeCriancaDTO();
                    dto.setFaixaEtaria(rs.getString("faixa_etaria"));
                    dto.setTotalCriancas(rs.getInt("total_criancas"));
                    relatorio.add(dto);
                }
            }
        }
        return relatorio;
    }
}
