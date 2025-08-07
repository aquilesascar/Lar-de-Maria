package org.example.View;

import org.example.DAO.TotalDoacaoDoadorDAO;
import org.example.DTO.DoadorTotalDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaTotalDoacaoDoador extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaTotalDoacaoDoador() {
        setTitle("Total de Doações por Doador");
        setSize(500, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new String[]{"Doador", "Total Doado (R$)"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel rodape = new JPanel();
        rodape.add(btnFechar);
        add(rodape, BorderLayout.SOUTH);

        carregarDados();

        setVisible(true);
    }

    private void carregarDados() {
        try {
            TotalDoacaoDoadorDAO dao = new TotalDoacaoDoadorDAO();
            List<DoadorTotalDTO> lista = dao.TotalDoacaoDoador();

            modelo.setRowCount(0); // limpa a tabela

            for (DoadorTotalDTO d : lista) {
                modelo.addRow(new Object[]{
                        d.getNome(),
                        String.format("R$ %.2f", d.getTotal_doacao())
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar doações: " + e.getMessage());
        }
    }
}
