package org.example.View;

import org.example.DAO.ConsultaCampanhaDAO;
import org.example.DTO.ConsultaCampanha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class TelaListarCampanhas extends JFrame {

    private JTable tabelaCampanhas;
    private JButton botaoAtualizar;
    private JScrollPane scrollPane;
    private JPanel painelPrincipal;

    public TelaListarCampanhas() {
        initComponents();
        carregarDadosNaTabela();
    }

    private void initComponents() {
        setTitle("Relatório de Campanhas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        painelPrincipal = new JPanel(new BorderLayout());

        // Configuração da JTable, que exibirá os dados.
        // É importante colocá-la dentro de um JScrollPane para ter rolagem.
        tabelaCampanhas = new JTable();
        scrollPane = new JScrollPane(tabelaCampanhas);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botão para atualizar a lista de campanhas.
        botaoAtualizar = new JButton("Atualizar Dados");
        botaoAtualizar.addActionListener(this::botaoAtualizarActionPerformed);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoAtualizar);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void carregarDadosNaTabela() {
        ConsultaCampanhaDAO dao = new ConsultaCampanhaDAO();
        try {
            // Busca a lista de campanhas no banco de dados através do DAO.
            List<ConsultaCampanha> lista = dao.getConsultaCampanha();

            // Cria um modelo de tabela para organizar os dados em colunas.
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Nome da Campanha");
            modelo.addColumn("Valor Total Arrecadado");
            modelo.addColumn("Crianças Beneficiadas");

            // Itera sobre a lista de DTOs e adiciona cada objeto como uma linha na tabela.
            for (ConsultaCampanha campanha : lista) {
                modelo.addRow(new Object[]{
                        campanha.getNome_campanha(),
                        campanha.getValor_total(),
                        campanha.getQuant_crianca()
                });
            }

            // Atribui o modelo à sua JTable.
            tabelaCampanhas.setModel(modelo);

        } catch (SQLException e) {
            // Em caso de erro no banco de dados, exibe uma mensagem amigável.
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botaoAtualizarActionPerformed(ActionEvent evt) {
        carregarDadosNaTabela();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaListarCampanhas().setVisible(true);
        });
    }
}