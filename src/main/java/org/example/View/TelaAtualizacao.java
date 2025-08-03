package org.example.View;

import javax.swing.*;
import java.awt.*;

public class TelaAtualizacao extends JFrame {
    public TelaAtualizacao() {
        setTitle("Atualização");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnAtualizarMembro = new JButton("Atualizar Membro da Equipe");
        btnAtualizarMembro.addActionListener(e -> new TelaAtualizarMembroEquipe().setVisible(true));
        add(btnAtualizarMembro);

        JButton btnAtualizarCrianca = new JButton("Atualizar Crianca");
        btnAtualizarCrianca.addActionListener(e -> new TelaAtualizarCrianca().setVisible(true));
        add(btnAtualizarCrianca);

        setVisible(true);
    }
}
