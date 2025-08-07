package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.RelatorioNecessidadeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioNecessidadeDAO {

    public List<RelatorioNecessidadeDTO> getRelatorioStatusPorCrianca() throws SQLException {
        List<RelatorioNecessidadeDTO> relatorio = new ArrayList<>();

        String sql = "SELECT " +
                "    c.nome AS nome_crianca, " +
                "    ne.status, " +
                "    COUNT(ne.id_necessidade) AS total_por_status " +
                "FROM crianca c " +
                "JOIN necessidadeespecifica ne ON c.id_crianca = ne.idCrianca " +
                "GROUP BY c.id_crianca, c.nome, ne.status " +
                "ORDER BY " +
                "    (SELECT COUNT(*) FROM necessidadeespecifica WHERE idCrianca = c.id_crianca) DESC, " +
                "    c.nome, " +
                "    ne.status;";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                RelatorioNecessidadeDTO dto = new RelatorioNecessidadeDTO();
                dto.setNomeCrianca(rs.getString("nome_crianca"));
                dto.setStatus(rs.getString("status"));
                dto.setTotalPorStatus(rs.getInt("total_por_status"));
                relatorio.add(dto);
            }
        }
        return relatorio;
    }
}
