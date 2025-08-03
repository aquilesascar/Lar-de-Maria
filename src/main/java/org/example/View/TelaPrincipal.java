package org.example.View;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema Lar de Maria");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel titulo = new JLabel("Bem-vindo ao sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(titulo);

        JButton btnCadastro = new JButton("Cadastrar");
        btnCadastro.addActionListener(e -> new TelaCadastro());

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> new TelaAtualizacao());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> new TelaExclusao());

        painel.add(btnCadastro);
        painel.add(btnAtualizar);
        painel.add(btnExcluir);

        add(painel);
        setVisible(true);
    }
}
