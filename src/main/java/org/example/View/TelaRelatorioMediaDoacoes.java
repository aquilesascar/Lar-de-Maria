package org.example.View;

import org.example.Connection.ConexaoMySQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaRelatorioMediaDoacoes extends JFrame {

    private JTable tabela;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabela;


    // Sua consulta SQL
    private static final String SQL_QUERY = "SELECT " +
            "tipo_docao AS 'Tipo de Doação', " +
            "AVG(" +
            "   CASE " +
            "       WHEN tipo_docao = 'Doação Financeira' THEN valor_total " +
            "       WHEN tipo_docao = 'Doação de Item Material' THEN estimativa_valor " +
            "       ELSE 0 " +
            "   END" +
            ") AS 'Média de valor da Doação' " +
            "FROM " +
            "   doacaofinanceira_trabalhovoluntario_doacaomaterial " +
            "GROUP BY " +
            "   tipo_docao " +
            "ORDER BY " +
            "  2 DESC;";

    public TelaRelatorioMediaDoacoes() {
        super("Relatório de Média de Doações"); // Chamada para o construtor de JFrame

        // Configurações da janela
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null); // Centraliza a janela

        // Criação do modelo da tabela
        String[] colunas = {"Tipo de Doação", "Média de valor da Doação"};
        modeloTabela = new DefaultTableModel(colunas, 0);

        // Criação da tabela e do painel de rolagem
        tabela = new JTable(modeloTabela);
        scrollPane = new JScrollPane(tabela);

        // Adiciona a tabela à janela
        this.add(scrollPane, BorderLayout.CENTER);

        // Carrega os dados do banco de dados na tabela
        carregarDados();
    }

    private void carregarDados() {
        // Limpa a tabela antes de carregar novos dados
        modeloTabela.setRowCount(0);
        ConexaoMySQL con = new ConexaoMySQL();

        try (Connection conn = con.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_QUERY)) {

            while (rs.next()) {
                String tipoDoacao = rs.getString("Tipo de Doação");
                double mediaValor = rs.getDouble("Média de valor da Doação");

                // Adiciona uma nova linha ao modelo da tabela
                modeloTabela.addRow(new Object[]{tipoDoacao, String.format("%.2f", mediaValor)});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao conectar ou buscar dados do banco: " + ex.getMessage(),
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Executa a interface gráfica na thread de despacho de eventos
        SwingUtilities.invokeLater(() -> {
            new TelaRelatorioMediaDoacoes().setVisible(true);
        });
    }
}