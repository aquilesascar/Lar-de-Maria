package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {
    public TelaCadastro() {
        setTitle("Cadastro");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 10, 10));

        JButton btnCrianca = new JButton("Cadastrar Criança");
        btnCrianca.addActionListener(e -> new TelaCadastroCrianca().setVisible(true));

        JButton btnCampanha = new JButton("Cadastrar Campanha");
        btnCampanha.addActionListener(e -> new TelaCadastroCampanha().setVisible(true));

        JButton btnMembroEquipe = new JButton("Cadastrar Membro da Equipe");
        btnMembroEquipe.addActionListener(e -> new TelaCadastroMembroEquipe().setVisible(true));

        JButton btnDoador = new JButton("Cadastrar Doador");
        btnDoador.addActionListener((ActionEvent e) -> {
            new TelaCadastroDoador().setVisible(true);
        });
        add(btnDoador);

        JButton btnNecessidadeEspecifica = new JButton("Registrar Necessidade");
        btnNecessidadeEspecifica.addActionListener((ActionEvent e) -> {
            new TelaNecessidadeEspecifica().setVisible(true);
        });
        add(btnNecessidadeEspecifica);

        JButton btnRegistrarEntrega = new JButton("Registrar Entrega");
        btnRegistrarEntrega.addActionListener((ActionEvent e) -> {
            new TelaRegistrarEntrega().setVisible(true);
        });
        add(btnRegistrarEntrega);

        JButton btnRegistrarDoacao = new JButton("Registrar Doação");
        btnRegistrarDoacao.addActionListener((ActionEvent e) -> {
            new TelaCadastroDoacao().setVisible(true);
        });
        add(btnRegistrarDoacao);


        add(btnCrianca);
        add(btnCampanha);
        add(btnMembroEquipe);

        setVisible(true);
    }
}
