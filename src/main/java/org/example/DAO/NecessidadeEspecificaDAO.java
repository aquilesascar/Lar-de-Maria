package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.NecessidadeEspecificaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NecessidadeEspecificaDAO {

    public void registrarAtendimento(NecessidadeEspecificaDTO dto) throws SQLException {
        String sql = "INSERT INTO necessidade_especifica (idCrianca, idAlocacaoRecurso, descricao, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, dto.getIdCrianca());
            pstm.setInt(2, dto.getIdAlocacaoRecurso());
            pstm.setString(3, dto.getDescricao());
            pstm.setString(4, dto.getStatus());

            pstm.executeUpdate();
        }
    }

}
