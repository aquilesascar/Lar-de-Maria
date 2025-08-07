package org.example.View;

import org.example.DAO.RelatorioIdadeCriancaDAO;
import org.example.DTO.RelatorioIdadeCriancaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class TelaRelatorioIdade extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JSpinner spinnerIdadeMin, spinnerIdadeMax;
    private JButton botaoFiltrar;

    public TelaRelatorioIdade() {
        super("Relatório de Crianças por Faixa Etária");

        initComponents();

        carregarDados();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Painel de Filtros (Norte) ---
        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros"));

        painelFiltros.add(new JLabel("Idade de:"));
        // Spinner para idade mínima, de 0 a 18, começando em 0.
        spinnerIdadeMin = new JSpinner(new SpinnerNumberModel(0, 0, 18, 1));
        painelFiltros.add(spinnerIdadeMin);

        painelFiltros.add(new JLabel("até:"));
        // Spinner para idade máxima, de 0 a 18, começando em 18.
        spinnerIdadeMax = new JSpinner(new SpinnerNumberModel(18, 0, 18, 1));
        painelFiltros.add(spinnerIdadeMax);

        botaoFiltrar = new JButton("Filtrar");
        botaoFiltrar.addActionListener(this::botaoFiltrarActionPerformed);
        painelFiltros.add(botaoFiltrar);

        add(painelFiltros, BorderLayout.NORTH);

        String[] colunas = {"Faixa Etária", "Quantidade de Crianças"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarDados() {
        try {
            // Pega os valores dos spinners
            int idadeMin = (int) spinnerIdadeMin.getValue();
            int idadeMax = (int) spinnerIdadeMax.getValue();

            if (idadeMin > idadeMax) {
                JOptionPane.showMessageDialog(this, "A idade mínima não pode ser maior que a idade máxima.", "Erro de Filtro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Limpa a tabela antes de carregar novos dados
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

    private void botaoFiltrarActionPerformed(ActionEvent e) {
        // Ação do botão é simplesmente chamar o método para carregar os dados
        carregarDados();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaRelatorioIdade().setVisible(true));
    }
}
