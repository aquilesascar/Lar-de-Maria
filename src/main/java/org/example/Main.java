package org.example;

import org.example.View.TelaCadastroCrianca;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread de eventos da AWT (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Cria uma instância da sua tela de cadastro
                TelaCadastroCrianca telaCadastro = new TelaCadastroCrianca();

                // 2. Torna a tela visível
                telaCadastro.setVisible(true);




            }
        });
    }
}

