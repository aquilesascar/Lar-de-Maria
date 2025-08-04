package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.DistribuicaoRecursoDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DistribuicaoRecursoDAO {

    public void registrarEntrega(DistribuicaoRecursoDTO dto) throws SQLException {
        String sql = "INSERT INTO distribuicaorecurso (idAlocacaoRecurso, id_crianca, idMembroEquipe, data_distribuicao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, dto.getIdAlocacaoRecurso());
            pstm.setInt(2, dto.getIdCrianca());
            pstm.setInt(3, dto.getIdMembroEquipe());
            pstm.setDate(4, Date.valueOf(dto.getDataDistribuicao()));
            pstm.executeUpdate();
        }
    }
}