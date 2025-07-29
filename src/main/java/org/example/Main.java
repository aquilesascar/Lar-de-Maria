package org.example;
import org.example.View.TelaAtenderNecessidade;
import org.example.View.TelaCadastroCrianca;

import org.example.View.TelaCadastroCampanha;
import org.example.View.TelaRegistrarEntrega;
import org.example.View.TelaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new TelaAtenderNecessidade().setVisible(true);
        new TelaRegistrarEntrega().setVisible(true);
        new TelaCadastroCrianca().setVisible(true);
        // Garante que a interface gráfica seja executada na thread de eventos da AWT (EDT)
      
       TelaPrincipal telaPrincipal = new TelaPrincipal();
       telaPrincipal.setVisible(true);
    }
}

