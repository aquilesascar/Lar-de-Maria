package org.example.DTO;

public class ConsultaCampanha {
    String nome_campanha;
    float valor_total;
    int quant_crianca;

    public ConsultaCampanha(String nome_campanha, float valor_total, int quant_crianca) {
        this.nome_campanha = nome_campanha;
        this.valor_total = valor_total;
        this.quant_crianca = quant_crianca;
    }

    public String getNome_campanha() {
        return nome_campanha;
    }

    public void setNome_campanha(String nome_campanha) {
        this.nome_campanha = nome_campanha;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public int getQuant_crianca() {
        return quant_crianca;
    }

    public void setQuant_crianca(int quant_crianca) {
        this.quant_crianca = quant_crianca;
    }
}
