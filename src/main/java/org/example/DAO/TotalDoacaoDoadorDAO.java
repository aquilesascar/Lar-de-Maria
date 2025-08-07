package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.DoadorTotalDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TotalDoacaoDoadorDAO {
    public List<DoadorTotalDTO> TotalDoacaoDoador() throws SQLException {
        // A sua consulta SQL já estava correta para o seu banco
        String sql = "SELECT d.nome_completo, ROUND(SUM(df.valor_total), 2) AS total_doacao \n" +
                "FROM doacaofinanceira_trabalhovoluntario_doacaomaterial df\n" +
                "JOIN doadaor_pessoafisica_pessoajuridica d ON df.id_doador = d.id_doador\n" +
                "WHERE df.valor_total IS NOT NULL\n" +
                "GROUP BY d.nome_completo\n" +
                "ORDER BY total_doacao DESC";

        List<DoadorTotalDTO> listaDoadorTotal = new ArrayList<>();
        ConexaoMySQL conexao = new ConexaoMySQL();
        try (
                Connection conn = conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                // CORREÇÃO: executeQuery() não precisa do argumento 'sql'
                ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                String nome_completo = rs.getString("nome_completo");
                float total_doacao = rs.getFloat("total_doacao");
                listaDoadorTotal.add(new DoadorTotalDTO(nome_completo, total_doacao));
            }
        }

        return listaDoadorTotal;
    }
}
