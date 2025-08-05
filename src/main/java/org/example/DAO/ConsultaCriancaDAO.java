package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.RelatorioCriancaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaCriancaDAO {
    /**
     * Busca no banco de dados um relatório demográfico das crianças acolhidas,
     * agrupadas por faixa etária e gênero.
     * @return Uma lista de RelatorioCriancaDTO com os dados agregados.
     * @throws SQLException
     */
    public List<RelatorioCriancaDTO> getRelatorioDemografico() throws SQLException {
        List<RelatorioCriancaDTO> relatorio = new ArrayList<>();

        // Esta query SQL calcula a idade, define as faixas etárias usando CASE,
        // e agrupa os resultados para contagem.
        String sql = "SELECT\n" +
                "    CASE\n" +
                "        WHEN TIMESTAMPDIFF(YEAR, C.data_nascimento, CURDATE()) <= 2 THEN '0 a 2 anos'\n" +
                "        WHEN TIMESTAMPDIFF(YEAR, C.data_nascimento, CURDATE()) BETWEEN 3 AND 5 THEN '3 a 5 anos'\n" +
                "        WHEN TIMESTAMPDIFF(YEAR, C.data_nascimento, CURDATE()) BETWEEN 6 AND 11 THEN '6 a 11 anos'\n" +
                "        ELSE '12 a 18 anos'\n" +
                "    END AS FaixaEtaria,\n" +
                "    C.genero,\n" +
                "    COUNT(C.id_crianca) AS TotalCriancas\n" +
                "FROM CRIANCA AS C\n" +
                "GROUP BY\n" +
                "    FaixaEtaria, C.genero\n" +
                "ORDER BY\n" +
                "    FaixaEtaria ASC, TotalCriancas DESC;";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                RelatorioCriancaDTO dto = new RelatorioCriancaDTO();
                dto.setFaixaEtaria(rs.getString("faixa_etaria"));
                dto.setGenero(rs.getString("genero"));
                dto.setTotalCriancas(rs.getInt("total_criancas"));
                relatorio.add(dto);
            }
        }
        return relatorio;
    }
}

