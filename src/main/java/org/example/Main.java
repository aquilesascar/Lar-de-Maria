package org.example;

import org.example.View.TelaLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
            // Garante que a interface gráfica seja executada na Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new TelaLogin().setVisible(true);
                }
            });
        }
}
