package org.example.View;

import org.example.DAO.CampanhaDAO;
import org.example.DTO.CampanhaDTO;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaCadastroCampanha extends JFrame {

    private JTextField txtNomeCampanha, txtMetaArrecadacao, txtDataInicio, txtDataFinal;
    private JTextArea txtMetaDescricao;
    private JButton btnSalvar, btnLimpar;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TelaCadastroCampanha() {
        setTitle("Cadastro de Campanha");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome da campanha
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nome da Campanha:"), gbc);
        gbc.gridx = 1;
        txtNomeCampanha = new JTextField(20);
        formPanel.add(txtNomeCampanha, gbc);

        // Meta de arrecadação
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Meta de Arrecadação (opcional):"), gbc);
        gbc.gridx = 1;
        txtMetaArrecadacao = new JTextField(20);
        formPanel.add(txtMetaArrecadacao, gbc);

        // Meta descrição
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Descrição da Meta (opcional):"), gbc);
        gbc.gridx = 1;
        txtMetaDescricao = new JTextArea(3, 20);
        txtMetaDescricao.setLineWrap(true);
        txtMetaDescricao.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtMetaDescricao);
        formPanel.add(scroll, gbc);

        // Data de início
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Data de Início (dd/mm/aaaa):"), gbc);
        gbc.gridx = 1;
        txtDataInicio = new JTextField(10);
        formPanel.add(txtDataInicio, gbc);

        // Data final
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Data Final (dd/mm/aaaa):"), gbc);
        gbc.gridx = 1;
        txtDataFinal = new JTextField(10);
        formPanel.add(txtDataFinal, gbc);

        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnLimpar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> salvarCampanha());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void salvarCampanha() {
        try {
            String nome = txtNomeCampanha.getText().trim();
            String metaStr = txtMetaArrecadacao.getText().trim();
            String descricao = txtMetaDescricao.getText().trim();
            Date inicio = sdf.parse(txtDataInicio.getText().trim());
            Date fim = sdf.parse(txtDataFinal.getText().trim());

            if (!validarCampos(nome, inicio, fim)) {
                JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Integer meta = null;
            if (!metaStr.isEmpty()) {
                meta = Integer.parseInt(metaStr);
            }

            CampanhaDTO campanha = new CampanhaDTO(nome, meta, descricao, inicio, fim);

            new CampanhaDAO().cadastrarCampanha(campanha);

            JOptionPane.showMessageDialog(this, "Campanha cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/mm/aaaa.", "Erro de Data", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Meta de arrecadação deve ser um número inteiro.", "Erro de Número", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível cadastrar a campanha.", "Erro de Campanha", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private boolean validarCampos(String nome, Date inicio, Date fim) {
        return !nome.isEmpty() && inicio != null && fim != null;
    }

    private void limparCampos() {
        txtNomeCampanha.setText("");
        txtMetaArrecadacao.setText("");
        txtMetaDescricao.setText("");
        txtDataInicio.setText("");
        txtDataFinal.setText("");
        txtNomeCampanha.requestFocus();
    }
}
