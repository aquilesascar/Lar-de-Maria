package org.example;

import org.example.View.TelaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //so pra
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));

        // Garante que a interface gráfica seja executada na thread de eventos da AWT (EDT)

    }
}

