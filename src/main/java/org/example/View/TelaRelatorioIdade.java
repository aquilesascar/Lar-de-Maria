package org.example.View;

import org.example.DAO.RelatorioIdadeCriancaDAO;
import org.example.DTO.RelatorioIdadeCriancaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaRelatorioIdade extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaRelatorioIdade() {
        super("Relatório de Crianças por Faixa Etária");

        initComponents();
        carregarDados(); // Carrega dados diretamente sem filtros
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Tabela de Resultados (Centro) ---
        String[] colunas = {"Faixa Etária", "Quantidade de Crianças"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarDados() {
        try {
            // Usa valores fixos de idade
            int idadeMin = 0;
            int idadeMax = 18;

            modeloTabela.setRowCount(0);

            RelatorioIdadeCriancaDAO dao = new RelatorioIdadeCriancaDAO();
            List<RelatorioIdadeCriancaDTO> lista = dao.getRelatorioPorIdade(idadeMin, idadeMax);

            for (RelatorioIdadeCriancaDTO item : lista) {
                modeloTabela.addRow(new Object[]{
                        item.getFaixaEtaria(),
                        item.getTotalCriancas()
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao buscar dados do relatório: " + ex.getMessage(),
                    "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaRelatorioIdade().setVisible(true));
    }
}
