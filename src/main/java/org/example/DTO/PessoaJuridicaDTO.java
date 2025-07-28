package org.example.DTO;

public class PessoaJuridicaDTO extends DoadorDTO {

    private String razao_social;
    private String cnpj;
    private String nome_contato;

    public PessoaJuridicaDTO(String tipo_doador, String preferencia_meio_contato, String complemento, int numero, String estado, String cidade, String bairro, String logradouro, String frequencia_doacao, String telefone, String email, int id_doador, String nome_contato, String cnpj, String razao_social) {
        super(tipo_doador, preferencia_meio_contato, complemento, numero, estado, cidade, bairro, logradouro, frequencia_doacao, telefone, email, id_doador);
        this.nome_contato = nome_contato;
        this.cnpj = cnpj;
        this.razao_social = razao_social;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getNome_contato() {
        return nome_contato;
    }

    public void setNome_contato(String nome_contato) {
        this.nome_contato = nome_contato;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
