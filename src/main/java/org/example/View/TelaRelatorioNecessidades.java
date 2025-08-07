package org.example.View;

import org.example.DAO.RelatorioNecessidadeDAO;
import org.example.DTO.RelatorioNecessidadeDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class TelaRelatorioNecessidades extends JFrame {

    private JTable tabelaRelatorio;
    private JButton botaoAtualizar;
    private JScrollPane scrollPane;
    private JPanel painelPrincipal;

    public TelaRelatorioNecessidades() {
        initComponents();
        carregarDadosNaTabela();
    }

    private void initComponents() {
        setTitle("Relatório de Necessidades por Criança");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        painelPrincipal = new JPanel(new BorderLayout());

        tabelaRelatorio = new JTable();
        scrollPane = new JScrollPane(tabelaRelatorio);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        botaoAtualizar = new JButton("Atualizar Dados");
        botaoAtualizar.addActionListener(this::botaoAtualizarActionPerformed);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAtualizar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void carregarDadosNaTabela() {
        RelatorioNecessidadeDAO dao = new RelatorioNecessidadeDAO();
        try {
            List<RelatorioNecessidadeDTO> lista = dao.getRelatorioStatusPorCrianca();

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Nome da Criança");
            modelo.addColumn("Status da Necessidade");
            modelo.addColumn("Quantidade");

            for (RelatorioNecessidadeDTO item : lista) {
                modelo.addRow(new Object[]{
                        item.getNomeCrianca(),
                        item.getStatus(),
                        item.getTotalPorStatus()
                });
            }

            tabelaRelatorio.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados do relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void botaoAtualizarActionPerformed(ActionEvent evt) {
        carregarDadosNaTabela();
    }

}
