package org.example.DTO;

import java.util.Date;
import java.util.Random;

public class CampanhaDTO {
    int id_campanha;
    String nome_campanha;
    float valor_quantidade_arrcadado;
    String meta_arrecadacao;
    Date data_inicio;
    Date data_final;

    public CampanhaDTO(Date data_final, Date data_inicio, String meta_arrecadacao, float valor_quantidade_arrcadado, String nome_campanha) {
        this.nome_campanha = nome_campanha;
        this.valor_quantidade_arrcadado = valor_quantidade_arrcadado;
        this.meta_arrecadacao = meta_arrecadacao;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
    }

    public CampanhaDTO(Date data_final, Date data_inicio, String meta_arrecadacao, float valor_quantidade_arrcadado, String nome_campanha, int id_campanha) {
        this.id_campanha = id_campanha;
        this.nome_campanha = nome_campanha;
        this.valor_quantidade_arrcadado = valor_quantidade_arrcadado;
        this.meta_arrecadacao = meta_arrecadacao;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
    }

    public String getNome_campanha() {
        return nome_campanha;
    }

    public int getId_campanha() {
        return id_campanha;
    }

    public float getValor_quantidade_arrcadado() {
        return valor_quantidade_arrcadado;
    }

    public String getMeta_arrecadacao() {
        return meta_arrecadacao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public Date getData_final() {
        return data_final;
    }

    private int gerarIdCampanhaAleatorio() {
        Random random = new Random();
        return 10000 + random.nextInt(90000); // Gera entre 10000 e 99999
    }
}