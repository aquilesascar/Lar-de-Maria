package org.example.View;

import org.example.DAO.CriancaDAO;
import org.example.DAO.NecessidadeEspecificaDAO;
import org.example.DTO.CriancaDTO;
import org.example.DTO.NecessidadeEspecificaDTO;
import org.example.DAO.MembroEquipeDAO;
import org.example.DTO.MembroEquipeDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;


public class TelaNecessidadeEspecifica extends JFrame {

    private JComboBox<CriancaDTO> cbCriancas;
    private JTextArea txtDescricao;
    private JComboBox<String> cbStatus;
    private JButton btnSalvar;
    private JTextField txtDataRegistro;
    private JLabel lblMembroEquipe;
    private javax.swing.JComboBox<MembroEquipeDTO> cbMembrosEquipe;


    public TelaNecessidadeEspecifica() {
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

        // Linha 0: Data de Registro (Automática)
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Data do Registro:"), gbc);
        txtDataRegistro = new JTextField(10);
        txtDataRegistro.setEditable(false);
        txtDataRegistro.setBackground(new Color(230, 230, 230));
        txtDataRegistro.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtDataRegistro, gbc);

        // Linha 1: Seleção da Criança
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Criança:"), gbc);
        cbCriancas = new JComboBox<>();
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbCriancas, gbc);

        // Linha 2: Descrição
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.NORTHEAST;
        formPanel.add(new JLabel("Descrição:"), gbc);
        txtDescricao = new JTextArea(5, 30);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JScrollPane(txtDescricao), gbc);

        // Linha 3: Status
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Status:"), gbc);
        cbStatus = new JComboBox<>(new String[]{"EM DESENVOLVIMENTO", "CONCLUIDO"});
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbStatus, gbc);

        // Linha 4: Membro da Equipe (Condicional - opcional, mantido mas invisível)
        lblMembroEquipe = new JLabel("Membro Responsável:");
        cbMembrosEquipe = new JComboBox<>();
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(lblMembroEquipe, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbMembrosEquipe, gbc);
        lblMembroEquipe.setVisible(false);
        cbMembrosEquipe.setVisible(false);

        // Botão Salvar
        btnSalvar = new JButton("Registrar Atendimento");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnSalvar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        carregarComboBoxes();

        cbStatus.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boolean atendida = "CONCLUIDO".equals(e.getItem());
                lblMembroEquipe.setVisible(atendida);
                cbMembrosEquipe.setVisible(atendida);
                if (atendida) {
                    carregarMembrosEquipe(); // opcional, caso deseje implementar
                }
            }
        });

        btnSalvar.addActionListener(e -> registrarAtendimento());
    }

    private void carregarMembrosEquipe() {
        try {
            org.example.DAO.MembroEquipeDAO dao = new org.example.DAO.MembroEquipeDAO();
            List<org.example.DTO.MembroEquipeDTO> membros = dao.listarMembros();
            cbMembrosEquipe.setModel(new DefaultComboBoxModel<>(new Vector<>(membros)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar membros da equipe: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void carregarComboBoxes() {
        CriancaDAO criancaDAO = new CriancaDAO();
        List<CriancaDTO> criancas = criancaDAO.listarCriancas();
        cbCriancas.setModel(new DefaultComboBoxModel<>(new Vector<>(criancas)));
    }

    private void registrarAtendimento() {
        if (cbCriancas.getSelectedIndex() == -1 || txtDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma criança e preencha a descrição.", "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            CriancaDTO criancaSelecionada = (CriancaDTO) cbCriancas.getSelectedItem();

            NecessidadeEspecificaDTO dto = new NecessidadeEspecificaDTO();
            dto.setIdCrianca(criancaSelecionada.getId_crianca());
            dto.setDescricao(txtDescricao.getText());
            dto.setStatus((String) cbStatus.getSelectedItem());

            NecessidadeEspecificaDAO dao = new NecessidadeEspecificaDAO();
            dao.registrarAtendimento(dto);

            JOptionPane.showMessageDialog(this, "Atendimento registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            txtDescricao.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar no banco de dados: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
}
