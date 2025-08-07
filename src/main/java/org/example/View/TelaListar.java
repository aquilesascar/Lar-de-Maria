package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaListar extends JFrame {
    public TelaListar() {
        setTitle("Cadastro");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnListarCampanhas = new JButton("Listar Campanhas");
        btnListarCampanhas.addActionListener((ActionEvent e) -> {
            new TelaListarCampanhas().setVisible(true);
        });
        add(btnListarCampanhas);

        JButton btnListarVoluntarios = new JButton("Dias de trabalho do Voluntario");
        btnListarVoluntarios.addActionListener((ActionEvent e) -> {
            new TelaListarVoluntarios().setVisible(true);
        });
        add(btnListarVoluntarios);

        JButton btnMediaDoacoes = new JButton("Doações Anuais");
        btnMediaDoacoes.addActionListener((ActionEvent e) -> {
            new TelaMediaDoacaoAnual().setVisible(true);
        });
        add(btnMediaDoacoes);

        JButton btnTotalPorDoador = new JButton("Total por Doador");
        btnTotalPorDoador.addActionListener(e -> new TelaTotalDoacaoDoador().setVisible(true));
        add(btnTotalPorDoador);

    }
}
