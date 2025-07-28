package org.example.DTO;

public class PessoaFisicaDTO extends DoadorDTO {

    private String nome;
    private String cpf;
    private String rg;

    public PessoaFisicaDTO(String tipo_doador, String preferencia_meio_contato, String complemento, int numero, String estado, String cidade, String bairro, String logradouro, String frequencia_doacao, String telefone, String email, int id_doador, String rg, String cpf, String nome) {
        super(tipo_doador,preferencia_meio_contato, complemento, numero, estado, cidade, bairro, logradouro, frequencia_doacao, telefone, email, id_doador);
        this.rg = rg;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
