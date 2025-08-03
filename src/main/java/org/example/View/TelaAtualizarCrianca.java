package org.example.View;

import org.example.DAO.CriancaDAO;
import org.example.DTO.CriancaDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaAtualizarCrianca extends JFrame {
    private JComboBox<CriancaDTO> comboCriancas;
    private JComboBox<String> comboCampos;
    private JTextField txtNovoValor;
    private JButton btnAtualizar, btnCancelar;

    public TelaAtualizarCrianca() {
        setTitle("Atualizar Criança");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        comboCriancas = new JComboBox<>();
        carregarCriancas();

        comboCampos = new JComboBox<>(new String[]{
                "nome",
                "data_nascimento",
                "genero",
                "data_entrada",
                "motivo_acolhimento",
                "condicoes_medicas",
                "escola",
                "data_saida",
                "motivo_saida",
                "logradouro",
                "numero",
                "complemento",
                "bairro",
                "cidade",
                "estado"
        });

        txtNovoValor = new JTextField();
        btnAtualizar = new JButton("Atualizar");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Selecione a criança:"));
        add(comboCriancas);

        add(new JLabel("Campo a atualizar:"));
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

    private void carregarCriancas() {
        CriancaDAO dao = new CriancaDAO();
        List<CriancaDTO> criancas = dao.listarCriancas();
        comboCriancas.removeAllItems();
        for (CriancaDTO c : criancas) {
            comboCriancas.addItem(c);
        }
    }

    private void atualizarCampo(ActionEvent e) {
        CriancaDTO crianca = (CriancaDTO) comboCriancas.getSelectedItem();
        String campo = (String) comboCampos.getSelectedItem();
        String novoValor = txtNovoValor.getText().trim();

        if (crianca != null && campo != null && !novoValor.isEmpty()) {
            new CriancaDAO().atualizarCampoCrianca(crianca.getId_crianca(), campo, novoValor);
            JOptionPane.showMessageDialog(this, "Campo atualizado com sucesso!");
            txtNovoValor.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
        }
    }
}
