package org.example.DTO;

import java.time.LocalDate;

public class DoacaoItemMaterialDTO extends DoacaoDTO{
    private String descricao_detalhada;
    private String estado_conservacao;
    private int quantidade;
    private float estimativa_valor;

    public DoacaoItemMaterialDTO(LocalDate data, String tipo_doacao, int id_doacao, Integer id_doador, Integer id_campanha, String descricao_detalhada, String estado_conservacao, int quantidade, float estimativa_valor) {
        super(data, tipo_doacao, id_doacao, id_doador, id_campanha);
        this.descricao_detalhada = descricao_detalhada;
        this.estado_conservacao = estado_conservacao;
        this.quantidade = quantidade;
        this.estimativa_valor = estimativa_valor;
    }

    public String getDescricao_detalhada() {
        return descricao_detalhada;
    }

    public void setDescricao_detalhada(String descricao_detalhada) {
        this.descricao_detalhada = descricao_detalhada;
    }

    public String getEstado_conservacao() {
        return estado_conservacao;
    }

    public void setEstado_conservacao(String estado_conservacao) {
        this.estado_conservacao = estado_conservacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getEstimativa_valor() {
        return estimativa_valor;
    }

    public void setEstimativa_valor(float estimativa_valor) {
        this.estimativa_valor = estimativa_valor;
    }
}
