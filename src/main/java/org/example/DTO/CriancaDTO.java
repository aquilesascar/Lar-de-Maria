package org.example.DTO;

import java.time.LocalDate;

public class CriancaDTO {

    private int id_crianca;
    private String nome;
    private LocalDate data_nascimento;
    private String genero;
    private LocalDate data_entrada;
    private String motivo_acolhimento;
    private String condicoes_medicas;
    private String escola;
    private LocalDate data_saida;
    private String motivo_saida;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    //Getters e Setters para todos os campos
    public int getId_crianca() {
        return id_crianca;
    }

    public void setId_crianca(int id_crianca) {
        this.id_crianca = id_crianca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(LocalDate data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getMotivo_acolhimento() {
        return motivo_acolhimento;
    }

    public void setMotivo_acolhimento(String motivo_acolhimento) {
        this.motivo_acolhimento = motivo_acolhimento;
    }

    public String getCondicoes_medicas() {
        return condicoes_medicas;
    }

    public void setCondicoes_medicas(String condicoes_medicas) {
        this.condicoes_medicas = condicoes_medicas;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public LocalDate getData_saida() {
        return data_saida;
    }

    public void setData_saida(LocalDate data_saida) {
        this.data_saida = data_saida;
    }

    public String getMotivo_saida() {
        return motivo_saida;
    }

    public void setMotivo_saida(String motivo_saida) {
        this.motivo_saida = motivo_saida;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id_crianca + ")";
    }
}

