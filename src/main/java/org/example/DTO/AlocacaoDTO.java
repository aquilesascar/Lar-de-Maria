package org.example.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlocacaoDTO {

    private int idAlocacao;
    private String descricao;
    private BigDecimal valorTotal;
    private BigDecimal valorDisponivel;
    private String categoriaDestino;
    private LocalDate dataAlocacao;

    // --- Getters e Setters ---

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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorDisponivel() {
        return valorDisponivel;
    }

    public void setValorDisponivel(BigDecimal valorDisponivel) {
        this.valorDisponivel = valorDisponivel;
    }

    public String getCategoriaDestino() {
        return categoriaDestino;
    }

    public void setCategoriaDestino(String categoriaDestino) {
        this.categoriaDestino = categoriaDestino;
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(LocalDate dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    /**
     * Sobrescreve o método toString para exibir a descrição no JComboBox.
     * Isso faz com que o ComboBox mostre um texto amigável em vez do nome do objeto.
     * @return A descrição da alocação.
     */
    @Override
    public String toString() {
        return getDescricao();
    }
}
