package org.example.View;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {
    public TelaCadastro() {
        setTitle("Cadastro");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnCrianca = new JButton("Cadastrar Criança");
        btnCrianca.addActionListener(e -> new TelaCadastroCrianca().setVisible(true));

        JButton btnCampanha = new JButton("Cadastrar Campanha");
        btnCampanha.addActionListener(e -> new TelaCadastroCampanha().setVisible(true));

        JButton btnMembroEquipe = new JButton("Cadastrar Membro da Equipe");
        btnMembroEquipe.addActionListener(e -> new TelaCadastroMembroEquipe().setVisible(true));

        add(btnCrianca);
        add(btnCampanha);
        add(btnMembroEquipe);

        setVisible(true);
    }
}
