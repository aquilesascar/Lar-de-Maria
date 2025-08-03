package org.example.View;

import org.example.DAO.MembroEquipeDAO;
import org.example.DTO.MembroEquipeDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaAtualizarMembroEquipe extends JFrame {
    private JComboBox<MembroEquipeDTO> comboMembros;
    private JComboBox<String> comboCampos;
    private JTextField txtNovoValor;
    private JButton btnAtualizar, btnCancelar;

    public TelaAtualizarMembroEquipe() {
        setTitle("Atualizar Membro da Equipe");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        comboMembros = new JComboBox<>();
        carregarMembros();

        comboCampos = new JComboBox<>(new String[]{
                "nome_completo", "cpf", "email", "telefone", "cargo_funcao", "tipo_membro"
        });

        txtNovoValor = new JTextField();
        btnAtualizar = new JButton("Atualizar");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Selecione o membro:"));
        add(comboMembros);

        add(new JLabel("Selecione o campo a atualizar:"));
        add(comboCampos);

        add(new JLabel("Novo valor:"));
        add(txtNovoValor);

        JPanel botoes = new JPanel();
        botoes.add(btnAtualizar);
        botoes.add(btnCancelar);
        add(botoes);

        btnAtualizar.addActionListener(this::atualizarCampo);
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void carregarMembros() {
        MembroEquipeDAO dao = new MembroEquipeDAO();
        List<MembroEquipeDTO> membros = dao.listarMembros();
        comboMembros.removeAllItems();
        for (MembroEquipeDTO membro : membros) {
            comboMembros.addItem(membro);
        }
    }

    private void atualizarCampo(ActionEvent e) {
        MembroEquipeDTO membro = (MembroEquipeDTO) comboMembros.getSelectedItem();
        String campo = (String) comboCampos.getSelectedItem();
        String novoValor = txtNovoValor.getText().trim();

        if (membro != null && campo != null && !novoValor.isEmpty()) {
            new MembroEquipeDAO().atualizarCampoMembroEquipe(membro.getId_membro_equipe(), campo, novoValor);
            JOptionPane.showMessageDialog(this, "Campo atualizado com sucesso!");
            txtNovoValor.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
        }
    }
}
