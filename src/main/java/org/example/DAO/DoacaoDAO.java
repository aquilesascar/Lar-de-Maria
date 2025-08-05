package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.DoacaoDTO;
import org.example.DTO.DoacaoFinanceiraDTO;
import org.example.DTO.DoacaoItemMaterialDTO;
import org.example.DTO.TrabalhoVoluntarioDTO;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DoacaoDAO extends Component {
    public void cadastrarDoacao(DoacaoDTO doacao) throws SQLException {
        String sql = "INSERT INTO doacaofinanceira_trabalhovoluntario_doacaomaterial (" +
                "data, id_doador, forma_pagamento, valor_total, descricao_servico, " +
                "descricao_detalhada, estado_conservacao, quantidade, estimativa_valor, " +
                "id_campanha, tipo_docao, data_inicio, data_final) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        ConexaoMySQL conexao = new ConexaoMySQL();
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.getConnection();
            stmt = con.prepareStatement(sql);

            // 1. Preenche os campos obrigatórios (sempre presentes)
            stmt.setDate(1, java.sql.Date.valueOf(doacao.getData()));

            // 2. Preenche os IDs opcionais (id_doador e id_campanha)
            if (doacao.getId_doador() != null) {
                stmt.setInt(2, doacao.getId_doador());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            if (doacao.getId_campanha() != null) {
                stmt.setInt(10, doacao.getId_campanha());
            } else {
                stmt.setNull(10, Types.INTEGER);
            }

            // 3. Preenche todos os campos específicos como nulos por padrão
            // Isso evita que valores de um tipo de doação 'sujem' outro tipo
            stmt.setNull(3, Types.VARCHAR); // forma_pagamento
            stmt.setNull(4, Types.FLOAT);   // valor_total
            stmt.setNull(5, Types.VARCHAR); // descricao_servico
            stmt.setNull(6, Types.VARCHAR); // descricao_detalhada
            stmt.setNull(7, Types.VARCHAR); // estado_conservacao
            stmt.setNull(8, Types.INTEGER); // quantidade
            stmt.setNull(9, Types.FLOAT);   // estimativa_valor
            stmt.setNull(12, Types.DATE);   // data_inicio
            stmt.setNull(13, Types.DATE);   // data_final

            // 4. Preenche os campos específicos de acordo com o tipo de doação
            if (doacao instanceof DoacaoFinanceiraDTO df) {
                stmt.setString(3, df.getForma_pagamento());
                stmt.setFloat(4, df.getValor_total());
            } else if (doacao instanceof DoacaoItemMaterialDTO dm) {
                stmt.setString(6, dm.getDescricao_detalhada());
                stmt.setString(7, dm.getEstado_conservacao());
                stmt.setInt(8, dm.getQuantidade());
                stmt.setFloat(9, dm.getEstimativa_valor());
            } else if (doacao instanceof TrabalhoVoluntarioDTO tv) {
                stmt.setString(5, tv.getDescricao_serviço());
                stmt.setDate(12, java.sql.Date.valueOf(tv.getData_inicio()));
                stmt.setDate(13, java.sql.Date.valueOf(tv.getData_final()));
            }

            // 5. Preenche o tipo de doação (comum a todos)
            stmt.setString(11, doacao.getTipo_doacao());

            stmt.executeUpdate();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}