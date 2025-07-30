package org.example.DTO;

import java.util.Date;

public class MembroEquipeDTO {
    private int id_membro_equipe;
    private String nome_completo;
    private String cpf;
    private String email;
    private String telefone;
    private String cargo_funcao;
    private Date data_inicio;
    private Date data_saida;

    public MembroEquipeDTO(String nome_completo, String cpf, String email, String telefone, String cargo_funcao, Date data_inicio, Date data_saida) {
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo_funcao = cargo_funcao;
        this.data_inicio = data_inicio;
        this.data_saida = data_saida;
    }

    public MembroEquipeDTO(int id_membro_equipe, String nome_completo, String cpf, String email, String telefone, String cargo_funcao, Date data_inicio, Date data_saida) {
        this.id_membro_equipe = id_membro_equipe;
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo_funcao = cargo_funcao;
        this.data_inicio = data_inicio;
        this.data_saida = data_saida;
    }

    public int getId_membro_equipe() {
        return id_membro_equipe;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCargo_funcao() {
        return cargo_funcao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public Date getData_saida() {
        return data_saida;
    }
}
