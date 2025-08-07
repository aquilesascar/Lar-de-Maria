package org.example.DAO;

import org.example.Connection.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.DAO.DiaSemanaVoluntarioDAO;
import org.example.DTO.DiaSemanaVoluntarioDTO;

public class DiaSemanaVoluntarioDAO {

    public void cadastrarDisponibilidade(DiaSemanaVoluntarioDTO dia_semana ) throws SQLException {
        String sql = "INSERT INTO dia_semana_voluntario(id_doador,dia_semana, turno) VALUES(?,?,?)";
        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection con = conexao.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        try {

            stmt.setInt(1, dia_semana.getId_doador());
            stmt.setString(2, dia_semana.getDia_semana());
            stmt.setString(3, dia_semana.getTurno());
            stmt.executeUpdate();

        }finally {
            stmt.close();
            con.close();
        }



    }
}