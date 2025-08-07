package org.example.DTO;

/**
 * DTO para armazenar os dados agregados do relatório de crianças por faixa etária.
 */
public class RelatorioIdadeCriancaDTO {

    private String faixaEtaria;
    private int totalCriancas;

    // Getters e Setters
    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public int getTotalCriancas() {
        return totalCriancas;
    }

    public void setTotalCriancas(int totalCriancas) {
        this.totalCriancas = totalCriancas;
    }
}
