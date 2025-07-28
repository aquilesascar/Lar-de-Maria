package org.example.View;

import org.example.DAO.CampanhaDAO;
import org.example.DTO.CampanhaDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaCadastroCampanha extends JFrame {

    private JTextField txtNomeCampanha, txtMetaArrecadacao, txtValorArrecadado, txtDataInicio, txtDataFinal;
    private JButton btnSalvar, btnLimpar;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TelaCadastroCampanha() {
        setTitle("Cadastro de Campanha");
        setSize(500, 400);
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
        formPanel.add(new JLabel("Meta de Arrecadação:"), gbc);
        gbc.gridx = 1;
        txtMetaArrecadacao = new JTextField(20);
        formPanel.add(txtMetaArrecadacao, gbc);

        // Valor arrecadado
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Valor Arrecadado:"), gbc);
        gbc.gridx = 1;
        txtValorArrecadado = new JTextField(20);
        formPanel.add(txtValorArrecadado, gbc);

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
            String meta = txtMetaArrecadacao.getText().trim();
            float valor = Float.parseFloat(txtValorArrecadado.getText().trim());
            Date inicio = sdf.parse(txtDataInicio.getText().trim());
            Date fim = sdf.parse(txtDataFinal.getText().trim());

            if (!validarCampos(nome, meta, valor)) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CampanhaDTO campanha = new CampanhaDTO(fim, inicio, meta, valor, nome);
            new CampanhaDAO().cadastrarCampanha(campanha);

            JOptionPane.showMessageDialog(this, "Campanha cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/mm/aaaa.", "Erro de Data", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor arrecadado deve ser um número.", "Erro de Número", JOptionPane.ERROR_MESSAGE);
        } catch (Error e) {
            JOptionPane.showMessageDialog(this, "Nao foi possivel cadastrar a campanha", "Erro de Campanha", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos(String nome, String meta, float valor) {
        if (nome.isEmpty() || meta.isEmpty()) {
            return false;
        }
        else if(valor <= 0){
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtNomeCampanha.setText("");
        txtMetaArrecadacao.setText("");
        txtValorArrecadado.setText("");
        txtDataInicio.setText("");
        txtDataFinal.setText("");
        txtNomeCampanha.requestFocus();
    }
}
