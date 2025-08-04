package org.example.DTO;

import java.sql.Timestamp;

public class AlocacaoRecursoDTO {
    private int idAlocacao;
    private String descricao;
    private int quantidadeAlocada;
    private float valorAlocado;
    private String destino;
    private java.sql.Timestamp dataDecisao;

    // Getters e Setters
    public int getIdAlocacao() {
        return idAlocacao;
    }

    public void setIdAlocacao(int idAlocacao) {
        this.idAlocacao = idAlocacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeAlocada() {
        return quantidadeAlocada;
    }

    public void setQuantidadeAlocada(int quantidadeAlocada) {
        this.quantidadeAlocada = quantidadeAlocada;
    }

    public float getValorAlocado() {
        return valorAlocado;
    }

    public void setValorAlocado(float valorAlocado) {
        this.valorAlocado = valorAlocado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Timestamp getDataDecisao() {
        return dataDecisao;
    }

    public void setDataDecisao(java.sql.Timestamp dataDecisao) {
        this.dataDecisao = dataDecisao;
    }

    // Sobrescreve o toString para exibir um texto amigável no JComboBox
    @Override
    public String toString() {
        return getDescricao() + " (ID: " + getIdAlocacao() + ")";
    }


}