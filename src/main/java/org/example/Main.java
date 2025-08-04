package org.example;
import org.example.View.TelaNecessidadeEspecifica;

import org.example.View.TelaRegistrarEntrega;

public class Main {

    public static void main(String[] args) {
        new TelaNecessidadeEspecifica().setVisible(true);
        new TelaRegistrarEntrega().setVisible(true);

        // Garante que a interface gráfica seja executada na thread de eventos da AWT (EDT)

    }
}

