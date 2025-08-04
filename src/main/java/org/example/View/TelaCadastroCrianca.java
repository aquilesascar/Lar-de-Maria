package org.example.View;

import org.example.DAO.CriancaDAO;
import org.example.DTO.CriancaDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.sql.SQLException;

public class TelaCadastroCrianca extends JFrame {

    // Componentes do formulário
    private JTextField txtNome, txtDataNascimento, txtDataEntrada, txtEscola, txtLogradouro, txtNumero, txtComplemento, txtBairro, txtCidade;
    private JComboBox<String> cbGenero, cbEstado;
    private JTextArea txtMotivoAcolhimento, txtCondicoesMedicas;
    private JButton btnSalvar, btnLimpar;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelaCadastroCrianca() {
        setTitle("Cadastro de Nova Criança");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com os campos
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Seção de Dados Pessoais ---
        JPanel panelPessoal = createSectionPanel("Dados Pessoais");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(panelPessoal, gbc);
        // Nome
        panelPessoal.add(new JLabel("Nome Completo:"), createGbc(0, 0, GridBagConstraints.EAST));
        txtNome = new JTextField(30);
        panelPessoal.add(txtNome, createGbc(1, 0));
        //data de Nascimento
        panelPessoal.add(new JLabel("Data de Nascimento (dd/mm/aaaa):"), createGbc(0, 1, GridBagConstraints.EAST));
        txtDataNascimento = new JTextField(10);
        panelPessoal.add(txtDataNascimento, createGbc(1, 1));
        //gênero
        panelPessoal.add(new JLabel("Gênero:"), createGbc(2, 1, GridBagConstraints.EAST));
        cbGenero = new JComboBox<>(new String[]{"Feminino", "Masculino"});
        panelPessoal.add(cbGenero, createGbc(3, 1));

        // --- Seção de Endereço ---
        JPanel panelEndereco = createSectionPanel("Endereço de Origem");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; formPanel.add(panelEndereco, gbc);
        // Logradouro e Número
        panelEndereco.add(new JLabel("Logradouro:"), createGbc(0, 0, GridBagConstraints.EAST));
        txtLogradouro = new JTextField(25);
        panelEndereco.add(txtLogradouro, createGbc(1, 0));
        panelEndereco.add(new JLabel("Nº:"), createGbc(2, 0, GridBagConstraints.EAST));
        txtNumero = new JTextField(5);
        panelEndereco.add(txtNumero, createGbc(3, 0));
        // Complemento e Bairro
        panelEndereco.add(new JLabel("Complemento:"), createGbc(0, 1, GridBagConstraints.EAST));
        txtComplemento = new JTextField(10);
        panelEndereco.add(txtComplemento, createGbc(1, 1));
        panelEndereco.add(new JLabel("Bairro:"), createGbc(2, 1, GridBagConstraints.EAST));
        txtBairro = new JTextField(15);
        panelEndereco.add(txtBairro, createGbc(3, 1));
        // Cidade e Estado
        panelEndereco.add(new JLabel("Cidade:"), createGbc(0, 2, GridBagConstraints.EAST));
        txtCidade = new JTextField(15);
        panelEndereco.add(txtCidade, createGbc(1, 2));
        panelEndereco.add(new JLabel("Estado:"), createGbc(2, 2, GridBagConstraints.EAST));
        cbEstado = new JComboBox<>(new String[]{"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"});
        panelEndereco.add(cbEstado, createGbc(3, 2));

        // --- Seção de Acolhimento e Saúde ---
        JPanel panelAcolhimento = createSectionPanel("Informações de Acolhimento e Saúde");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(panelAcolhimento, gbc);
        // Data de Entrada
        panelAcolhimento.add(new JLabel("Data de Entrada (dd/mm/aaaa):"), createGbc(0, 0));
        txtDataEntrada = new JTextField(10);
        panelAcolhimento.add(txtDataEntrada, createGbc(1, 0));
        // Motivo Acolhimento
        panelAcolhimento.add(new JLabel("Motivo do Acolhimento:"), createGbc(0, 1));
        txtMotivoAcolhimento = new JTextArea(4, 20);
        panelAcolhimento.add(new JScrollPane(txtMotivoAcolhimento), createGbc(0, 2, 2, 1));
        // Condições Médicas
        panelAcolhimento.add(new JLabel("Condições Médicas:"), createGbc(0, 3));
        txtCondicoesMedicas = new JTextArea(4, 20);
        panelAcolhimento.add(new JScrollPane(txtCondicoesMedicas), createGbc(0, 4, 2, 1));

        // --- Seção de Educação ---
        JPanel panelEducacao = createSectionPanel("Informações Educacionais");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        formPanel.add(panelEducacao, gbc);
        // Escola
        panelEducacao.add(new JLabel("Escola:"), createGbc(0, 0));
        txtEscola = new JTextField(20);
        panelEducacao.add(txtEscola, createGbc(1, 0));

        // --- Painel de Botões ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSalvar = new JButton("Salvar Cadastro");
        btnLimpar = new JButton("Limpar Campos");
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnLimpar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Ações dos Botões ---
        btnSalvar.addActionListener(e -> salvarCrianca());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), title, TitledBorder.LEFT, TitledBorder.TOP));
        return panel;
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private GridBagConstraints createGbc(int x, int y, int anchor) {
        GridBagConstraints gbc = createGbc(x, y);
        gbc.anchor = anchor;
        return gbc;
    }

    private GridBagConstraints createGbc(int x, int y, int width, int height) {
        GridBagConstraints gbc = createGbc(x, y);
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private void salvarCrianca() {
        if (txtNome.getText().trim().isEmpty() || txtDataNascimento.getText().trim().isEmpty() || txtDataEntrada.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome, Data de Nascimento e Data de Entrada são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            CriancaDTO crianca = new CriancaDTO();
            crianca.setNome(txtNome.getText());
            crianca.setData_nascimento(LocalDate.parse(txtDataNascimento.getText(), dateFormatter));
            crianca.setData_entrada(LocalDate.parse(txtDataEntrada.getText(), dateFormatter));
            crianca.setGenero((String) cbGenero.getSelectedItem());
            crianca.setLogradouro(txtLogradouro.getText());
            if (!txtNumero.getText().trim().isEmpty()) {
                crianca.setNumero(Integer.parseInt(txtNumero.getText()));
            }
            crianca.setComplemento(txtComplemento.getText());
            crianca.setBairro(txtBairro.getText());
            crianca.setCidade(txtCidade.getText());
            crianca.setEstado((String) cbEstado.getSelectedItem());
            crianca.setMotivo_acolhimento(txtMotivoAcolhimento.getText());
            crianca.setCondicoes_medicas(txtCondicoesMedicas.getText());
            crianca.setEscola(txtEscola.getText());

            // Campos de saída não são preenchidos no cadastro inicial
            crianca.setData_saida(null);
            crianca.setMotivo_saida("");

            CriancaDAO criancaDAO = new CriancaDAO();
            criancaDAO.cadastrarCrianca(crianca);

            JOptionPane.showMessageDialog(this, "Criança cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/mm/aaaa.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo 'Número' deve conter apenas dígitos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no banco de dados: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        // Limpa todos os campos do formulário
        txtNome.setText("");
        txtDataNascimento.setText("");
        txtDataEntrada.setText("");
        cbGenero.setSelectedIndex(0);
        txtLogradouro.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        cbEstado.setSelectedIndex(0);
        txtMotivoAcolhimento.setText("");
        txtCondicoesMedicas.setText("");
        txtEscola.setText("");
        txtNome.requestFocus();
    }
}
