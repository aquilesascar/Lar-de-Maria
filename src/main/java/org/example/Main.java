package org.example;

import org.example.View.TelaCadastroCrianca;
import org.example.View.TelaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread de eventos da AWT (EDT)
       TelaPrincipal telaPrincipal = new TelaPrincipal();
       telaPrincipal.setVisible(true);
    }
}

