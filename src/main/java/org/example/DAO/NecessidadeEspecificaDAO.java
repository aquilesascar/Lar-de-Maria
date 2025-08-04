package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.NecessidadeEspecificaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NecessidadeEspecificaDAO {

    public void registrarAtendimento(NecessidadeEspecificaDTO dto) throws SQLException {
        String sql = "INSERT INTO necessidadeespecifica (idCrianca, descricao, status) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, dto.getIdCrianca());
            pstm.setString(2, dto.getDescricao());
            pstm.setString(3, dto.getStatus());

            pstm.executeUpdate();
        }
    }
}
