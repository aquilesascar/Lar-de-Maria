package org.example.View;

import org.example.DAO.AlocacaoRecursoDAO;
import org.example.DAO.CriancaDAO;
import org.example.DAO.DistribuicaoRecursoDAO;
import org.example.DTO.AlocacaoRecursoDTO;
import org.example.DTO.CriancaDTO;
import org.example.DTO.DistribuicaoRecursoDTO;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Vector;

public class TelaRegistrarEntrega extends JFrame {

    private JComboBox<AlocacaoRecursoDTO> cbAlocacoesPendentes;
    private JComboBox<CriancaDTO> cbCriancas;
    private JButton btnRegistrar;
    private JFormattedTextField txtDataEntrega;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelaRegistrarEntrega() {
        setTitle("Registrar Distribuição de Recurso");
        setSize(600, 300); // Ajuste de altura para os campos
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Linha 0: Criança que Recebeu
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Criança que Recebeu:"), gbc);
        cbCriancas = new JComboBox<>();
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbCriancas, gbc);

        // Linha 1: Item/Alocação Pendente (será preenchido com base na criança)
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Item/Alocação Pendente:"), gbc);
        cbAlocacoesPendentes = new JComboBox<>();
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbAlocacoesPendentes, gbc);

        // Linha 2: Data da Entrega
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Data da Entrega:"), gbc);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            txtDataEntrega = new JFormattedTextField(dateMask);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            txtDataEntrega = new JFormattedTextField();
        }
        txtDataEntrega.setColumns(10);
        txtDataEntrega.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtDataEntrega, gbc);

        // Botão de Registro
        btnRegistrar = new JButton("Confirmar Entrega");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnRegistrar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- LÓGICA DE EVENTOS ---
        // Adiciona o "ouvinte" que tornará a tela interativa
        cbCriancas.addActionListener(e -> atualizarAlocacoes());
        btnRegistrar.addActionListener(e -> registrarEntrega());

        // Carrega os dados iniciais
        carregarDadosIniciais();
    }

    /**
     * Carrega a lista de crianças (que não muda) e atualiza as alocações pela primeira vez.
     */
    private void carregarDadosIniciais() {
        try {
            CriancaDAO criancaDAO = new CriancaDAO();
            List<CriancaDTO> criancas = criancaDAO.listarCriancas();
            cbCriancas.setModel(new DefaultComboBoxModel<>(new Vector<>(criancas)));

            // Chama o método para carregar as alocações da primeira criança da lista
            atualizarAlocacoes();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados iniciais: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Atualiza o ComboBox de alocações com base na criança atualmente selecionada.
     * Este método é chamado tanto no início quanto toda vez que o usuário muda a seleção.
     */
    private void atualizarAlocacoes() {
        CriancaDTO criancaSelecionada = (CriancaDTO) cbCriancas.getSelectedItem();

        if (criancaSelecionada == null) {
            cbAlocacoesPendentes.setModel(new DefaultComboBoxModel<>());
            return;
        }

        try {
            AlocacaoRecursoDAO alocacaoDAO = new AlocacaoRecursoDAO();
            List<AlocacaoRecursoDTO> alocacoes = alocacaoDAO.listarAlocacoesPorCrianca(criancaSelecionada.getId_crianca());
            cbAlocacoesPendentes.setModel(new DefaultComboBoxModel<>(new Vector<>(alocacoes)));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar alocações para a criança selecionada: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void registrarEntrega() {
        if (cbAlocacoesPendentes.getSelectedItem() == null || cbCriancas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um item e a criança que o recebeu.", "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            LocalDate dataEntrega;
            try {
                // CORREÇÃO: Lê a data do campo de texto em vez de usar a data atual
                dataEntrega = LocalDate.parse(txtDataEntrega.getText(), dateFormatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/mm/aaaa.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            AlocacaoRecursoDTO alocacao = (AlocacaoRecursoDTO) cbAlocacoesPendentes.getSelectedItem();
            CriancaDTO crianca = (CriancaDTO) cbCriancas.getSelectedItem();
            int idMembroEquipe = 1; // Placeholder: Pegue o ID do usuário logado na sessão

            DistribuicaoRecursoDTO dto = new DistribuicaoRecursoDTO();
            dto.setIdAlocacaoRecurso(alocacao.getIdAlocacao());
            dto.setIdCrianca(crianca.getId_crianca());
            dto.setIdMembroEquipe(idMembroEquipe);
            dto.setDataDistribuicao(dataEntrega);

            DistribuicaoRecursoDAO dao = new DistribuicaoRecursoDAO();
            dao.registrarEntrega(dto);

            JOptionPane.showMessageDialog(this, "Entrega registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Atualiza a lista de pendências
            atualizarAlocacoes();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar a entrega no banco de dados: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
