package org.example.DTO;

public class DoadorTotalDTO {
    private String nome;
    private float total_doacao;

    public DoadorTotalDTO(String nome, float total_doacao) {
        this.nome = nome;
        this.total_doacao = total_doacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTotal_doacao() {
        return total_doacao;
    }

    public void setTotal_doacao(float total_doacao) {
        this.total_doacao = total_doacao;
    }
}