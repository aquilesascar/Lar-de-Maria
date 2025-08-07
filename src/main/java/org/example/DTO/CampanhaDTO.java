package org.example.DTO;

import java.util.Date;

public class CampanhaDTO {
    private int id_campanha;
    private String nome_campanha;
    private int meta_arrecadacao;
    private String meta_descricao;
    private Date data_inicio;
    private Date data_final;

    public CampanhaDTO(int id_campanha, String nome_campanha, Integer meta_arrecadacao, String meta_descricao, Date data_inicio, Date data_final) {
        this.id_campanha = id_campanha;
        this.nome_campanha = nome_campanha;
        this.meta_arrecadacao = meta_arrecadacao;
        this.meta_descricao = meta_descricao;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
    }

    public CampanhaDTO(String nome_campanha, Integer meta_arrecadacao, String meta_descricao, Date data_inicio, Date data_final) {
        this.nome_campanha = nome_campanha;
        this.meta_arrecadacao = meta_arrecadacao;
        this.meta_descricao = meta_descricao;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
    }

    public int getId_campanha() {
        return id_campanha;
    }

    public String getNome_campanha() {
        return nome_campanha;
    }

    public int getMeta_arrecadacao() {
        return meta_arrecadacao;
    }

    public String getMeta_descricao() {
        return meta_descricao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setId_campanha(int id_campanha) {
        this.id_campanha = id_campanha;
    }

    public void setNomeCampanha(String nome_campanha) {
        this.nome_campanha = nome_campanha;
    }

    public void setMetaArrecadacao(Integer meta_arrecadacao) {
        this.meta_arrecadacao = meta_arrecadacao;
    }

    public void setMetaDescricao(String meta_descricao) {
        this.meta_descricao = meta_descricao;
    }

    public void setDataInicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public void setDataFinal(Date data_final) {
        this.data_final = data_final;
    }
}
