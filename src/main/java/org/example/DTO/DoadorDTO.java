package org.example.DTO;

public abstract class DoadorDTO {

    private int id_doador;

    private String email;
    private String telefone;
    private String frequencia_doacao;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private int numero;
    private String complemento;
    private String tipo_doador;

    public String getTipo_doador() {
        return tipo_doador;
    }

    public void setTipo_doador(String tipo_doador) {
        this.tipo_doador = tipo_doador;
    }

    public DoadorDTO(String tipo_doador, String preferencia_meio_contato, String complemento, int numero, String estado, String cidade, String bairro, String logradouro, String frequencia_doacao, String telefone, String email, int id_doador) {
        this.tipo_doador = tipo_doador;
        this.preferencia_meio_contato = preferencia_meio_contato;
        this.complemento = complemento;
        this.numero = numero;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.frequencia_doacao = frequencia_doacao;
        this.telefone = telefone;
        this.email = email;
        this.id_doador = id_doador;
    }



    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    private String preferencia_meio_contato;

    public int getId_doador() {
        return id_doador;
    }

    public void setId_doador(int id_doador) {
        this.id_doador = id_doador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFrequencia_doacao() {
        return frequencia_doacao;
    }

    public void setFrequencia_doacao(String frequencia_doacao) {
        this.frequencia_doacao = frequencia_doacao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPreferencia_meio_contato() {
        return preferencia_meio_contato;
    }

    public void setPreferencia_meio_contato(String preferencia_meio_contato) {
        this.preferencia_meio_contato = preferencia_meio_contato;
    }
}
