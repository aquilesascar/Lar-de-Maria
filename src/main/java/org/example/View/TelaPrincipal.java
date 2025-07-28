package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema Lar de Maria");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(titulo);

        // Botão Campanha
        JButton btnCampanha = new JButton("Cadastrar Campanha");
        btnCampanha.addActionListener((ActionEvent e) -> {
            new TelaCadastroCampanha().setVisible(true);
        });
        painel.add(btnCampanha);

        // Botão Criança
        JButton btnCrianca = new JButton("Cadastrar Criança");
        btnCrianca.addActionListener((ActionEvent e) -> {
            new TelaCadastroCrianca().setVisible(true);
        });
        painel.add(btnCrianca);

        add(painel);
    }
}
