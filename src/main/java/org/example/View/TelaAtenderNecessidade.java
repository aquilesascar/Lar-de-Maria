package org.example.View;

import org.example.DAO.NecessidadeEspecificaDAO;
import org.example.DTO.AlocacaoDTO;
import org.example.DTO.CriancaDTO;
import org.example.DTO.NecessidadeEspecificaDTO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaAtenderNecessidade extends JFrame {

    private JComboBox<CriancaDTO> cbCriancas;
    private JComboBox<AlocacaoDTO> cbAlocacoes;
    private JTextArea txtDescricao;
    private JComboBox<String> cbStatus;
    private JButton btnSalvar;

    public TelaAtenderNecessidade() {
        setTitle("Atender Necessidade Específica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Linha 0: Seleção da Criança
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Criança:"), gbc);
        cbCriancas = new JComboBox<>();
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbCriancas, gbc);

        // Linha 1: Seleção da Alocação de Recurso
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Alocação de Recurso:"), gbc);
        cbAlocacoes = new JComboBox<>();
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbAlocacoes, gbc);

        // Linha 2: Descrição da Necessidade
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.NORTHEAST;
        formPanel.add(new JLabel("Descrição:"), gbc);
        txtDescricao = new JTextArea(5, 30);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JScrollPane(txtDescricao), gbc);

        // Linha 3: Status
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Status:"), gbc);
        cbStatus = new JComboBox<>(new String[]{"Pendente", "Em Andamento", "Atendida"});
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbStatus, gbc);

        // Botão Salvar
        btnSalvar = new JButton("Registrar Atendimento");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSalvar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Carregar dados nos ComboBoxes
        carregarComboBoxes();

        // Ação do botão
        btnSalvar.addActionListener(e -> registrarAtendimento());
    }

    private void carregarComboBoxes() {
        // try {
        //     // Carregar Crianças
        //     CriancaDAO criancaDAO = new CriancaDAO();
        //     List<CriancaDTO> criancas = criancaDAO.listarCriancas();
        //     cbCriancas.setModel(new DefaultComboBoxModel<>(new Vector<>(criancas)));
        //
        //     // Carregar Alocações
        //     AlocacaoRecursoDAO alocacaoDAO = new AlocacaoRecursoDAO();
        //     List<AlocacaoDTO> alocacoes = alocacaoDAO.listarAlocacoes();
        //     cbAlocacoes.setModel(new DefaultComboBoxModel<>(new Vector<>(alocacoes)));
        //
        // } catch (SQLException e) {
        //     JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        // }
    }

    private void registrarAtendimento() {
        if (cbCriancas.getSelectedIndex() == -1 || cbAlocacoes.getSelectedIndex() == -1 || txtDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma criança, uma alocação e preencha a descrição.", "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Coleta os dados da tela
            CriancaDTO criancaSelecionada = (CriancaDTO) cbCriancas.getSelectedItem();
            AlocacaoDTO alocacaoSelecionada = (AlocacaoDTO) cbAlocacoes.getSelectedItem();

            NecessidadeEspecificaDTO dto = new NecessidadeEspecificaDTO();
            dto.setIdCrianca(criancaSelecionada.getId_crianca());
            dto.setIdAlocacaoRecurso(alocacaoSelecionada.getIdAlocacao());
            dto.setDescricao(txtDescricao.getText());
            dto.setStatus((String) cbStatus.getSelectedItem());

            // Chama o DAO para salvar
            NecessidadeEspecificaDAO dao = new NecessidadeEspecificaDAO();
            dao.registrarAtendimento(dto);

            JOptionPane.showMessageDialog(this, "Atendimento de necessidade registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Limpar campos se necessário
            txtDescricao.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar no banco de dados: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
}

