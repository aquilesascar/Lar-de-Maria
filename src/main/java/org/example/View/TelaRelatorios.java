package org.example.View;

import javax.swing.*;
import java.awt.*;

public class TelaRelatorios extends javax.swing.JFrame {
    public TelaRelatorios() {
        setTitle("Relatórios");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnMediaDoacoes = new JButton("Relatório de Media de Doações");
        btnMediaDoacoes.addActionListener(e -> new TelaRelatorioMediaDoacoes().setVisible(true));
        JButton btnRelatorioIdade = new JButton("Relatório de Idade");
        btnRelatorioIdade.addActionListener(e -> new TelaRelatorioIdade().setVisible(true));

        JButton btnRelatorioNecessidades = new JButton("Relatorio de Necessidades");
        btnRelatorioNecessidades.addActionListener(e -> new TelaRelatorioNecessidades().setVisible(true));

        add(btnMediaDoacoes);
        setVisible(true);
        add(btnRelatorioIdade);
        setVisible(true);
        add(btnRelatorioNecessidades);
    }
}
