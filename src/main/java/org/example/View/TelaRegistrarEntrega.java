package org.example.View;

import org.example.DAO.AlocacaoRecursoDAO;
import org.example.DAO.CriancaDAO;
import org.example.DAO.DistribuicaoRecursoDAO;
import org.example.DTO.AlocacaoRecursoDTO;
import org.example.DTO.CriancaDTO;
import org.example.DTO.DistribuicaoRecursoDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Vector;

public class TelaRegistrarEntrega extends JFrame {

    private JComboBox<AlocacaoRecursoDTO> cbAlocacoesPendentes;
    private JComboBox<CriancaDTO> cbCriancas;
    private JButton btnRegistrar;

    public TelaRegistrarEntrega() {
        setTitle("Registrar Distribuição de Recurso");
        setSize(600, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Item/Alocação Pendente:"), gbc);
        cbAlocacoesPendentes = new JComboBox<>();
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbAlocacoesPendentes, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Criança que Recebeu:"), gbc);
        cbCriancas = new JComboBox<>();
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(cbCriancas, gbc);

        btnRegistrar = new JButton("Confirmar Entrega");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnRegistrar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        carregarDados();

        btnRegistrar.addActionListener(e -> registrarEntrega());
    }

    private void carregarDados() {
        try {
            // Carregar Alocações Pendentes
            AlocacaoRecursoDAO alocacaoDAO = new AlocacaoRecursoDAO();
            List<AlocacaoRecursoDTO> alocacoes = alocacaoDAO.listarEntregasPendentes();
            cbAlocacoesPendentes.setModel(new DefaultComboBoxModel<>(new Vector<>((Collection) alocacoes)));

            // Carregar Crianças
            CriancaDAO criancaDAO = new CriancaDAO();
            List<CriancaDTO> criancas = criancaDAO.listarCriancas();
            cbCriancas.setModel(new DefaultComboBoxModel<>(new Vector<>((Collection) criancas)));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do banco: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void registrarEntrega() {
        if (cbAlocacoesPendentes.getSelectedItem() == null || cbCriancas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um item e a criança que o recebeu.", "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            AlocacaoRecursoDTO alocacao = (AlocacaoRecursoDTO) cbAlocacoesPendentes.getSelectedItem();
            CriancaDTO crianca = (CriancaDTO) cbCriancas.getSelectedItem();
            int idMembroEquipe = 1; // Placeholder: No futuro, pegue o ID do usuário logado na sessão

            DistribuicaoRecursoDTO dto = new DistribuicaoRecursoDTO();
            dto.setIdAlocacaoRecurso(alocacao.getIdAlocacao());
            dto.setIdCrianca(crianca.getId_crianca());
            dto.setIdMembroEquipe(idMembroEquipe);
            dto.setDataDistribuicao(LocalDate.now());

            DistribuicaoRecursoDAO dao = new DistribuicaoRecursoDAO();
            dao.registrarEntrega(dto);

            JOptionPane.showMessageDialog(this, "Entrega registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Atualiza a lista de pendências, removendo o item que acabou de ser entregue
            carregarDados();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar a entrega no banco de dados: " + e.getMessage(), "Erro de Banco de Dados", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

