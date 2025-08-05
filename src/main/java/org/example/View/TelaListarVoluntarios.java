package org.example.View;

import org.example.DAO.ConsultaDiasVoluntarioDAO;
import org.example.DTO.ConsultaDiasVoluntario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class TelaListarVoluntarios extends JFrame {

    private JTable tabelaVoluntarios;
    private JButton botaoAtualizar;
    private JScrollPane scrollPane;
    private JPanel painelPrincipal;

    public TelaListarVoluntarios() {
        initComponents();
        carregarDadosNaTabela();
    }

    private void initComponents() {
        setTitle("Relatório de Voluntários");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        painelPrincipal = new JPanel(new BorderLayout());

        // Configuração da JTable e do JScrollPane
        tabelaVoluntarios = new JTable();
        scrollPane = new JScrollPane(tabelaVoluntarios);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a lista
        botaoAtualizar = new JButton("Atualizar Dados");
        botaoAtualizar.addActionListener(this::botaoAtualizarActionPerformed);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAtualizar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void carregarDadosNaTabela() {
        ConsultaDiasVoluntarioDAO dao = new ConsultaDiasVoluntarioDAO();
        try {
            // Busca a lista de voluntários no banco de dados
            List<ConsultaDiasVoluntario> lista = dao.consultaDiasVoluntario();

            // Cria um modelo de tabela para exibir os dados
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Nome do Voluntário");
            modelo.addColumn("Dia da Semana");

            // Itera sobre a lista de DTOs e adiciona cada objeto como uma linha na tabela
            for (ConsultaDiasVoluntario voluntario : lista) {
                modelo.addRow(new Object[]{
                        voluntario.getNome(),
                        voluntario.getDia()
                });
            }

            // Atribui o modelo à JTable
            tabelaVoluntarios.setModel(modelo);

        } catch (SQLException e) {
            // Em caso de erro, exibe uma mensagem amigável
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botaoAtualizarActionPerformed(ActionEvent evt) {
        carregarDadosNaTabela();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaListarVoluntarios().setVisible(true);
        });
    }
}