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
    private String tipo_membro;
    private Integer id_doador_voluntario;

    public MembroEquipeDTO() {
    }

    public MembroEquipeDTO(String nome_completo, String cpf, String email, String telefone,
                           String cargo_funcao, Date data_inicio, Date data_saida,
                           String tipo_membro, Integer id_doador_voluntario) {
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo_funcao = cargo_funcao;
        this.data_inicio = data_inicio;
        this.data_saida = data_saida;
        this.tipo_membro = tipo_membro;
        this.id_doador_voluntario = id_doador_voluntario;
    }

    public MembroEquipeDTO(String nome_completo, String cpf, String email, String telefone,
                           String cargo_funcao, Date data_inicio, Date data_saida,
                           String tipo_membro) {
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo_funcao = cargo_funcao;
        this.data_inicio = data_inicio;
        this.data_saida = data_saida;
        this.tipo_membro = tipo_membro;
    }

    public MembroEquipeDTO(int id_membro_equipe, String nome_completo, String cpf, String email, String telefone,
                           String cargo_funcao, Date data_inicio, Date data_saida,
                           String tipo_membro, Integer id_doador_voluntario) {
        this.id_membro_equipe = id_membro_equipe;
        this.nome_completo = nome_completo;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo_funcao = cargo_funcao;
        this.data_inicio = data_inicio;
        this.data_saida = data_saida;
        this.tipo_membro = tipo_membro;
        this.id_doador_voluntario = id_doador_voluntario;
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

    public String getTipo_membro() {
        return tipo_membro;
    }

    public Integer getId_doador_voluntario() {
        return id_doador_voluntario;
    }

    public void setId_membro_equipe(int id_membro_equipe) {
        this.id_membro_equipe = id_membro_equipe;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCargo_funcao(String cargo_funcao) {
        this.cargo_funcao = cargo_funcao;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    public void setTipo_membro(String tipo_membro) {
        this.tipo_membro = tipo_membro;
    }

    public void setId_doador_voluntario(Integer id_doador_voluntario) {
        this.id_doador_voluntario = id_doador_voluntario;
    }

    @Override
    public String toString() {
        return nome_completo;
    }
}
