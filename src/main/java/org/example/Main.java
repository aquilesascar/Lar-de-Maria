package org.example;

import org.example.View.TelaConsultaCrianca;
import org.example.View.TelaPrincipal;
import org.example.View.TelaRelatorioNecessidades;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));

        // Garante que a interface gráfica seja executada na thread de eventos da AWT (EDT)

        new TelaConsultaCrianca().setVisible(true);
        new TelaRelatorioNecessidades().setVisible(true);
    }
}

