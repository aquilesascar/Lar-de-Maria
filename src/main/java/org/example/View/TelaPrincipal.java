package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

    public  TelaPrincipal() {
        setTitle("Sistema Lar de Maria");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(6, 1, 10, 10));

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

        /*
        JButton btnDoador = new JButton("Cadastrar Doador");
        btnDoador.addActionListener((ActionEvent e) -> {
            //new TelaCadastroDoador().setVisible(true);
        });
        painel.add(btnDoador);
         */

        JButton btnNecessidadeEspecifica = new JButton("Registrar Necessidade");
        btnNecessidadeEspecifica.addActionListener((ActionEvent e) -> {
            new TelaNecessidadeEspecifica().setVisible(true);
        });
        painel.add(btnNecessidadeEspecifica);

        JButton btnRegistrarEntrega = new JButton("Registrar Entrega");
        btnRegistrarEntrega.addActionListener((ActionEvent e) -> {
            new TelaRegistrarEntrega().setVisible(true);
        });
        painel.add(btnRegistrarEntrega);

        JButton btnRegistrarDoacao = new JButton("Registrar Doação");
        btnRegistrarDoacao.addActionListener((ActionEvent e) -> {
            new TelaCadastroDoacao().setVisible(true);
        });
        painel.add(btnRegistrarDoacao);

        JButton btnListarCampanhas = new JButton("Listar Campanhas");
        btnListarCampanhas.addActionListener((ActionEvent e) -> {
            new TelaListarCampanhas().setVisible(true);
        });
        painel.add(btnListarCampanhas);

        JButton btnListarVoluntarios = new JButton("Dias de trabalho do Voluntario");
        btnListarVoluntarios.addActionListener((ActionEvent e) -> {
            new TelaListarVoluntarios().setVisible(true);
        });
        painel.add(btnListarVoluntarios);

        add(painel);
        setVisible(true);
    }
}
