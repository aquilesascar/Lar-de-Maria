package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.ConsultaDiasVoluntario;
import org.example.DTO.DoacaoMesDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MediaDoacaoAnualDAO {

    public List<DoacaoMesDTO> MediaDoacaoAnualDAO() throws SQLException {
        // A sua consulta SQL já estava correta para o seu banco
        String sql = "SELECT MONTH(data) AS mes, ROUND(AVG(valor_total), 2) AS media_doacao\n" +
                "FROM doacaofinanceira_trabalhovoluntario_doacaomaterial\n" +
                "WHERE valor_total IS NOT NULL AND YEAR(data) = YEAR(CURDATE()) GROUP BY MONTH(data) ORDER BY mes";

        List<DoacaoMesDTO> listaMediaDeDoacoes = new ArrayList<>();
        ConexaoMySQL conexao = new ConexaoMySQL();
        try (
                Connection conn = conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                // CORREÇÃO: executeQuery() não precisa do argumento 'sql'
                ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                int mes = rs.getInt("mes");
                float media_doacao = rs.getFloat("media_doacao");
                listaMediaDeDoacoes.add(new DoacaoMesDTO(mes, media_doacao));
            }

        }

        return listaMediaDeDoacoes;
    }
}
