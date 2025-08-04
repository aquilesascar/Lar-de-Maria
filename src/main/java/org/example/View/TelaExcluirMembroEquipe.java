package org.example.View;

import org.example.DAO.MembroEquipeDAO;
import org.example.DTO.MembroEquipeDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaExcluirMembroEquipe extends JFrame {
    private JComboBox<MembroEquipeDTO> comboMembros;
    private JButton btnExcluir, btnCancelar;

    public TelaExcluirMembroEquipe() {
        setTitle("Excluir Membro da Equipe");
        setSize(400, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        comboMembros = new JComboBox<>();
        carregarMembros();

        btnExcluir = new JButton("Excluir");
        btnCancelar = new JButton("Cancelar");

        JPanel panelCentral = new JPanel();
        panelCentral.add(new JLabel("Selecione um membro:"));
        panelCentral.add(comboMembros);

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnCancelar);

        add(panelCentral, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        // Ação do botão Excluir
        btnExcluir.addActionListener(this::excluirMembro);

        // Ação do botão Cancelar
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void carregarMembros() {
        MembroEquipeDAO dao = new MembroEquipeDAO();
        List<MembroEquipeDTO> membros = dao.listarMembros();
        comboMembros.removeAllItems();
        for (MembroEquipeDTO membro : membros) {
            comboMembros.addItem(membro); // toString() será usado para exibir
        }
    }

    private void excluirMembro(ActionEvent e) {
        MembroEquipeDTO membroSelecionado = (MembroEquipeDTO) comboMembros.getSelectedItem();
        if (membroSelecionado != null) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja excluir " + membroSelecionado.getNome_completo() + "?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                MembroEquipeDAO dao = new MembroEquipeDAO();
                dao.deletarMembroEquipe(membroSelecionado.getId_membro_equipe());
                carregarMembros(); // atualiza lista
            }
        }
    }
}
