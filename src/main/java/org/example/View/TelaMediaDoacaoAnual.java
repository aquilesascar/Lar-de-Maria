package org.example.View;

import org.example.DAO.MediaDoacaoAnualDAO;
import org.example.DTO.DoacaoMesDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaMediaDoacaoAnual extends JFrame {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaMediaDoacaoAnual() {
        setTitle("Média Mensal de Doações Financeiras - Ano Atual");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel(new String[]{"Mês", "Média de Doação (R$)"}, 0);
        tabela = new JTable(modeloTabela);
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
            MediaDoacaoAnualDAO dao = new MediaDoacaoAnualDAO();
            List<DoacaoMesDTO> lista = dao.MediaDoacaoAnualDAO();
            modeloTabela.setRowCount(0); // limpa a tabela

            for (DoacaoMesDTO d : lista) {
                modeloTabela.addRow(new Object[]{
                        getNomeMes(d.getMes()),
                        String.format("R$ %.2f", d.getValor_medio())
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private String getNomeMes(int mes) {
        String[] meses = {
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };
        return (mes >= 1 && mes <= 12) ? meses[mes - 1] : "Desconhecido";
    }
}
