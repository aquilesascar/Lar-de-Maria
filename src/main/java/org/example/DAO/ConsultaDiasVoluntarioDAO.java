package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.ConsultaDiasVoluntario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDiasVoluntarioDAO {

    public List<ConsultaDiasVoluntario> consultaDiasVoluntario() throws SQLException {
        // A sua consulta SQL já estava correta para o seu banco
        String sql = "select m.nome_completo as nome, dia.dia_semana as dia " +
                "from membroequipe as m " +
                "join dia_semana_voluntario as dia on m.id_doador_voluntario = dia.id_doador " +
                "group by m.nome_completo, dia.dia_semana";

        List<ConsultaDiasVoluntario> listaDeVoluntarios = new ArrayList<>();
        ConexaoMySQL conexao = new ConexaoMySQL();

        try (
                Connection conn = conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                // CORREÇÃO: executeQuery() não precisa do argumento 'sql'
                ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                String dia = rs.getString("dia");
                String nome = rs.getString("nome");
                listaDeVoluntarios.add(new ConsultaDiasVoluntario(nome, dia));
            }

        }

        return listaDeVoluntarios;
    }
}