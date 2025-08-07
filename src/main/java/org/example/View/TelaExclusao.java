package org.example.View;

import javax.swing.*;
import java.awt.*;

public class TelaExclusao extends JFrame {
    public TelaExclusao() {
        setTitle("Exclusão");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnExcluirMembro = new JButton("Excluir Membro da Equipe");
        btnExcluirMembro.addActionListener(e -> new TelaExcluirMembroEquipe().setVisible(true));

        add(btnExcluirMembro);

        setVisible(true);
    }
}
