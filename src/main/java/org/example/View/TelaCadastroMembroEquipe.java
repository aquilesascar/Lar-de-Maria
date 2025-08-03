package org.example.View;

import org.example.DAO.MembroEquipeDAO;
import org.example.DTO.MembroEquipeDTO;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaCadastroMembroEquipe extends JFrame {
    private JTextField txtNomeCompleto, txtCpf, txtEmail, txtTelefone, txtCargoFuncao, txtDataInicio, txtDataSaida;
    private JComboBox<String> cbTipoMembro;
    private JButton btnSalvar, btnLimpar;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TelaCadastroMembroEquipe() {
        setTitle("Cadastro de Membro da Equipe");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Nome
        gbc.gridx = 0; gbc.gridy = y;
        formPanel.add(new JLabel("Nome Completo:"), gbc);
        gbc.gridx = 1;
        txtNomeCompleto = new JTextField(20);
        formPanel.add(txtNomeCompleto, gbc);

        // CPF
        gbc.gridx = 0; gbc.gridy = ++y;
        formPanel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        txtCpf = new JTextField(20);
        formPanel.add(txtCpf, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = ++y;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);

        // Telefone
        gbc.gridx = 0; gbc.gridy = ++y;
        formPanel.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        txtTelefone = new JTextField(20);
        formPanel.add(txtTelefone, gbc);

        // Cargo/Funcao
        gbc.gridx = 0; gbc.gridy = ++y;
        formPanel.add(new JLabel("Cargo/Função:"), gbc);
        gbc.gridx = 1;
        txtCargoFuncao = new JTextField(20);
        formPanel.add(txtCargoFuncao, gbc);

        // Data de Início
        gbc.gridx = 0; gbc.gridy = ++y;
        formPanel.add(new JLabel("Data de Início (dd/mm/aaaa):"), gbc);
        gbc.gridx = 1;
        txtDataInicio = new JTextField(10);
        formPanel.add(txtDataInicio, gbc);

        // Data de Saída
        gbc.gridx = 0; gbc.gridy = ++y;
        formPanel.add(new JLabel("Data de Saída (dd/mm/aaaa) (opcional):"), gbc);
        gbc.gridx = 1;
        txtDataSaida = new JTextField(10);
        formPanel.add(txtDataSaida, gbc);

        // Tipo de Membro (Voluntário/Colaborador)
        gbc.gridx = 0; gbc.gridy = ++y;
        formPanel.add(new JLabel("Tipo de Membro:"), gbc);
        gbc.gridx = 1;
        cbTipoMembro = new JComboBox<>(new String[]{"", "Voluntário", "Colaborador"});
        formPanel.add(cbTipoMembro, gbc);

        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnLimpar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> salvarMembroEquipe());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void salvarMembroEquipe() {
        try {
            String nomeCompleto = txtNomeCompleto.getText().trim();
            String cpf = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();
            String telefone = txtTelefone.getText().trim();
            String cargoFuncao = txtCargoFuncao.getText().trim();
            Date inicio = sdf.parse(txtDataInicio.getText().trim());

            String dataSaidaStr = txtDataSaida.getText().trim();
            Date saida = dataSaidaStr.isEmpty() ? null : sdf.parse(dataSaidaStr);

            String tipoMembro = (String) cbTipoMembro.getSelectedItem();
            if (tipoMembro != null && tipoMembro.isEmpty()) {
                tipoMembro = null; // Trata seleção vazia
            }

            if (!validarCampos(nomeCompleto, cpf, email, telefone, cargoFuncao, inicio)) {
                JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MembroEquipeDTO membroEquipe = new MembroEquipeDTO(
                    nomeCompleto, cpf, email, telefone, cargoFuncao,
                    inicio, saida, tipoMembro
            );

            new MembroEquipeDAO().cadastrarMembroEquipe(membroEquipe);
            JOptionPane.showMessageDialog(this, "Membro da Equipe cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/mm/aaaa.", "Erro de Data", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID do doador voluntário deve ser um número inteiro.", "Erro de Número", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível cadastrar Membro da Equipe.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private boolean validarCampos(String nome, String cpf, String email, String telefone, String cargoFuncao, Date inicio) {
        return nome != null && !nome.isEmpty() &&
                cpf != null && !cpf.isEmpty() &&
                email != null && !email.isEmpty() &&
                telefone != null && !telefone.isEmpty() &&
                cargoFuncao != null && !cargoFuncao.isEmpty() &&
                inicio != null;
    }

    private void limparCampos() {
        txtNomeCompleto.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtCargoFuncao.setText("");
        txtDataInicio.setText("");
        txtDataSaida.setText("");
        cbTipoMembro.setSelectedIndex(0);
    }
}
