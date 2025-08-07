package org.example.DTO;

public class RelatorioCriancaDTO {

    private String faixaEtaria;
    private String genero;
    private int totalCriancas;

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTotalCriancas() {
        return totalCriancas;
    }

    public void setTotalCriancas(int totalCriancas) {
        this.totalCriancas = totalCriancas;
    }
}

