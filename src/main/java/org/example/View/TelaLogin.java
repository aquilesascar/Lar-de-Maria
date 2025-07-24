package org.example.View;

import org.example.Connection.ConexaoMySQL;
import org.example.DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//IMAGINANDO UM BOTÃO DE LOGIN SOMENTE COMO EXEMPLO, EXEMPLO!!!!!!

public class TelaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public TelaLogin() {
        // --- CONFIGURAÇÕES DA JANELA ---
        setTitle("Tela de Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);

        // --- PAINEL PRINCIPAL COM GRIDBAGLAYOUT ---
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- COMPONENTES DA TELA ---
        // Rótulo Usuário
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Usuário:"), gbc);

        // Campo de Texto Usuário
        txtUsuario = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(txtUsuario, gbc);

        // Rótulo Senha
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Senha:"), gbc);

        // Campo de Senha
        txtSenha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(txtSenha, gbc);

        // Botão de Login
        btnLogin = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnLogin, gbc);

        // Adiciona o painel à janela
        add(panel);

        // --- AÇÃO DO BOTÃO DE LOGIN ---
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String nomeUsuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        if (nomeUsuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.checkLogin(nomeUsuario, senha)) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
            // Aqui você pode fechar a tela de login e abrir a tela principal do sistema
            // Ex: new TelaPrincipal().setVisible(true);
            // this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}