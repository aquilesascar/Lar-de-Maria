package org.example.DAO;

import org.example.Connection.ConexaoMySQL;
import org.example.DTO.ConsultaCampanha;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaCampanhaDAO {

    public List<ConsultaCampanha> getConsultaCampanha() throws SQLException {
        String sql = "select c.nome_campanha, round(sum(d.valor_total),2) as valorTotal, " +
                "count(distinct n.idCrianca) as totalCriancas " +
                "from campanha as c " +
                "join doacaofinanceira_trabalhovoluntario_doacaomaterial as d " +
                "on c.id_campanha = d.id_campanha " +
                "join alocacao_doacao as a on d.id_doacao = a.id_doacao " +
                "join alocacaorecurso as al on a.id_alocacao = al.id_alocacao " +
                "join necessidadeespecifica as n on al.id_necessidade = n.id_necessidade " +
                "where d.tipo_docao = 'Doação Financeira' " +
                "group by c.nome_campanha";

        List<ConsultaCampanha> listaDeCampanhas = new ArrayList<>();
        ConexaoMySQL con = new ConexaoMySQL();

        try (Connection conn = con.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome_campanha = rs.getString("nome_campanha");
                float valor_total = rs.getFloat("valorTotal");
                int quantidade_criancas = rs.getInt("totalCriancas");

                // Cria o objeto DTO e adiciona à lista
                ConsultaCampanha campanha = new ConsultaCampanha(nome_campanha, valor_total, quantidade_criancas);
                listaDeCampanhas.add(campanha);
            }

        } // O try-with-resources fecha os recursos automaticamente

        return (ArrayList<ConsultaCampanha>) listaDeCampanhas;
    }
}
