package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

    public  TelaPrincipal() {
        setTitle("Sistema Lar de Maria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(titulo);

        JButton btnCadastro = new JButton("Cadastrar");
        btnCadastro.addActionListener(e -> new TelaCadastro());

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> new TelaAtualizacao());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> new TelaExclusao());

        JButton btnRelatorios = new JButton("Relatórios");
        btnRelatorios.addActionListener(e -> new TelaRelatorios());

        painel.add(btnCadastro);
        painel.add(btnAtualizar);
        painel.add(btnExcluir);
        painel.add(btnRelatorios);

        add(painel);
        setVisible(true);

    }

}
