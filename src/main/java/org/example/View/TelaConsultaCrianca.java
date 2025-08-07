package org.example.View;

import org.example.DAO.ConsultaCriancaDAO;
import org.example.DTO.RelatorioCriancaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List; // CORREÇÃO: Importa a lista correta (java.util.List)

public class TelaConsultaCrianca extends JFrame {
    private JTable tabelaRelatorio;
    private JButton botaoAtualizar;
    private JScrollPane scrollPane;
    private JPanel painelPrincipal;

    public TelaConsultaCrianca() {
        initComponents();
        carregarDadosNaTabela();
    }

    private void initComponents() {
        setTitle("Relatório Demográfico de Crianças");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
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
        ConsultaCriancaDAO dao = new ConsultaCriancaDAO();
        try {
            List<RelatorioCriancaDTO> lista = dao.getRelatorioDemografico();

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Faixa Etária");
            modelo.addColumn("Gênero");
            modelo.addColumn("Total de Crianças");

            for (RelatorioCriancaDTO item : lista) {
                modelo.addRow(new Object[]{
                        item.getFaixaEtaria(),
                        item.getGenero(),
                        item.getTotalCriancas()
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
